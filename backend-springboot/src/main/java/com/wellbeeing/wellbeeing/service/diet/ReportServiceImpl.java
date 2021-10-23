package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.diet.DishDAO;
import com.wellbeeing.wellbeeing.repository.diet.ProductDAO;
import com.wellbeeing.wellbeeing.repository.diet.ReportDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    public ReportDAO reportDAO;
    public ProfileDAO profileDAO;
    public DishDAO dishDAO;
    public ProductDAO productDAO;

    public DishService dishService;

    public ReportServiceImpl(@Qualifier("reportDAO") ReportDAO reportDAO,
                             @Qualifier("dishDAO") DishDAO dishDAO,
                             @Qualifier("productDAO") ProductDAO productDAO,
                             @Qualifier("dishService") DishService dishService,
                             @Qualifier("profileDAO") ProfileDAO profileDAO){
        this.dishDAO = dishDAO;
        this.reportDAO = reportDAO;
        this.productDAO = productDAO;
        this.dishService = dishService;
        this.profileDAO = profileDAO;

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
        updateReportDerivedElementsByReportId(reportId);
        return true;
    }

    @Override
    public boolean deleteProductsFromReportByReportId(UUID reportId, List<UUID> productsIds) throws NotFoundException {
        List<ReportProductDetail> products = getReportProductsByReportId(reportId);
        List<ReportProductDetail> newProducts = products.stream().
                filter(prod -> !productsIds.contains(prod.getProduct().getId())).collect(Collectors.toList());
        Report report = getReportById(reportId);
        report.setProductDetailsList(newProducts);
        reportDAO.save(report);
        updateReportDerivedElementsByReportId(reportId);
        return true;
    }

    @Override
    public boolean deleteReportById(UUID reportId) throws NotFoundException {
        Report report = reportDAO.findById(reportId).orElse(null);
        if(report != null)
            return true;
        else throw new NotFoundException("Report with id: "+ reportId + " not found");
    }

    @Override
    public Report addReportForProfileByProfileId(UUID profileId) throws NotFoundException, ConflictException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile == null)
            throw new NotFoundException("Profile with id:" + profileId + " not found");
        Report newReport = Report.builder().reportDate(new Date()).reportOwner(profile).build();
        if(reportDAO.findByReportDate(newReport.getReportDate()).orElse(null) == null){
            throw new ConflictException("Report for this day already exists");
        }
        reportDAO.save(newReport);
        return newReport;
    }

    @Override
    public boolean addDishesToReportByReportId(List<UUID> dishesId, UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        for (UUID dishId : dishesId) {
            dishDAO.findById(dishId).ifPresent(dish -> report.getDishList().add(dish));
        }
        reportDAO.save(report);
        return true;
    }

    @Override
    public boolean addProductsToReportByReportId(List<ReportProductDetail> products, UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        for (ReportProductDetail prod : products) {
                report.getProductDetailsList().add(prod);
        }
        reportDAO.save(report);
        return true;
    }


    @Override
    public void updateReportDerivedElementsByReportId(UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        double calories = 0;
        double proteins = 0;
        double carbs = 0;
        double fats = 0;
        for(Dish d : report.getDishList()){
            calories += d.getDerivedCalories();
            proteins += d.getDerivedProteins();
            carbs += d.getDerivedCarbohydrates();
            fats += d.getDerivedFats();
        }
        for(ReportProductDetail p : report.getProductDetailsList()){
            calories += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getCaloriesPerHundredGrams();
            proteins += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getProteinsPerHundredGrams();
            fats += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getFatsPerHundredGrams();
            carbs += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getCarbohydratesPerHundredGrams();
        }
        report.setDerivedCalories(calories);
        report.setDerivedCarbohydrates(carbs);
        report.setDerivedFats(fats);
        report.setDerivedProteins(proteins);
        reportDAO.save(report);
    }

    @Override
    public Map<String, Map<String, Double>> countDetailedElementsAmountsByReportId(UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        Map<String, Map<String, Double>> result = new HashMap<>();

        List<DishProductDetail> dishProdDetails = new ArrayList<>();
        report.getDishList().forEach(d -> dishProdDetails.addAll(d.getDishProductDetails()));
        List<ReportProductDetail> reportProdDetails = report.getProductDetailsList();

        Map<String, Double> reportMacroDetails = new HashMap<>();
        Map<String, Double> reportVitaminDetails = new HashMap<>();
        Map<String, Double> reportMineralDetails = new HashMap<>();

        countProdDetails(dishProdDetails, reportMacroDetails, reportVitaminDetails, reportMineralDetails);
        countProdDetails(reportProdDetails, reportMacroDetails, reportVitaminDetails, reportMineralDetails);

        result.put("macroDetails", reportMacroDetails);
        result.put("vitamins", reportVitaminDetails);
        result.put("minerals", reportMineralDetails);
        return result;
    }

    private void countProdDetails(List<? extends ProductDetail> details, Map<String, Double> reportMacroDetails,
                      Map<String, Double> reportVitaminDetails, Map<String, Double> reportMineralDetails){
        for(ProductDetail dpd : details){
            double actProductGrams = dpd.getAmount() * dpd.getMeasureType().getNumberOfGrams();

            List<MacroDetail> actualProductMacroDetails = dpd.getProduct().getMacroDetails();
            List<MineralDetail> actualProductMineralDetails = dpd.getProduct().getMineralDetails();
            List<VitaminDetail> actualProductVitaminDetails = dpd.getProduct().getVitaminDetails();

            actualProductMacroDetails
                    .forEach(md -> reportMacroDetails.put(md.getDetailedMacroType().toString(), (
                            reportMacroDetails.get(md.getDetailedMacroType().toString()) == null
                                    ?
                                    actProductGrams * md.getMeasureType().getNumberOfGrams()
                                    :
                                    reportMacroDetails.get(md.getDetailedMacroType().toString())
                                            + actProductGrams * md.getMeasureType().getNumberOfGrams())));
            actualProductMineralDetails
                    .forEach(m -> reportMineralDetails.put(m.getMineralType().toString(), (
                            reportMineralDetails.get(m.getMineralType().toString()) == null
                                    ?
                                    actProductGrams * m.getMeasureType().getNumberOfGrams()
                                    :
                                    reportMineralDetails.get(m.getMineralType().toString())
                                            + actProductGrams * m.getMeasureType().getNumberOfGrams())));
            actualProductVitaminDetails
                    .forEach(v -> reportVitaminDetails.put(v.getVitaminType().toString(), (
                            reportVitaminDetails.get(v.getVitaminType().toString()) == null
                                    ?
                                    actProductGrams * v.getMeasureType().getNumberOfGrams()
                                    :
                                    reportVitaminDetails.get(v.getVitaminType().toString())
                                            + actProductGrams * v.getMeasureType().getNumberOfGrams())));
        }
    }


    @Override
    public List<Report> getReportByDateAndProfileId(Date date, UUID profileId) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile == null)
            throw new NotFoundException("Profile with id: " + profileId +" not found");
        return profile.getDietReports().stream().filter
                (r -> r.getReportDate().equals(date)).collect(Collectors.toList());
    }

    @Override
    public List<Report> getReportsByProfileId(UUID profileId) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile == null)
            throw new NotFoundException("Profile with id: " + profileId + " not found");
        return profile.getDietReports();
    }

    @Override
    public boolean checkIfProfileReport(UUID profileId, UUID reportId) {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile != null){
            for(Report r : profile.getDietReports()){
                if (r.getId().equals(reportId)) {
                   return false;
                }
            }
            return true;
        }
        return true;
    }
}
