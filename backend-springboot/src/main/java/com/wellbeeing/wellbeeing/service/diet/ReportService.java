package com.wellbeeing.wellbeeing.service.diet;
import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.diet.Report;
import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ReportService {
    Report getReportById(UUID reportID) throws NotFoundException;
    List<ReportProductDetail> getReportProductsByReportId(UUID reportId) throws NotFoundException;
    List<Dish> getReportDishesByReportId(UUID reportId) throws NotFoundException;
    boolean deleteDishesFromReportByReportId(UUID reportId, List<UUID> dishes) throws NotFoundException;
    boolean deleteProductsFromReportByReportId(UUID reportId, List<UUID> productsIds) throws NotFoundException;
    boolean deleteReportById(UUID reportId) throws NotFoundException;
    Report addReportForProfileByProfileId(UUID profileId) throws NotFoundException, ConflictException;
    boolean addDishesToReportByReportId(List<UUID> dishesId, UUID reportId) throws NotFoundException;
    boolean addProductsToReportByReportId(List<ReportProductDetail> products, UUID reportId) throws NotFoundException;
    void updateReportDerivedElementsByReportId(UUID reportId) throws NotFoundException;
    Map<String, Map<String, Double>> countDetailedElementsAmountsByReportId(UUID reportId) throws NotFoundException;
    List<Report> getReportByDateAndProfileId(Date date, UUID profileId) throws NotFoundException;
    List<Report> getReportsByProfileId(UUID profileId) throws NotFoundException;
    boolean checkIfProfileReport(UUID profileId, UUID reportId);

}
