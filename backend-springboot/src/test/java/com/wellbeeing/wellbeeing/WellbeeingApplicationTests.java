package com.wellbeeing.wellbeeing;

import com.wellbeeing.wellbeeing.sport.tests.ExercisesControllerIntegrationTests;
import com.wellbeeing.wellbeeing.sport.tests.TrainingControllerTests;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExercisesControllerIntegrationTests.class,
        TrainingControllerTests.class
})
public class WellbeeingApplicationTests {

    @Test
    void contextLoads() {
    }


}
