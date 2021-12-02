package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.sport.ProfileStatisticsResponse;
import com.wellbeeing.wellbeeing.domain.sport.ReportExercise;
import com.wellbeeing.wellbeeing.domain.sport.ReportTraining;
import com.wellbeeing.wellbeeing.domain.sport.SportReport;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.sport.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service("sportReportService")
public class SportReportServiceImpl implements SportReportService {

    private final EntityManager entityManager;

    public SportReportDAO sportReportDAO;
    public ProfileDAO profileDAO;
    public TrainingDAO trainingDAO;
    public ExerciseDAO exerciseDAO;
    public ReportExerciseDAO reportExerciseDAO;
    public ReportTrainingDAO reportTrainingDAO;
//    public ReportDishDetailDAO reportDishDetailDAO;

    public ExerciseService exerciseService;

    public SportReportServiceImpl(@Qualifier("sportReportDAO") SportReportDAO sportReportDAO,
                                  @Qualifier("exerciseDAO") ExerciseDAO exerciseDAO,
                                  @Qualifier("trainingDAO") TrainingDAO trainingDAO,
                                  @Qualifier("exerciseService") ExerciseService exerciseService,
                                  @Qualifier("profileDAO") ProfileDAO profileDAO,
                                  @Qualifier("reportExerciseDAO") ReportExerciseDAO reportExerciseDAO,
                                  @Qualifier("reportTrainingDAO") ReportTrainingDAO reportTrainingDAO,
                                  EntityManager entityManager) {
        this.sportReportDAO = sportReportDAO;
        this.exerciseDAO = exerciseDAO;
        this.trainingDAO = trainingDAO;
        this.exerciseService = exerciseService;
        this.profileDAO = profileDAO;
        this.reportExerciseDAO = reportExerciseDAO;
        this.reportTrainingDAO = reportTrainingDAO;

        this.entityManager = entityManager;
    }

