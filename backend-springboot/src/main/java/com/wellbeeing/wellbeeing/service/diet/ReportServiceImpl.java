package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.diet.dish.DishProductDetail;
import com.wellbeeing.wellbeeing.domain.diet.report.Report;
import com.wellbeeing.wellbeeing.domain.diet.report.ReportDishDetail;
import com.wellbeeing.wellbeeing.domain.diet.report.ReportProductDetail;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.diet.report.ReportDAO;
import com.wellbeeing.wellbeeing.repository.diet.report.ReportDishDetailDAO;
import com.wellbeeing.wellbeeing.repository.diet.report.ReportProductDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EntityManager entityManager;

    private ReportDAO reportDAO;
    private ProfileDAO profileDAO;
    private ReportProductDetailDAO reportProductDetailDAO;
    private ReportDishDetailDAO reportDishDetailDAO;
    private ElementDetailService elementDetailService;


    public ReportServiceImpl(@Qualifier("reportDAO") ReportDAO reportDAO,
                             @Qualifier("profileDAO") ProfileDAO profileDAO,
                             @Qualifier("reportProductDetailDAO") ReportProductDetailDAO reportProductDetailDAO,
                             @Qualifier("reportDishDetailDAO") ReportDishDetailDAO reportDishDetailDAO,
                             @Qualifier("elementDetailService") ElementDetailService elementDetailService){
        this.reportDAO = reportDAO;
        this.profileDAO = profileDAO;
        this.reportProductDetailDAO = reportProductDetailDAO;
        this.reportDishDetailDAO = reportDishDetailDAO;
        this.elementDetailService = elementDetailService;

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
    public Report deleteDishesFromReportByReportId(UUID reportId, List<UUID> dishesIds) throws NotFoundException {
        Report report = getReportById(reportId);
        List<ReportDishDetail> dishes = report.getDishDetailsList();
        for(ReportDishDetail rdd : dishes){
            if(dishesIds.contains(rdd.getId())){
                reportDishDetailDAO.deleteById(rdd.getId());
            }
        }
        entityManager.clear();
        Report reportUpdated = getReportById(report.getId());
        reportUpdated.setDerived();
        return reportDAO.save(reportUpdated);
    }

    @Override
    public Report deleteProductsFromReportByReportId(UUID reportId, List<UUID> productsIds) throws NotFoundException {
        Report report = getReportById(reportId);
        List<ReportProductDetail> products = report.getProductDetailsList();
        for(ReportProductDetail rpd : products){
            if(productsIds.contains(rpd.getId())){
                reportProductDetailDAO.deleteById(rpd.getId());
            }
        }
        entityManager.clear();
        Report reportUpdated = getReportById(report.getId());
        reportUpdated.setDerived();
        return reportDAO.save(reportUpdated);
    }

    @Override
    public boolean deleteReportById(UUID reportId) throws NotFoundException {
        Report report = reportDAO.findById(reportId).orElse(null);
        if(report != null) {
            reportDAO.deleteById(reportId);
            return true;
        }
        else throw new NotFoundException("Report with id: "+ reportId + " not found");
    }

    @Override
    public Report addReportForProfileByProfileId(UUID profileId, LocalDate date) throws NotFoundException, ConflictException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile == null)
            throw new NotFoundException("Profile with id:" + profileId + " not found");
        Report newReport = Report.builder()
                .reportDate(date)
                .reportOwner(profile)
                .derivedNutritionalValues(new NutritionalValueDerivedData())
                .build();
        if(reportDAO.findByReportDateAndReportOwnerId(newReport.getReportDate(), profileId).orElse(null) != null){
            throw new ConflictException("Report for this day already exists");
        }
        reportDAO.save(newReport);
        return newReport;
    }

    @Override
    public Report addDishesToReportByReportId(List<ReportDishDetail> dishes, UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        for (ReportDishDetail dish : dishes) {
            dish.setReport(report);
            dish.setDerivedNutritionalValues(new NutritionalValueDerivedData());
            ReportDishDetail rdd = reportDishDetailDAO.saveAndFlush(dish);
            entityManager.clear();
            ReportDishDetail newDish = reportDishDetailDAO.findById(rdd.getId()).orElse(null);
            if(newDish != null){
                newDish.setDerived();
                reportDishDetailDAO.save(newDish);
            }
        }
        report = getReportById(reportId);
        report.setDerived();
        return reportDAO.save(report);
    }


    @Override
    public Report addProductsToReportByReportId(List<ReportProductDetail> products, UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        for (ReportProductDetail prod : products) {
            prod.setReport(report);
            prod.setDerivedNutritionalValues(new NutritionalValueDerivedData());
            ReportProductDetail rpd = reportProductDetailDAO.saveAndFlush(prod);
            entityManager.clear();
            ReportProductDetail newProd = reportProductDetailDAO.findById(rpd.getId()).orElse(null);
            if(newProd != null){
                newProd.setDerived();
                reportProductDetailDAO.save(newProd);
            }
        }
        report = getReportById(reportId);
        report.setDerived();
        return reportDAO.save(report);
    }

    @Override
    public List<Report> getReportByDateAndProfileId(LocalDate date, UUID profileId) throws NotFoundException {
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
    public List<Report> getReportsByMonthAndProfileId(int month, int year, UUID profileId) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile == null)
            throw new NotFoundException("Profile with id: " + profileId + " not found");
        return profile.getDietReports().stream().filter
                (r -> r.getReportDate().getMonth().getValue() == month && r.getReportDate().getYear() == year)
                .sorted(Comparator.comparing(Report::getReportDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> getReportsByProfileIdAndDate(UUID profileId, LocalDate start, LocalDate end) throws NotFoundException {
        Profile profile = profileDAO.findById(profileId).orElse(null);
        if(profile == null)
            throw new NotFoundException("Profile with id: " + profileId + " not found");
        return reportDAO.findByReportOwnerIdAndReportDateBetweenOrderByReportDate(profileId, start, end);
    }

    @Override
    public void updateReportDerivedElementsByReportId(UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        report.setDerived();
        reportDAO.save(report);
    }

    @Override
    public Map<String, Map<String, Double>> countDetailedElementsAmountsByReportId(UUID reportId) throws NotFoundException {
        Report report = getReportById(reportId);
        Map<String, Map<String, Double>> result = new HashMap<>();

        List<DishProductDetail> dishProdDetails = new ArrayList<>();
        for(ReportDishDetail rdd : report.getDishDetailsList()){
            for(int i = 0; i < rdd.getPortions(); i++){
                dishProdDetails.addAll(rdd.getDish().getDishProductDetails());
            }
        }
        List<ReportProductDetail> reportProdDetails = report.getProductDetailsList();

        Map<String, Double> reportMacroDetails = new HashMap<>();
        Map<String, Double> reportVitaminDetails = new HashMap<>();
        Map<String, Double> reportMineralDetails = new HashMap<>();

        elementDetailService.countProdDetails(dishProdDetails, reportMacroDetails, reportVitaminDetails, reportMineralDetails);
        elementDetailService.countProdDetails(reportProdDetails, reportMacroDetails, reportVitaminDetails, reportMineralDetails);

        result.put("macroDetailsGram", reportMacroDetails);
        result.put("vitaminsMicroGram", reportVitaminDetails);
        result.put("mineralsMilliGram", reportMineralDetails);
        return result;
    }
}
