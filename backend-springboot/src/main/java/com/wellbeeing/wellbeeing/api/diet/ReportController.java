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
    public ResponseEntity<?> getReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(reportService.checkIfProfileReport(profileId, reportId)){
            return new ResponseEntity<>(new ErrorMessage("Access forbidden", "403"), HttpStatus.FORBIDDEN);
        }
        Report report  = reportService.getReportById(reportId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(reportService.checkIfProfileReport(profileId, reportId)){
            return new ResponseEntity<>(new ErrorMessage("Access forbidden", "403"), HttpStatus.FORBIDDEN);
        }
        boolean reportDelAns  = reportService.deleteReportById(reportId);
        return new ResponseEntity<>(reportDelAns, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/dish", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDishesFromReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<UUID> dishes) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(reportService.checkIfProfileReport(profileId, reportId)){
            return new ResponseEntity<>(new ErrorMessage("Access forbidden", "403"), HttpStatus.FORBIDDEN);
        }
        boolean reportDelAns  = reportService.deleteDishesFromReportByReportId(reportId, dishes);
        return new ResponseEntity<>(reportDelAns, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/product", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductsFromReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<UUID> products) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(reportService.checkIfProfileReport(profileId, reportId)){
            return new ResponseEntity<>(new ErrorMessage("Access forbidden", "403"), HttpStatus.FORBIDDEN);
        }
        boolean reportDelAns  = reportService.deleteProductsFromReportByReportId(reportId, products);
        return new ResponseEntity<>(reportDelAns, HttpStatus.OK);
    }

        @RequestMapping(path = "/report/{reportId}/dish", method = RequestMethod.POST)
    public ResponseEntity<?> addDishesToReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<UUID> dishes) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(reportService.checkIfProfileReport(profileId, reportId)){
            throw new ForbiddenException("Report access for report with id: " + reportId + " forbidden");
        }
        boolean reportDelAns  = reportService.addDishesToReportByReportId(dishes, reportId);
        return new ResponseEntity<>(reportDelAns, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/product", method = RequestMethod.POST)
    public ResponseEntity<?> addProductsToReportByReportId(Principal principal,
                                                                @PathVariable("reportId")UUID reportId,
                                                                @RequestBody @NonNull List<ReportProductDetail> products) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(reportService.checkIfProfileReport(profileId, reportId)){
            throw new ForbiddenException("Report access for report with id: " + reportId + " forbidden");
        }
        boolean reportDelAns  = reportService.addProductsToReportByReportId(products, reportId);
        return new ResponseEntity<>(reportDelAns, HttpStatus.OK);
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
        if(reportService.checkIfProfileReport(profileId, reportId)){
            throw new ForbiddenException("Report access for report with id: " + reportId + " forbidden");
        }
        return new ResponseEntity<>(reportService.countDetailedElementsAmountsByReportId(reportId), HttpStatus.OK);
    }

    /*@RequestMapping(path = "/report/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getReportDetailsByDate(Principal principal, @PathVariable("date") String date){
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(reportService.countDetailedElementsAmountsByReportId(reportId), HttpStatus.OK);
    }*/
}
