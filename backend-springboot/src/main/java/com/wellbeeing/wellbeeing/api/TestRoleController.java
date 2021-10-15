package com.wellbeeing.wellbeeing.api;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.RoleServiceApi;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TestRoleController {
    private RoleServiceApi roleService;
    public TestRoleController(@Qualifier("roleService") RoleServiceApi roleService){
        this.roleService = roleService;
    }

    @RequestMapping(path = "/addRole", method = RequestMethod.POST)
    public ResponseEntity<?> addRole(@RequestBody @NonNull Role role){
        if(!roleService.addRole(role))
            return new ResponseEntity<>(new ErrorMessage("Role already exist", "error"), HttpStatus.CONFLICT);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/roles/doctor", method = RequestMethod.GET)
    @RolesAllowed(ERole.Name.ROLE_DOCTOR)
    public ResponseEntity<?> doctorTest(){
        return new ResponseEntity<>("Doctor here", HttpStatus.OK);
    }

    @RequestMapping(path = "/roles/trainer", method = RequestMethod.GET)
    @RolesAllowed(ERole.Name.ROLE_TRAINER)
    public ResponseEntity<?> trainerTest(){
        return new ResponseEntity<>("trainer here", HttpStatus.OK);
    }

    @RequestMapping(path = "/roles/dietician", method = RequestMethod.GET)
    @RolesAllowed(ERole.Name.ROLE_DIETICIAN)
    public ResponseEntity<?> dieticianTest(){
        return new ResponseEntity<>("dietician here", HttpStatus.OK);
    }

    @RequestMapping(path = "/roles/specialist", method = RequestMethod.GET)
    @RolesAllowed({ERole.Name.ROLE_DIETICIAN, ERole.Name.ROLE_DOCTOR, ERole.Name.ROLE_TRAINER})
    public ResponseEntity<?> specialistTest(){
        return new ResponseEntity<>("specialist here", HttpStatus.OK);
    }
}
