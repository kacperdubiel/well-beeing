package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.ReportExercise;
import com.wellbeeing.wellbeeing.domain.sport.ReportTraining;
import com.wellbeeing.wellbeeing.domain.sport.SportReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SportReportService {
    SportReport getSportReportById(UUID reportID) throws NotFoundException;

    //        List<ReportProductDetail> getReportProductsByReportId(UUID reportId) throws NotFoundException;
    SportReport getSportReportByDateAndOwner(LocalDate date, Profile owner) throws NotFoundException;

    List<ReportExercise> getReportExercisesByReportId(UUID reportId) throws NotFoundException;

    SportReport deleteExercisesFromReportByReportId(UUID reportId, List<Long> exercisesIds) throws NotFoundException;

    boolean deleteSportReportById(UUID reportId) throws NotFoundException;

    SportReport addSportReportForProfileByProfileId(UUID profileId) throws NotFoundException, ConflictException;

    SportReport addSportReportForProfileByDateAndProfileId(LocalDate reportDate, UUID profileId) throws NotFoundException, ConflictException;

    SportReport addExercisesToReportByReportId(List<ReportExercise> dishes, UUID reportId) throws NotFoundException;

    //        Report addProductsToReportByReportId(List<ReportProductDetail> products, UUID reportId) throws NotFoundException;
//        void updateReportDerivedElementsByReportId(UUID reportId) throws NotFoundException;
    Map<String, Map<String, Double>> countDetailedElementsAmountsByReportId(UUID reportId) throws NotFoundException;

    List<SportReport> getSportReportsByProfileId(UUID profileId) throws NotFoundException;

    List<SportReport> getSportReportByDateAndProfileId(LocalDate date, UUID profileId) throws NotFoundException;

    List<SportReport> getSportReportsByMonthAndProfileId(int month, int year, UUID profileId) throws NotFoundException;

    List<SportReport> getSportReportsByProfileIdAndDate(UUID profileId, LocalDate start, LocalDate end) throws NotFoundException;

    SportReport deleteTrainingsFromReportByReportId(UUID reportId, List<Long> trainings) throws NotFoundException;

    SportReport addTrainingsToReportByReportId(List<ReportTraining> trainings, UUID reportId) throws NotFoundException;
}
