package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.sport.ProfileStatisticsResponse;
import com.wellbeeing.wellbeeing.domain.sport.ReportExercise;
import com.wellbeeing.wellbeeing.domain.sport.ReportTraining;
import com.wellbeeing.wellbeeing.domain.sport.SportReport;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.sport.SportReportService;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/report")
public class SportReportController {
    private final SportReportService sportReportService;
    private final UserService userService;
    private final ProfileService profileService;
    private final ProfileConnectionService profileConnectionService;
    private UserDAO userDAO;


    public SportReportController(@Qualifier("sportReportService") SportReportService sportReportService,
                                 @Qualifier("userService") UserService userService,
                                 @Qualifier("profileService") ProfileService profileService,
                                 @Qualifier("profileConnectionService") ProfileConnectionService profileConnectionService) {
        this.sportReportService = sportReportService;
        this.userService = userService;
        this.profileService = profileService;
        this.profileConnectionService = profileConnectionService;
    }

    @GetMapping(path = "/{reportId}")
    public ResponseEntity<?> getSportReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        SportReport report = sportReportService.getSportReportById(reportId);
        if (!profileId.equals(report.getReportOwner().getId())) {
            throw new ForbiddenException("Access to sport report with id" + reportId + " forbidden");
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{reportId}")
    public ResponseEntity<?> deleteSportReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if (!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())) {
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        boolean reportDelStatus = sportReportService.deleteSportReportById(reportId);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{reportId}/exercise")
    public ResponseEntity<?> deleteExercisesFromSportReportByReportId(Principal principal,
                                                                      @PathVariable("reportId") UUID reportId,
                                                                      @RequestBody @NonNull List<Long> exercises) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if (!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())) {
            throw new ForbiddenException("Access to sport report with id" + reportId + " forbidden");
        }
        SportReport reportDelStatus = sportReportService.deleteExercisesFromReportByReportId(reportId, exercises);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{reportId}/training")
    public ResponseEntity<?> deleteTrainingsFromSportReportByReportId(Principal principal,
                                                                      @PathVariable("reportId") UUID reportId,
                                                                      @RequestBody @NonNull List<Long> trainings) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if (!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())) {
            throw new ForbiddenException("Access to sport report with id" + reportId + " forbidden");
        }
        SportReport reportDelStatus = sportReportService.deleteTrainingsFromReportByReportId(reportId, trainings);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @PostMapping(path = "/{reportId}/exercise")
    public ResponseEntity<?> addExercisesToReportByReportId(Principal principal,
                                                            @PathVariable("reportId") UUID reportId,
                                                            @RequestBody @NonNull List<ReportExercise> exercises) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if (!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())) {
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        SportReport reportAddStatus = sportReportService.addExercisesToReportByReportId(exercises, reportId);
        return new ResponseEntity<>(reportAddStatus, HttpStatus.OK);
    }

    @PostMapping(path = "/{reportId}/training")
    public ResponseEntity<?> addTrainingsToReportByReportId(Principal principal,
                                                            @PathVariable("reportId") UUID reportId,
                                                            @RequestBody @NonNull List<ReportTraining> trainings) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if (!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())) {
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        SportReport reportAddStatus = sportReportService.addTrainingsToReportByReportId(trainings, reportId);
        return new ResponseEntity<>(reportAddStatus, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addReport(Principal principal) throws NotFoundException, ConflictException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        SportReport newReport = sportReportService.addSportReportForProfileByProfileId(profileId);
        return new ResponseEntity<>(newReport, HttpStatus.OK);
    }

    @PostMapping(path = "/date")
    public ResponseEntity<?> addReportOnDate(@RequestBody @NonNull LocalDate date, Principal principal) throws NotFoundException, ConflictException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        SportReport newReport = sportReportService.addSportReportForProfileByDateAndProfileId(date, profileId);
        return new ResponseEntity<>(newReport, HttpStatus.OK);
    }

    @GetMapping(path = "/{date}/date")
    public ResponseEntity<?> getReportByDate(Principal principal,
                                             @PathVariable("date") LocalDate date) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(sportReportService.getSportReportByDateAndProfileId(date, profileId), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getReportByMonthAndYear(Principal principal,
                                                     @RequestParam(value = "month", defaultValue = "1") int month,
                                                     @RequestParam(value = "year", defaultValue = "2020") int year) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(sportReportService.getSportReportsByMonthAndProfileId(month, year, profileId), HttpStatus.OK);
    }

    @RequestMapping(path = "/profile/{profileId}/from/{date_from}/to/{date_to}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileReportByDate(Principal principal,
                                                    @PathVariable("profileId") UUID reportOwnerId,
                                                    @PathVariable("date_from")
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate from,
                                                    @PathVariable("date_to")
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate to)
            throws NotFoundException, ForbiddenException {
        UUID authorizedUserId = userService.findUserIdByUsername(principal.getName());
        Profile authorizedUser = profileService.getProfileById(authorizedUserId);
        Profile reportOwner = profileService.getProfileById(reportOwnerId);

        if (!reportOwnerId.equals(authorizedUserId)) {
            ProfileConnection pConnResult = profileConnectionService.getProfileConnectionByProfileAndConnectedWithAndType(
                    reportOwner, authorizedUser, EConnectionType.WITH_DOCTOR);

            if (pConnResult == null || !pConnResult.isAccepted()) {
                throw new ForbiddenException("You do not have access rights to do that!");
            }
        }

        List<SportReport> sportReports = sportReportService.getSportReportsByProfileIdAndDate(reportOwnerId, from, to);
        return new ResponseEntity<>(sportReports, HttpStatus.OK);
    }

    @GetMapping(path = "/stats")
    public ResponseEntity<?> getUserStatistics(@RequestParam("date_from")
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                               @RequestParam("date_to")
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                               Principal principal) throws NotFoundException, ConflictException {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        ProfileStatisticsResponse statistics = sportReportService.getStatisticsByProfileIdAndDate(userId, from, to);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }


}
