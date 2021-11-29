package com.wellbeeing.wellbeeing.diet;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/test.properties")
public class NutritionLabelControllerTests {

    @LocalServerPort
    private int port;
    @Autowired
    JwtUtil jwt;
    @Autowired @Qualifier("userService")
    UserService userService;
    User user;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    NutritionLabel fetchedNutritionLabel =  null;

    public void init(){
        setup();
        try{
            userService.loadUserByEmail("test_diet");
        } catch(UsernameNotFoundException e){
            createNewUser();
        }
        String token = jwt.generateToken(user);
        headers.clear();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }

    public void setup(){
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.ROLE_BASIC_USER));
        roles.add(new Role(ERole.ROLE_DIETICIAN));
        this.user = new User("test_diet", "test_diet", roles);
    }

    void createNewUser(){
        userService.register(this.user);
    }

    @Test
    public void testCreateLabel() throws Exception {
        init();
        NutritionLabel nl = NutritionLabel.builder()
                .name("test" + LocalDateTime.now())
                .description("opis")
                .build();
        RequestEntity<NutritionLabel> request = RequestEntity
                .post(createURLWithPort("/nutrition-label"))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(nl);
        ResponseEntity<NutritionLabel> response = restTemplate.exchange(request, NutritionLabel.class);
        Assertions.assertSame(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).getName().contains("test"));
    }

    @Test
    public void testGetLabel() throws Exception{
        init();
        RequestEntity<Void> request = RequestEntity
                .get(createURLWithPort("/nutrition-label/a3f0c859-8318-4e31-bec7-d6388f24ff3b"))
                .headers(headers)
                .build();
        ResponseEntity<NutritionLabel> response = restTemplate.exchange(request, NutritionLabel.class);
        this.fetchedNutritionLabel = response.getBody();
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).getName().contains("fetch_test"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private String createSampleJsonData() {
        return "{\"id\":1,\"name\":\"Tom\"}";
    }
}
