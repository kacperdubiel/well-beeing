package com.wellbeeing.wellbeeing.service.diet;
import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.Report;
import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ReportService {
    Report getReportById(UUID reportID) throws Exception;
    List<ReportProductDetail> getReportProductsByReportId(UUID reportId) throws Exception;
    List<Dish> getReportDishesByReportId(UUID reportId) throws NotFoundException;
    boolean deleteDishesFromReportByReportId(UUID reportI, List<UUID> dishes) throws NotFoundException;
    boolean deleteProductsFromReportByReportId(UUID reportId, List<UUID> productsIds); //TODO
    boolean deleteReportById(UUID reportId); //TODO
    Report updateReportByReportId(UUID reportId); //TODO
    Report addReportForProfileByProfileId(UUID profileId); //TODO
    Dish addDishToReportByReportId(UUID reportId); //TODO
    Product addProductToReportByReportId(UUID reportId); //TODO
    boolean updateReportDerivedElementsByReportId(UUID reportId); //TODO
    Map<String, Map<String, Double>> countDishDetailedElementsAmountsByReportId(UUID dishId); //TODO
    Report getReportByDateAndProfileId(UUID profileId); //TODO
    List<Report> getReportsByProfileId(UUID profileId); //TODO

}
