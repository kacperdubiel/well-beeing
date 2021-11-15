package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.ReportExercise;
import com.wellbeeing.wellbeeing.domain.sport.SportReport;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.sport.SportReportService;
import com.wellbeeing.wellbeeing.service.sport.TrainingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/report")
public class SportReportController {
    private final SportReportService sportReportService;
    private final UserService userService;
    private UserDAO userDAO;


    public SportReportController(@Qualifier("sportReportService") SportReportService sportReportService,
                                 @Qualifier("userService") UserService userService) {
        this.sportReportService = sportReportService;
        this.userService = userService;
    }

    @GetMapping(path = "/{reportId}")
    public ResponseEntity<?> getSportReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        SportReport report  = sportReportService.getSportReportById(reportId);
        if(!profileId.equals(report.getReportOwner().getId())){
            throw new ForbiddenException("Access to sport report with id" + reportId + " forbidden");
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{reportId}")
    public ResponseEntity<?> deleteSportReportById(@PathVariable("reportId") UUID reportId, Principal principal) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        boolean reportDelStatus  = sportReportService.deleteSportReportById(reportId);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{reportId}/exercise")
    public ResponseEntity<?> deleteExercisesFromSportReportByReportId(Principal principal,
                                                              @PathVariable("reportId")UUID reportId,
                                                              @RequestBody @NonNull List<Long> exercises) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to sport report with id" + reportId + " forbidden");
        }
        SportReport reportDelStatus  = sportReportService.deleteExercisesFromReportByReportId(reportId, exercises);
        return new ResponseEntity<>(reportDelStatus, HttpStatus.OK);
    }

    @PostMapping(path = "/{reportId}/exercise")
    public ResponseEntity<?> addExercisesToReportByReportId(Principal principal,
                                                         @PathVariable("reportId")UUID reportId,
                                                         @RequestBody @NonNull List<ReportExercise> exercises) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(sportReportService.getSportReportById(reportId).getReportOwner().getId())){
            throw new ForbiddenException("Access to report with id" + reportId + " forbidden");
        }
        SportReport reportAddStatus  = sportReportService.addExercisesToReportByReportId(exercises, reportId);
        return new ResponseEntity<>(reportAddStatus, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addReport(Principal principal) throws NotFoundException, ConflictException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        SportReport newReport = sportReportService.addSportReportForProfileByProfileId(profileId);
        return new ResponseEntity<>(newReport, HttpStatus.OK);
    }

    @GetMapping(path = "/{date}/date")
    public ResponseEntity<?> getReportByDate(Principal principal,
                                             @PathVariable("date") LocalDate date) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(sportReportService.getSportReportByDateAndProfileId(date, profileId), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getReportByMonthAndYear(Principal principal,
                                                     @RequestParam(value = "month", defaultValue = "1" ) int month,
                                                     @RequestParam(value = "year", defaultValue = "2020") int year) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(sportReportService.getSportReportsByMonthAndProfileId(month, year, profileId), HttpStatus.OK);
    }
}
