package com.wellbeeing.wellbeeing.diet.unit;

import com.wellbeeing.wellbeeing.repository.diet.calculation.ProfileDietCalculationDAO;
import com.wellbeeing.wellbeeing.service.diet.calculation.ProfileDietCalculationServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class DietCalculationsServiceTests {

    @MockBean
    private ProfileDietCalculationDAO profileDietCalculationDAO;

    @InjectMocks
    ProfileDietCalculationServiceImpl dietCalculationService;
}
