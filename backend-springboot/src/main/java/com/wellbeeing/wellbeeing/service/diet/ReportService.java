package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Report;
import com.wellbeeing.wellbeeing.domain.diet.ReportDishDetail;
import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ReportService {
    Report getReportById(UUID reportID) throws NotFoundException;
    List<ReportProductDetail> getReportProductsByReportId(UUID reportId) throws NotFoundException;
    List<ReportDishDetail> getReportDishesByReportId(UUID reportId) throws NotFoundException;
    Report deleteDishesFromReportByReportId(UUID reportId, List<UUID> dishes) throws NotFoundException;
    Report deleteProductsFromReportByReportId(UUID reportId, List<UUID> productsIds) throws NotFoundException;
    boolean deleteReportById(UUID reportId) throws NotFoundException;
    Report addReportForProfileByProfileId(UUID profileId) throws NotFoundException, ConflictException;
    Report addDishesToReportByReportId(List<ReportDishDetail> dishes, UUID reportId) throws NotFoundException;
    Report addProductsToReportByReportId(List<ReportProductDetail> products, UUID reportId) throws NotFoundException;
    void updateReportDerivedElementsByReportId(UUID reportId) throws NotFoundException;
    Map<String, Map<String, Double>> countDetailedElementsAmountsByReportId(UUID reportId) throws NotFoundException;
    List<Report> getReportByDateAndProfileId(LocalDate date, UUID profileId) throws NotFoundException;
    List<Report> getReportsByProfileId(UUID profileId) throws NotFoundException;
    List<Report> getReportsByMonthAndProfileId(int month, int year, UUID profileId) throws NotFoundException;
}
