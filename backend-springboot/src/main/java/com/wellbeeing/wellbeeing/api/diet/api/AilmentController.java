package com.wellbeeing.wellbeeing.api.diet.api;

import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.service.diet.AilmentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AilmentController {
    private AilmentService ailmentService;

    public AilmentController(@Qualifier("ailmentService") AilmentService ailmentService){
        this.ailmentService = ailmentService;
    }

    @RequestMapping(path = "/ailment/{ailmentId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAilmentById(@PathVariable("ailmentId") UUID ailmentID) throws NotFoundException {
        Ailment ailment = ailmentService.getAilmentById(ailmentID);
        return new ResponseEntity<>(ailment, HttpStatus.OK);
    }

    @RequestMapping(path = "/ailment", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAilments() {
        return new ResponseEntity<>(ailmentService.getAllAilments(), HttpStatus.OK);
    }
}
