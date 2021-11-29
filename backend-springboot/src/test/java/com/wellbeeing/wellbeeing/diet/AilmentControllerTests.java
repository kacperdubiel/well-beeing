package com.wellbeeing.wellbeeing.diet;

import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.repository.diet.AilmentDAO;
import com.wellbeeing.wellbeeing.service.diet.AilmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class AilmentControllerTests {

    @MockBean
    private AilmentDAO ailmentDAO;

    @InjectMocks
    AilmentServiceImpl ailmentService;

    @Test
    public void testRetrieveAilmentWithMockRepository() throws Exception {
        UUID id = UUID.fromString("fc5f8de8-5116-11ec-bf63-0242ac130002");
        Ailment ailment = Ailment.builder()
                .id(id)
                .name("Cukrzyca")
                .description("opis")
                .build();
        when(ailmentDAO.findById(id)).thenReturn(Optional.of(ailment));
        Assertions.assertTrue(ailmentService.getAilmentById(id).getName().contains("Cukrzyca"));
    }
}
