package com.wellbeeing.wellbeeing.diet.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestPropertySource(
        locations = "classpath:diet-test.properties")
public class NutritionLabelControllerTests {

    private static final String URL_BASE = "/nutrition-label";
    @Autowired
    UserDetailsService userService;
    @Autowired
    JwtUtil jwtUtil;
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
    public void addNutritionLabelByDietician201()
            throws Exception {
        String userName = "maja@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        NutritionLabel nutritionLabel = NutritionLabel.builder().name("newLabel").description("descr").build();

        mvc.perform(post(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nutritionLabel)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("newLabel"));
    }

    @Test
    public void addNutritionLabelByNotDietician403()
            throws Exception {
        String userName = "kajtek@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        NutritionLabel nutritionLabel = NutritionLabel.builder().name("newLabel").description("descr").build();

        mvc.perform(post(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nutritionLabel)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateNutritionLabelByOwner200()
            throws Exception {
        String userName = "klaudia@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        NutritionLabel nutritionLabel = NutritionLabel.builder().name("newLabelMarchew").description("descr").build();

        mvc.perform(put(URL_BASE + "/db2da3d0-67c5-4f4d-8e4b-98ee2c487273")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nutritionLabel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("newLabelMarchew"));
    }

    @Test
    public void updateNutritionLabelByNotOwner403()
            throws Exception {
        String userName = "maja@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        NutritionLabel nutritionLabel = NutritionLabel.builder().name("newLabelMarchew").description("descr").build();

        mvc.perform(put(URL_BASE + "/db2da3d0-67c5-4f4d-8e4b-98ee2c487273")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nutritionLabel)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getNutritionLabelWithIdTest200()
            throws Exception {
        String userName = "maja@";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        mvc.perform(get(URL_BASE +"/db2da3d0-67c5-4f4d-8e4b-98ee2c487273")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nutritionLabelId").value("db2da3d0-67c5-4f4d-8e4b-98ee2c487273"));
    }
}