    @Override
    public SportReport getSportReportById(UUID reportId) throws NotFoundException {
        SportReport report = sportReportDAO.findById(reportId).orElse(null);
        if (report == null)
            throw new NotFoundException("Sport report with id: " + reportId + " not found");

        double weight = report.getReportOwner().getProfileCard().getWeight();
        if (report.getExerciseList() != null)
            report.getExerciseList().forEach(re -> {
                re.getExercise().setCaloriesBurned(re.getExercise().countCaloriesPerHour(weight));
            });
        if (report.getTrainingList() != null)
            report.getTrainingList().forEach(tr -> {
                tr.getTraining().setCaloriesBurned(tr.getTraining().caloriesBurned(weight));
                tr.getTraining().getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(weight)));
            });
        report.preUpdate();
        return report;
    }

    @Override
    public SportReport getSportReportByDateAndOwner(LocalDate date, Profile owner) throws NotFoundException {

        SportReport report = sportReportDAO.findByReportDateAndReportOwner(date, owner).orElse(null);
        if (report == null)
            throw new NotFoundException("Sport report for date: " + date + " for " + owner.getFullName() + " not found");

        calculateReportDetails(report);
        return report;
    }

    @Override
    public List<ReportExercise> getReportExercisesByReportId(UUID reportId) throws NotFoundException {

        return getSportReportById(reportId).getExerciseList();

    }

    @Override
    public SportReport deleteExercisesFromReportByReportId(UUID reportId, List<Long> exercisesIds) throws NotFoundException {
        SportReport report = getSportReportById(reportId);
        List<ReportExercise> exercises = report.getExerciseList();
        for (Long id : exercisesIds) {
            ReportExercise found = reportExerciseDAO.findById(id).orElse(null);
            if (found != null && exercises.contains(found)) {
                report.removeReportExerciseFromReport(found);
            }
        }
//        entityManager.clear();
//        SportReport reportUpdated = getSportReportById(report.getId());
//        reportUpdated.setDerived();
        return sportReportDAO.save(report);
    }

    @Override
    public boolean deleteSportReportById(UUID reportId) throws NotFoundException {
        SportReport report = sportReportDAO.findById(reportId).orElse(null);
        if (report != null) {
            sportReportDAO.deleteById(reportId);
            return true;
        } else throw new NotFoundException("Sport report with id: " + reportId + " not found");

    }

    @Override
    public SportReport addSportReportForProfileByProfileId(UUID profileId) throws NotFoundException, ConflictException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if (profile == null)
            throw new NotFoundException("Profile with id:" + profileId + " not found");
        SportReport newReport = SportReport.builder()
                .reportDate(LocalDate.now())
                .reportOwner(profile)
                .build();
        if (sportReportDAO.findByReportDateAndReportOwner(newReport.getReportDate(), profile).orElse(null) != null) {
            throw new ConflictException("Report for this day already exists");
        }
        sportReportDAO.save(newReport);
        return newReport;
    }

    @Override
    public SportReport addSportReportForProfileByDateAndProfileId(LocalDate reportDate, UUID profileId) throws NotFoundException, ConflictException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if (profile == null)
            throw new NotFoundException("Profile with id:" + profileId + " not found");
        SportReport newReport = SportReport.builder()
                .reportDate(reportDate)
                .reportOwner(profile)
                .build();
        if (sportReportDAO.findByReportDateAndReportOwner(newReport.getReportDate(), profile).orElse(null) != null) {
            throw new ConflictException("Report for this day already exists");
        }
        sportReportDAO.save(newReport);
        return newReport;
    }

    @Override
    public SportReport addExercisesToReportByReportId(List<ReportExercise> exercises, UUID reportId) throws NotFoundException {
        SportReport report = getSportReportById(reportId);
        for (ReportExercise ex : exercises) {
            ex.setSportReport(report);
            ReportExercise rdd = reportExerciseDAO.saveAndFlush(ex);
            entityManager.clear();
            ReportExercise newExercise = reportExerciseDAO.findById(rdd.getId()).orElse(null);
        }
        report = getSportReportById(reportId);
        return sportReportDAO.save(report);

    }

    @Override
    public Map<String, Map<String, Double>> countDetailedElementsAmountsByReportId(UUID reportId) throws NotFoundException {
        return null;
    }

    @Override
    public List<SportReport> getSportReportByDateAndProfileId(LocalDate date, UUID profileId) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if (profile == null)
            throw new NotFoundException("Profile with id: " + profileId + " not found");
        double weight = profile.getProfileCard().getWeight();

        List<SportReport> reports = profile.getSportReports().stream().filter
                (r -> r.getReportDate().equals(date)).collect(Collectors.toList());
        reports.forEach(re -> {
            re.getExerciseList().forEach(ex -> {
                ex.getExercise().setCaloriesBurned(ex.getExercise().countCaloriesPerHour(weight));
            });
            re.getTrainingList().forEach(tr -> {
                tr.getTraining().setCaloriesBurned(tr.getTraining().caloriesBurned(weight));
                tr.getTraining().getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(weight)));
            });
            re.preUpdate();
        });
        return reports;

    }

    @Override
    public List<SportReport> getSportReportsByProfileId(UUID profileId) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if (profile == null)
            throw new NotFoundException("Profile with id: " + profileId + " not found");
        double weight = profile.getProfileCard().getWeight();

        List<SportReport> reports = profile.getSportReports();
        reports.forEach(re -> {
            re.getExerciseList().forEach(ex -> {
                ex.getExercise().setCaloriesBurned(ex.getExercise().countCaloriesPerHour(weight));
            });
            re.getTrainingList().forEach(tr -> {
                tr.getTraining().setCaloriesBurned(tr.getTraining().caloriesBurned(weight));
                tr.getTraining().getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(weight)));
            });
            re.preUpdate();
        });
        return reports;
    }

    @Override
    public List<SportReport> getSportReportsByMonthAndProfileId(int month, int year, UUID profileId) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if (profile == null)
            throw new NotFoundException("Profile with id: " + profileId + " not found");
        double weight = profile.getProfileCard().getWeight();
        List<SportReport> reports = profile.getSportReports().stream().filter
                        (r -> r.getReportDate().getMonth().getValue() == month && r.getReportDate().getYear() == year)
                .collect(Collectors.toList());
        reports.forEach(re -> {
            re.getExerciseList().forEach(ex -> {
                ex.getExercise().setCaloriesBurned(ex.getExercise().countCaloriesPerHour(weight));
            });
            re.getTrainingList().forEach(tr -> {
                tr.getTraining().setCaloriesBurned(tr.getTraining().caloriesBurned(weight));
                tr.getTraining().getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(weight)));
            });
            re.preUpdate();
        });
        return reports;
    }

    @Override
    public List<SportReport> getSportReportsByProfileIdAndDate(UUID profileId, LocalDate start, LocalDate end) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if (profile == null)
            throw new NotFoundException("Profile with id: " + profileId + " not found");
        return sportReportDAO.findByReportOwnerIdAndReportDateBetweenOrderByReportDate(profileId, start, end);
    }

    @Override
    public SportReport deleteTrainingsFromReportByReportId(UUID reportId, List<Long> trainingsIds) throws NotFoundException {
        SportReport report = getSportReportById(reportId);
        List<ReportTraining> trainings = report.getTrainingList();
        for (Long id : trainingsIds) {
            ReportTraining found = reportTrainingDAO.findById(id).orElse(null);
            if (found != null && trainings.contains(found)) {
                report.removeReportTrainingFromReport(found);
            }
        }
//        entityManager.clear();
//        SportReport reportUpdated = getSportReportById(report.getId());
//        reportUpdated.setDerived();
        return sportReportDAO.save(report);
    }

    @Override
    public SportReport addTrainingsToReportByReportId(List<ReportTraining> trainings, UUID reportId) throws NotFoundException {
        SportReport report = getSportReportById(reportId);
        for (ReportTraining tr : trainings) {
            tr.setSportReport(report);
            ReportTraining rdd = reportTrainingDAO.saveAndFlush(tr);
            entityManager.clear();
            ReportTraining newTraining = reportTrainingDAO.findById(rdd.getId()).orElse(null);
        }
        report = getSportReportById(reportId);
        return sportReportDAO.save(report);
    }

    @Override
    public ProfileStatisticsResponse getStatisticsByProfileIdAndDate(UUID userId, LocalDate from, LocalDate to) throws NotFoundException, ConflictException {
        Profile user = profileDAO.findProfileByProfileUserId(userId).orElse(null);
        if (user == null)
            throw new NotFoundException("No user with such ID"); //Exception

        List<SportReport> reportsInChosenPeriod = sportReportDAO.findAllByReportDateBetweenOrderByReportDate(from, to);
        if (reportsInChosenPeriod == null || reportsInChosenPeriod.size() == 0)
            throw new ConflictException("No data from chosen period");

        reportsInChosenPeriod.forEach(this::calculateReportDetails);
        Map<Profile, List<SportReport>> reportsByProfile = new HashMap<>();
        reportsInChosenPeriod.forEach(r -> {
            if (!reportsByProfile.containsKey(r.getReportOwner())) {
                reportsByProfile.put(r.getReportOwner(), new ArrayList<>());
            }
            reportsByProfile.get(r.getReportOwner()).add(r);
        });

        List<SportReport> userReports = reportsByProfile.get(user);
        if (userReports == null || userReports.size() == 0)
            throw new ConflictException("User has no reports in chosen period");

        reportsByProfile.remove(user);
        int noProfiles = reportsByProfile.size();

        double userCalories = userReports.stream().map(SportReport::getCaloriesBurned).mapToInt(num -> num).sum();
        double userTrainingMinutes = (double) userReports.stream().map(SportReport::getTotalTimeSeconds).mapToInt(num -> num).sum() / 60;
        double userTrainingDays = userReports.size();

        double avgTrainingDays = reportsByProfile.values().stream().map(List::size).mapToInt(num -> num).average().orElse(0);

        int totalCalories = reportsByProfile.values().stream().map(list ->
                list.stream().map(SportReport::getCaloriesBurned).mapToInt(num -> num).sum()
        ).mapToInt(num -> num).sum();

        double totalTrainingMinutes = (double) reportsByProfile.values().stream().map(list ->
                list.stream().map(SportReport::getTotalTimeSeconds).mapToInt(num -> num).sum()
        ).mapToInt(num -> num).sum() / 60;

        double userCaloriesBetterThanPercent = reportsByProfile.values().stream().mapToInt(list ->
                        userCalories > list.stream().map(SportReport::getCaloriesBurned).mapToInt(num -> num).sum() ? 1 : 0)
                .summaryStatistics()
                .getAverage();
        double userTrainingMinutesBetterThanPercent = reportsByProfile.values().stream().mapToInt(list ->
                        userTrainingMinutes > (double) list.stream().map(SportReport::getTotalTimeSeconds).mapToInt(num -> num).sum() / 60 ? 1 : 0)
                .summaryStatistics()
                .getAverage();

        List<LocalDate> longestTrainingStreak = calculateLongestStreak(userReports);
        int userOverallLongestTrainingStreak = longestTrainingStreak.size();

        Map<String, Object> favouriteTrainingMap = findFavouriteTraining(userReports);
        int favouriteTrainingCount;
        Training favouriteTraining;
        if (favouriteTrainingMap.size() > 0) {
            favouriteTraining = (Training) favouriteTrainingMap.get("favouriteTraining");
            favouriteTrainingCount = (Integer) favouriteTrainingMap.get("trainingCount");
        } else {
            favouriteTrainingCount = 0;
            favouriteTraining = null;
        }
        ProfileStatisticsResponse response = ProfileStatisticsResponse
                .builder()
                .from(from)
                .to(to)
                .days((int) ChronoUnit.DAYS.between(from, to))
                .totalCalories(totalCalories)
                .totalTrainingMinutes(totalTrainingMinutes)
                .totalCaloriesAverage((double) totalCalories / noProfiles)
                .totalTrainingMinutesAverage(totalTrainingMinutes / noProfiles)
                .avgTrainingDays(avgTrainingDays)
                .userCalories(userCalories)
                .userTrainingMinutes(userTrainingMinutes)
                .userTrainingDays(userTrainingDays)
                .userCaloriesBetterThanPercent(userCaloriesBetterThanPercent)
                .userTrainingMinutesBetterThanPercent(userTrainingMinutesBetterThanPercent)
                .userOverallLongestTrainingStreak(userOverallLongestTrainingStreak)
                .userOverallLongestTrainingStreakDates(longestTrainingStreak)
                .userFavouriteTraining(favouriteTraining)
                .userFavouriteTrainingCount(favouriteTrainingCount)
                .build();
        return response;
    }

    private Map<String, Object> findFavouriteTraining(List<SportReport> userReports) {
        Map<Object, Long> trainingCountMap = new HashMap<>();
        for (SportReport report :
                userReports) {
            if (report.getTrainingList() != null) {
                Map<Object, Long> map = report.getTrainingList().stream().collect(Collectors.groupingBy(ReportTraining::getTraining, Collectors.counting()));
                map.forEach((k, v) -> trainingCountMap.merge(k, v, Long::sum));
            }
        }
        if (trainingCountMap.size() == 0)
            return new HashMap<>();
        Training favouriteTraining = (Training) Collections.max(trainingCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        Map<String, Object> result = new HashMap<>();
        result.put("favouriteTraining", favouriteTraining);
        result.put("trainingCount", trainingCountMap.get(favouriteTraining).intValue());
        return result;
    }

    private List<LocalDate> calculateLongestStreak(List<SportReport> userReports) {
        List<LocalDate> streak = new ArrayList<>();
        List<LocalDate> possibleStreak = new ArrayList<>();
        for (SportReport r :
                userReports) {
            LocalDate date = r.getReportDate();
            if (possibleStreak.size() == 0)
                possibleStreak.add(date);
            else if (possibleStreak.get(possibleStreak.size() - 1).plusDays(1).equals(date)) {
                possibleStreak.add(date);
            } else {
                if (possibleStreak.size() >= streak.size())
                    streak = new ArrayList<>(possibleStreak);
                possibleStreak.clear();
                possibleStreak.add(date);
            }
        }
        if (possibleStreak.size() >= streak.size())
            streak = new ArrayList<>(possibleStreak);

        return streak;
    }

    private void calculateReportDetails(SportReport re) {
        double weight = re.getReportOwner().getProfileCard().getWeight();
        re.getExerciseList().forEach(ex -> {
            ex.getExercise().setCaloriesBurned(ex.getExercise().countCaloriesPerHour(weight));
        });
        re.getTrainingList().forEach(tr -> {
            tr.getTraining().setCaloriesBurned(tr.getTraining().caloriesBurned(weight));
            tr.getTraining().getExerciseInTrainings().forEach(ex -> ex.setCaloriesBurned(ex.countCaloriesPerExerciseDuration(weight)));
        });
        re.preUpdate();
    }
}
