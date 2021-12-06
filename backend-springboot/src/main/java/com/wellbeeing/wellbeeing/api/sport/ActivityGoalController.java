package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.ActivityGoalService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/activity-goal")
@RestController
public class ActivityGoalController {
    private final ActivityGoalService activityGoalService;
    private final UserDAO userDAO;
    @Autowired
    private AuthenticationManager authenticationManager;


    public ActivityGoalController(@Qualifier("activityGoalService") ActivityGoalService activityGoalService,
                                  @Qualifier("userDAO") UserDAO userDAO) {
        this.activityGoalService = activityGoalService;
        this.userDAO = userDAO;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getActivityGoalById(@PathVariable(value = "id") Long activityGoalId, Principal principal) throws NotFoundException {
        ActivityGoal activityGoal = activityGoalService.getActivityGoal(activityGoalId, principal.getName());
        return new ResponseEntity<>(activityGoal, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getMyActivityGoals(Principal principal) throws NotFoundException {
        List<ActivityGoal> activityGoals = activityGoalService.getMyActivityGoals(principal.getName());
        return new ResponseEntity<>(activityGoals, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addActivityGoal(@RequestBody @NotNull ActivityGoal activityGoal, Principal principal) throws NotFoundException {
        ActivityGoal newActivityGoal = activityGoalService.addActivityGoal(activityGoal, principal.getName());
        return new ResponseEntity<>(newActivityGoal, HttpStatus.OK);
    }

}
