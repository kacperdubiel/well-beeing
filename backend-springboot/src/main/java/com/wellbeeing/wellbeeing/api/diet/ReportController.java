package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.Report;
import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.ReportService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReportController {
    private ReportService reportService;
    private UserService userService;

    @Autowired
    public ReportController(@Qualifier("reportService") ReportService reportService,
                            @Qualifier("userService") UserService userService){
        this.reportService = reportService;
        this.userService = userService;
    }

    @RequestMapping(path = "/report/{reportId}", method = RequestMethod.GET)
    public ResponseEntity<?> getReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Report report  = reportService.getReportById(reportId);
        if(!profileId.equals(report.getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        boolean reportDelStatus  = reportService.deleteReportById(reportId);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/dish", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDishesFromReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<UUID> dishes) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        boolean reportDelStatus  = reportService.deleteDishesFromReportByReportId(reportId, dishes);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/product", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductsFromReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<UUID> products) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        boolean reportDelStatus  = reportService.deleteProductsFromReportByReportId(reportId, products);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/dish", method = RequestMethod.POST)
    public ResponseEntity<?> addDishesToReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<UUID> dishes) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        boolean reportAddStatus  = reportService.addDishesToReportByReportId(dishes, reportId);
        return new ResponseEntity<>(reportAddStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/product", method = RequestMethod.POST)
    public ResponseEntity<?> addProductsToReportByReportId(Principal principal,
                                                                @PathVariable("reportId")UUID reportId,
                                                                @RequestBody @NonNull List<ReportProductDetail> products) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        boolean reportAddStatus  = reportService.addProductsToReportByReportId(products, reportId);
        return new ResponseEntity<>(reportAddStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> addReport(Principal principal) throws NotFoundException, ConflictException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Report newReport = reportService.addReportForProfileByProfileId(profileId);
        return new ResponseEntity<>(newReport, HttpStatus.OK);
    }

    @RequestMapping(path = "/report", method = RequestMethod.GET)
    public ResponseEntity<?> getReports(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        List<Report> reports = reportService.getReportsByProfileId(profileId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/details", method = RequestMethod.GET)
    public ResponseEntity<?> getReportDetailsByReportId(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        return new ResponseEntity<>(reportService.countDetailedElementsAmountsByReportId(reportId), HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/actualize", method = RequestMethod.GET)
    public ResponseEntity<?> updateReportDerivedByReportId(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        reportService.updateReportDerivedElementsByReportId(reportId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    /*@RequestMapping(path = "/report/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getReportDetailsByDate(Principal principal, @PathVariable("date") String date){
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(reportService.countDetailedElementsAmountsByReportId(reportId), HttpStatus.OK);
    }*/
}
