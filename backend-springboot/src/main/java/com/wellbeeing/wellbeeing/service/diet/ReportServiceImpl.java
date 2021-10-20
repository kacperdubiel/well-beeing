package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.Report;
import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import com.wellbeeing.wellbeeing.repository.diet.DishDAO;
import com.wellbeeing.wellbeeing.repository.diet.ProductDAO;
import com.wellbeeing.wellbeeing.repository.diet.ReportDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    public ReportDAO reportDAO;
    public DishDAO dishDAO;
    public ProductDAO productDAO;

    public DishService dishService;

    public ReportServiceImpl(@Qualifier("reportDAO") ReportDAO reportDAO,
                             @Qualifier("dishDAO") DishDAO dishDAO,
                             @Qualifier("productDAO") ProductDAO productDAO,
                             @Qualifier("dishService") DishService dishService){
        this.dishDAO = dishDAO;
        this.reportDAO = reportDAO;
        this.productDAO = productDAO;
        this.dishService = dishService;

    }

    @Override
    public Report getReportById(UUID reportId) throws NotFoundException {
        Report report = reportDAO.findById(reportId).orElse(null);
        if(report != null)
            return null;
        throw new NotFoundException("Report not found");
    }

    @Override
    public List<ReportProductDetail> getReportProductsByReportId(UUID reportId) throws NotFoundException {
        return getReportById(reportId).getProductDetailsList();
    }

    @Override
    public List<Dish> getReportDishesByReportId(UUID reportId) throws NotFoundException {
        return getReportById(reportId).getDishList();
    }

    @Override
    public boolean deleteDishesFromReportByReportId(UUID reportId, List<UUID> dishesIds) throws NotFoundException {
        List<Dish> dishes = getReportDishesByReportId(reportId);
        List<Dish> newDishes = dishes.stream().filter(dish -> !dishesIds.contains(dish.getId())).collect(Collectors.toList());
        Report report = getReportById(reportId);
        report.setDishList(newDishes);
        reportDAO.save(report);
        return true;
    }

    @Override
    public boolean deleteProductsFromReportByReportId(UUID reportId, List<UUID> productsIds) {
        return false;
    }

    @Override
    public boolean deleteReportById(UUID reportId) {
        return false;
    }

    @Override
    public Report updateReportByReportId(UUID reportId) {
        return null;
    }

    @Override
    public Report addReportForProfileByProfileId(UUID profileId) {
        return null;
    }

    @Override
    public Dish addDishToReportByReportId(UUID reportId) {
        return null;
    }

    @Override
    public Product addProductToReportByReportId(UUID reportId) {
        return null;
    }

    @Override
    public boolean updateReportDerivedElementsByReportId(UUID reportId) {
        return false;
    }

    @Override
    public Map<String, Map<String, Double>> countDishDetailedElementsAmountsByReportId(UUID dishId) {
        return null;
    }

    @Override
    public Report getReportByDateAndProfileId(UUID profileId) {
        return null;
    }

    @Override
    public List<Report> getReportsByProfileId(UUID profileId) {
        return null;
    }
}
