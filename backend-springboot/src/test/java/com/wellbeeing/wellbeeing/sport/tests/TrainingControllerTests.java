package com.wellbeeing.wellbeeing.sport.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.message.sport.AddExerciseToTrainingRequest;
import com.wellbeeing.wellbeeing.domain.sport.ETrainingDifficulty;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.util.JwtUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestPropertySource(
        locations = "classpath:sport-test.properties")
public class TrainingControllerTests {

    private static final String URL_BASE = "/sport/training";
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
    public void getAllTrainingsArrayAndCheckFirstRecord200Test()
            throws Exception {
        String userName = "konrad@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        mvc.perform(get(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content").isNotEmpty());
//                .andExpect(jsonPath("$.content[0].name").value("1 Handed Kettlebell Swing"));
    }

    @Test
    public void addTrainingWithAllValidNoPermission403Test()
            throws Exception {
        Training training = Training
                .builder()
                .name("MockTraining")
                .trainingDifficulty(ETrainingDifficulty.MEDIUM)
                .description("Some description")
                .instruction("Some instruction")
                .build();
        mvc.perform(post(URL_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(training)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void addTrainingWithAllValidTrainer201Test()
            throws Exception {
        String userName = "janusz@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        Training training = Training
                .builder()
                .name("MockTraining")
                .trainingDifficulty(ETrainingDifficulty.MEDIUM)
                .description("Some description")
                .instruction("Some instruction")
                .build();

        mvc.perform(post(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(training)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MockTraining"));
    }

    @Test
    public void addTrainingWithAllValidBasicUser201Test()
            throws Exception {
        String userName = "konrad@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);
        Training training = Training
                .builder()
                .name("MockTraining")
                .trainingDifficulty(ETrainingDifficulty.MEDIUM)
                .description("Some description")
                .instruction("Some instruction")
                .build();

        mvc.perform(post(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(training)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MockTraining"));
    }

    @Test
    public void addExerciseToTraining200Test()
            throws Exception {
        String userName = "konrad@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        long trainingId = 79;
        long exerciseId = 81;

        AddExerciseToTrainingRequest requestBody = new AddExerciseToTrainingRequest(2, 900, 3);
        mvc.perform(patch(String.format("%s/%d/add-exercise/%d", URL_BASE, trainingId, exerciseId))
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.exercise.exerciseId").value(81))
                .andExpect(jsonPath("$.repetitions").value(2))
                .andExpect(jsonPath("$.time_seconds").value(900))
                .andExpect(jsonPath("$.series").value(3));
    }

    @Test
    public void addExerciseToTrainingTestDuplicateExercise409Test()
            throws Exception {
        String userName = "konrad@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        long trainingId = 79;
        long exerciseId = 79;

        AddExerciseToTrainingRequest requestBody = new AddExerciseToTrainingRequest(2, 900, 3);
        mvc.perform(patch(String.format("%s/%d/add-exercise/%d", URL_BASE, trainingId, exerciseId))
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(status().isConflict());
    }

    @Test
    public void addExerciseToTrainingNotYourTraining403Test()
            throws Exception {
        String userName = "konrad@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        long trainingId = 95;
        long exerciseId = 79;

        AddExerciseToTrainingRequest requestBody = new AddExerciseToTrainingRequest(2, 900, 3);
        mvc.perform(patch(String.format("%s/%d/add-exercise/%d", URL_BASE, trainingId, exerciseId))
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void addTrainingWithInvalidNameDuplicate409Test()
            throws Exception {
        String userName = "janusz@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        Training training = Training
                .builder()
                .name("Siła i moc")
                .trainingDifficulty(ETrainingDifficulty.MEDIUM)
                .description("Some description")
                .instruction("Some instruction")
                .build();

        mvc.perform(post(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(training)))
                .andExpect(status().isConflict());
    }

    @Test
    public void addTrainingWithEmptyName409Test()
            throws Exception {
        String userName = "janusz@pwr.com";
        User user = (User) userService.loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(user);

        Training training = Training
                .builder()
                .trainingDifficulty(ETrainingDifficulty.MEDIUM)
                .description("Some description")
                .instruction("Some instruction")
                .build();

        mvc.perform(post(URL_BASE)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(training)))
                .andExpect(status().isConflict());
    }
}
