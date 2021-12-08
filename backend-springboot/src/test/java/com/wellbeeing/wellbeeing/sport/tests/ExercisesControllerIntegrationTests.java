package com.wellbeeing.wellbeeing.sport.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.message.sport.AddExerciseWithLabelsRequest;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.util.JwtUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestPropertySource(
        locations = "classpath:sport-test.properties")

public class ExercisesControllerIntegrationTests {

    @Autowired
    UserDetailsService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private MockMvc mvc;
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

    @Before
    public void setup() {
        System.out.println("Initiating the before steps");
    }

    @Test
    public void getAllExercisesArrayAndCheckFirstRecordTest()
            throws Exception {
        String userName = "konrad@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        mvc.perform(get("/sport/exercise")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].name").value("2 Handed Kettlebell Swing"));
    }

    @Test
    public void addExerciseWithAllValidNoPermissionTest()
            throws Exception {
        String userName = "konrad@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        Exercise exercise = Exercise
                .builder()
                .name("MockExercise")
                .exerciseType(EExerciseType.CARDIO)
                .met(8.5).description("Some description")
                .instruction("Some instruction")
                .build();
        AddExerciseWithLabelsRequest exerciseReq = new AddExerciseWithLabelsRequest(exercise, null);

        mvc.perform(post("/sport/exercise")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(exerciseReq)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void addExerciseWithAllValidTrainerTest()
            throws Exception {
        String userName = "janusz@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        Exercise exercise = Exercise
                .builder()
                .name("MockExercise")
                .exerciseType(EExerciseType.CARDIO)
                .met(8.5).description("Some description")
                .instruction("Some instruction")
                .build();
        AddExerciseWithLabelsRequest exerciseReq = new AddExerciseWithLabelsRequest(exercise, null);
        mvc.perform(post("/sport/exercise")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(exerciseReq)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MockExercise"));
    }

    @Test
    public void addExerciseWithInvalidNameDuplicateTest()
            throws Exception {
        String userName = "janusz@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        Exercise exercise = Exercise
                .builder()
                .name("2 Handed Kettlebell Swing")
                .exerciseType(EExerciseType.CARDIO)
                .met(8.5)
                .description("Some description")
                .instruction("Some instruction")
                .build();

        AddExerciseWithLabelsRequest exerciseReq = new AddExerciseWithLabelsRequest(exercise, null);
        mvc.perform(post("/sport/exercise")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(exerciseReq)))
                .andExpect(status().isConflict());
    }

    @Test
    public void addExerciseWithEmptyNameTest()
            throws Exception {
        String userName = "janusz@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        Exercise exercise = Exercise
                .builder()
                .exerciseType(EExerciseType.CARDIO)
                .met(8.5)
                .description("Some description")
                .instruction("Some instruction")
                .build();

        AddExerciseWithLabelsRequest exerciseReq = new AddExerciseWithLabelsRequest(exercise, null);
        mvc.perform(post("/sport/exercise")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(exerciseReq)))
                .andExpect(status().isNotFound());
    }
}
