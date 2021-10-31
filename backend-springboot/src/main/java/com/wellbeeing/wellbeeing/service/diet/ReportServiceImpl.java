package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.diet.*;
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
    public ReportProductDetailDAO reportProductDetailDAO;
    public ReportDishDetailDAO reportDishDetailDAO;

    public DishService dishService;

    public ReportServiceImpl(@Qualifier("reportDAO") ReportDAO reportDAO,
                             @Qualifier("dishDAO") DishDAO dishDAO,
                             @Qualifier("productDAO") ProductDAO productDAO,
                             @Qualifier("dishService") DishService dishService,
                             @Qualifier("profileDAO") ProfileDAO profileDAO,
                             @Qualifier("reportProductDetailDAO") ReportProductDetailDAO reportProductDetailDAO,
                             @Qualifier("reportDishDetailDAO") ReportDishDetailDAO reportDishDetailDAO){
        this.dishDAO = dishDAO;
        this.reportDAO = reportDAO;
        this.productDAO = productDAO;
        this.dishService = dishService;
        this.profileDAO = profileDAO;
        this.reportProductDetailDAO = reportProductDetailDAO;
        this.reportDishDetailDAO = reportDishDetailDAO;

    }

    @Override
    public Report getReportById(UUID reportId) throws NotFoundException {
        Report report = reportDAO.findById(reportId).orElse(null);
        if(report != null)
            return report;
        throw new NotFoundException("Report with id: " + reportId +" not found");
    }

    @Override
    public List<ReportProductDetail> getReportProductsByReportId(UUID reportId) throws NotFoundException {
        return getReportById(reportId).getProductDetailsList();
    }

    @Override
    public List<ReportDishDetail> getReportDishesByReportId(UUID reportId) throws NotFoundException {
        return getReportById(reportId).getDishDetailsList();
    }

    @Override
    public boolean deleteDishesFromReportByReportId(UUID reportId, List<UUID> dishesIds) throws NotFoundException {
        List<ReportDishDetail> dishes = getReportDishesByReportId(reportId);
        for(ReportDishDetail rdd : dishes){
            if(dishesIds.contains(rdd.getId())){
                Objects.requireNonNull(reportDishDetailDAO.findById(rdd.getId()).orElse(null)).setReport(null);
                reportDishDetailDAO.save(rdd);
                reportDishDetailDAO.deleteById(rdd.getId());
            }
        }
        List<ReportDishDetail> newDishes = dishes.stream().
                filter(dish -> !dishesIds.contains(dish.getId())).collect(Collectors.toList());
        Report report = getReportById(reportId);
        report.setDishDetailsList(newDishes);
        reportDAO.save(report);
        updateReportDerivedElementsByReportId(reportId);
        return true;
    }

    @Override
    public boolean deleteProductsFromReportByReportId(UUID reportId, List<UUID> productsIds) throws NotFoundException {
        List<ReportProductDetail> products = getReportProductsByReportId(reportId);
        for(ReportProductDetail rpd : products){
            if(productsIds.contains(rpd.getId())){
                Objects.requireNonNull(reportProductDetailDAO.findById(rpd.getId()).orElse(null)).setReport(null);
                reportProductDetailDAO.save(rpd);
                reportProductDetailDAO.deleteById(rpd.getId());
            }
        }
        List<ReportProductDetail> newProducts = products.stream().
                filter(prod -> !productsIds.contains(prod.getId())).collect(Collectors.toList());
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
        if(reportDAO.findByReportDate(newReport.getReportDate()).orElse(null) != null){
            throw new ConflictException("Report for this day already exists");
        }
        reportDAO.save(newReport);
        return newReport;
    }

    @Override
    public boolean addDishesToReportByReportId(List<ReportDishDetail> dishes, UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        for (ReportDishDetail dish : dishes) {
            dish.setReport(report);
            reportDishDetailDAO.save(dish);
            report.getDishDetailsList().add(dish);
        }
        reportDAO.save(report);
        return true;
    }

    @Override
    public boolean addProductsToReportByReportId(List<ReportProductDetail> products, UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        for (ReportProductDetail prod : products) {
                prod.setReport(report);
                reportProductDetailDAO.save(prod);
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
        for(ReportDishDetail d : report.getDishDetailsList()){
            calories += d.getDish().getDerivedCalories() * d.getPortions();
            proteins += d.getDish().getDerivedProteins() * d.getPortions();
            carbs += d.getDish().getDerivedCarbohydrates() * d.getPortions();
            fats += d.getDish().getDerivedFats() * d.getPortions();
        }
        for(ReportProductDetail p : report.getProductDetailsList()){
            calories += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getCaloriesPerHundredGrams()/100;
            proteins += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getProteinsPerHundredGrams()/100;
            fats += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getFatsPerHundredGrams()/100;
            carbs += p.getAmount() * p.getMeasureType().getNumberOfGrams() * p.getProduct().getCarbohydratesPerHundredGrams()/100;
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
        //report.getDishDetailsList().forEach(d -> dishProdDetails.addAll(d.getDish().getDishProductDetails()));
        for(ReportDishDetail rdd : report.getDishDetailsList()){
            for(int i = 0; i < rdd.getPortions(); i++){
                dishProdDetails.addAll(rdd.getDish().getDishProductDetails());
            }
        }
        List<ReportProductDetail> reportProdDetails = report.getProductDetailsList();

        Map<String, Double> reportMacroDetails = new HashMap<>();
        Map<String, Double> reportVitaminDetails = new HashMap<>();
        Map<String, Double> reportMineralDetails = new HashMap<>();

        countProdDetails(dishProdDetails, reportMacroDetails, reportVitaminDetails, reportMineralDetails);
        countProdDetails(reportProdDetails, reportMacroDetails, reportVitaminDetails, reportMineralDetails);

        result.put("macroDetailsGram", reportMacroDetails);
        result.put("vitaminsMicroGram", reportVitaminDetails);
        result.put("mineralsMilliGram", reportMineralDetails);
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
                                    actProductGrams * m.getMeasureType().getNumberOfGrams() / EWeightMeasure.MILLI_GRAM.getNumberOfGrams()
                                    :
                                    reportMineralDetails.get(m.getMineralType().toString())
                                            + actProductGrams * m.getMeasureType().getNumberOfGrams() / EWeightMeasure.MILLI_GRAM.getNumberOfGrams())));
            actualProductVitaminDetails
                    .forEach(v -> reportVitaminDetails.put(v.getVitaminType().toString(), (
                            reportVitaminDetails.get(v.getVitaminType().toString()) == null
                                    ?
                                    actProductGrams * v.getMeasureType().getNumberOfGrams() / EWeightMeasure.MICRO_GRAM.getNumberOfGrams()
                                    :
                                    reportVitaminDetails.get(v.getVitaminType().toString())
                                            + actProductGrams * v.getMeasureType().getNumberOfGrams() / EWeightMeasure.MICRO_GRAM.getNumberOfGrams())));
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
}
