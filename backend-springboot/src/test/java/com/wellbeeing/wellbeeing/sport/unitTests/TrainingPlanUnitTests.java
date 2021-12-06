package com.wellbeeing.wellbeeing.sport.unitTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import com.wellbeeing.wellbeeing.service.sport.TrainingPlanService;
import com.wellbeeing.wellbeeing.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:sport-test.properties")
public class TrainingPlanUnitTests {

    @Autowired
    UserDetailsService userService;
    @Autowired
    TrainingPlanService trainingPlanService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private ObjectMapper objectMapper;

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Profile getUserProfile(String userName) {
        User user = (User) userService.loadUserByUsername(userName);
        return user.getProfile();
    }

    @Test
    public void addNewTrainingPlanForMyself()
            throws Exception {
        // Arrange
        String userName = "konrad@pwr.com";
        Date planDate = new Date(2021, Calendar.DECEMBER, 6);
        // Act
        TrainingPlan plan = TrainingPlan
                .builder()
                .beginningDate(planDate)
                .build();
        TrainingPlan addedPlan = trainingPlanService.addTrainingPlan(plan, userName, getUserProfile(userName).getId(), -1);
        //Assert
        List<TrainingPlan> myPlans = trainingPlanService.getMyTrainingPlans(userName);

        Assertions.assertTrue(myPlans.size() > 0);
        Assertions.assertTrue(myPlans.stream().anyMatch(p -> p.getTrainingPlanId() == addedPlan.getTrainingPlanId()));
    }
}
