package com.wellbeeing.wellbeeing.api;

import com.wellbeeing.wellbeeing.domain.Role;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.RoleServiceApi;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
