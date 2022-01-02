package com.wellbeeing.wellbeeing.diet.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wellbeeing.wellbeeing.api.diet.message.CreateNutritionPlanRequest;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.diet.dish.Dish;
import com.wellbeeing.wellbeeing.domain.diet.nutrition_plan.NutritionPlanPosition;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeekDay;
import com.wellbeeing.wellbeeing.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestPropertySource(
        locations = "classpath:diet-test.properties")
public class NutritionPlanControllerTests {

    private static final String URL_BASE = "/nutrition-plan";
    @Autowired
    private UserDetailsService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MockMvc mvc;

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addNewEmptyNutritionPlan201()
            throws Exception {
        String userName = "maja@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        CreateNutritionPlanRequest request = new CreateNutritionPlanRequest();
        request.setName("nowy");

        mvc.perform(post(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("nowy"));
    }

    @Test
    public void addNewEmptyNutritionPlanDieticianByNotDietician403()
            throws Exception {
        String userName = "kajtek@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        CreateNutritionPlanRequest request = new CreateNutritionPlanRequest();
        request.setName("nowy");

        mvc.perform(post(URL_BASE + "/dietician")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void addPositionToOwnedNutritionPlan200()
            throws Exception {
        String userName = "klaudia@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        NutritionPlanPosition pos = NutritionPlanPosition.builder()
                .mealType(EMealType.LUNCH)
                .weekDay(EWeekDay.SATURDAY)
                .dish(Dish.builder()
                        .id(UUID.fromString("2ea31743-56d8-454a-adba-19b331f7b96d")).build()).build();

        mvc.perform(post(URL_BASE +"/57ddf132-6d3a-4343-abcd-f623d306e031/position")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pos)))
                .andExpect(status().isOk());
    }

    @Test
    public void addPositionToNotOwnedNutritionPlan403()
            throws Exception {
        String userName = "kajtek@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        NutritionPlanPosition pos = NutritionPlanPosition.builder()
                .mealType(EMealType.LUNCH)
                .weekDay(EWeekDay.SATURDAY)
                .dish(Dish.builder()
                        .id(UUID.fromString("2ea31743-56d8-454a-adba-19b331f7b96d")).build()).build();

        mvc.perform(post(URL_BASE +"/57ddf132-6d3a-4343-abcd-f623d306e031/position")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pos)))
                .andExpect(status().isForbidden());
    }
}
