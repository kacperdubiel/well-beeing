package com.wellbeeing.wellbeeing.api.diet.api;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.report.Report;
import com.wellbeeing.wellbeeing.domain.diet.report.ReportDishDetail;
import com.wellbeeing.wellbeeing.domain.diet.report.ReportProductDetail;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.api.diet.message.CreateReportRequest;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.ReportService;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReportController {
    private ReportService reportService;
    private UserService userService;
    private ProfileService profileService;
    private ProfileConnectionService profileConnectionService;

    @Autowired
    public ReportController(@Qualifier("reportService") ReportService reportService,
                            @Qualifier("userService") UserService userService,
                            @Qualifier("profileConnectionService") ProfileConnectionService profileConnectionService,
                            @Qualifier("profileService") ProfileService profileService){
        this.reportService = reportService;
        this.userService = userService;
        this.profileConnectionService = profileConnectionService;
        this.profileService = profileService;
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
        if(!profileId.equals(reportService.getReportById(reportId).getReportOwner().getId())){
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
        if(!profileId.equals(reportService.getReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        Report reportDelStatus  = reportService.deleteDishesFromReportByReportId(reportId, dishes);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/product", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductsFromReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<UUID> products) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        Report reportDelStatus  = reportService.deleteProductsFromReportByReportId(reportId, products);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/dish", method = RequestMethod.POST)
    public ResponseEntity<?> addDishesToReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<ReportDishDetail> dishes) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        Report reportAddStatus  = reportService.addDishesToReportByReportId(dishes, reportId);
        return new ResponseEntity<>(reportAddStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{reportId}/product", method = RequestMethod.POST)
    public ResponseEntity<?> addProductsToReportByReportId(Principal principal,
                                                                @PathVariable("reportId")UUID reportId,
                                                                @RequestBody @NonNull List<ReportProductDetail> products) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        Report reportAddStatus  = reportService.addProductsToReportByReportId(products, reportId);
        return new ResponseEntity<>(reportAddStatus, HttpStatus.OK);
    }

    @RequestMapping(path = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> addReport(Principal principal,
                                       @RequestBody @NonNull CreateReportRequest createReportRequest) throws NotFoundException, ConflictException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Report newReport = reportService.addReportForProfileByProfileId(profileId, createReportRequest.getReportDate());
        return new ResponseEntity<>(newReport, HttpStatus.OK);
    }

    /*@RequestMapping(path = "/report", method = RequestMethod.GET)
    public ResponseEntity<?> getReports(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        List<Report> reports = reportService.getReportsByProfileId(profileId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }*/

    @RequestMapping(path = "/report/{reportId}/details", method = RequestMethod.GET)
    public ResponseEntity<?> getReportDetailsByReportId(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        Report report = reportService.getReportById(reportId);
        Profile owner = report.getReportOwner();

        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Profile userProfile = profileService.getProfileById(profileId);
        ProfileConnection pc = profileConnectionService.getProfileConnectionByProfileAndConnectedWithAndTypeAndIsAccepted(owner, userProfile, EConnectionType.WITH_DIETICIAN);

        if(pc != null || userProfile.getId().equals(owner.getId())){
            return new ResponseEntity<>(reportService.countDetailedElementsAmountsByReportId(reportId), HttpStatus.OK);
        }
        throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
    }

    @RequestMapping(path = "/report/{reportId}/actualize", method = RequestMethod.GET)
    public ResponseEntity<?> updateReportDerivedByReportId(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(reportService.getReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        reportService.updateReportDerivedElementsByReportId(reportId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/{date}/date", method = RequestMethod.GET)
    public ResponseEntity<?> getReportByDate(Principal principal,
                                             @PathVariable("date") LocalDate date) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(reportService.getReportByDateAndProfileId(date, profileId), HttpStatus.OK);
    }

    @RequestMapping(path = "/report", method = RequestMethod.GET)
    public ResponseEntity<?> getReportByMonthAndYear(Principal principal,
                                                     @RequestParam(value = "month", defaultValue = "1" ) int month,
                                                     @RequestParam(value = "year", defaultValue = "2020") int year) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(reportService.getReportsByMonthAndProfileId(month, year, profileId), HttpStatus.OK);
    }

    @RequestMapping(path = "/report/profile/{profileId}/from/{date_from}/to/{date_to}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileReportByDate(Principal principal,
                                                    @PathVariable("profileId") UUID reportOwnerId,
                                                    @PathVariable("date_from")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate from,
                                                    @PathVariable("date_to")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate to)
            throws NotFoundException, ForbiddenException
    {
        UUID authorizedUserId = userService.findUserIdByUsername(principal.getName());
        Profile authorizedUser = profileService.getProfileById(authorizedUserId);
        Profile reportOwner = profileService.getProfileById(reportOwnerId);

        if(!reportOwnerId.equals(authorizedUserId)){
            ProfileConnection pConnResult = profileConnectionService.getProfileConnectionByProfileAndConnectedWithAndType(
                    reportOwner, authorizedUser, EConnectionType.WITH_DOCTOR);

            if(pConnResult == null || !pConnResult.isAccepted()){
                throw new ForbiddenException("You do not have access rights to do that!");
            }
        }

        List<Report> reports = reportService.getReportsByProfileIdAndDate(reportOwnerId, from, to);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @RequestMapping(path = "/report/profile/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileReportByMonthAndYear(Principal principal,
                                                     @PathVariable("profileId") UUID profileId,
                                                     @RequestParam(value = "month", defaultValue = "1" ) int month,
                                                     @RequestParam(value = "year", defaultValue = "2020") int year) throws NotFoundException, ForbiddenException {
        UUID dieticianId = userService.findUserIdByUsername(principal.getName());
        Profile dieticianProfile = profileService.getProfileById(dieticianId);
        Profile userProfile = profileService.getProfileById(profileId);

        ProfileConnection pc = profileConnectionService.getProfileConnectionByProfileAndConnectedWithAndTypeAndIsAccepted(userProfile, dieticianProfile, EConnectionType.WITH_DIETICIAN);
        if(pc != null)
            return new ResponseEntity<>(reportService.getReportsByMonthAndProfileId(month, year, profileId), HttpStatus.OK);
        throw new ForbiddenException("There is no connection with profile with id" + profileId);
    }

}
