package utm.pad.lab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import utm.pad.lab3.dtos.PersonDTO;
import utm.pad.lab3.dtos.ReserveDTO;
import utm.pad.lab3.services.UserService;

/** only intended to create users */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> create(){
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> newReserve(@RequestBody PersonDTO personDTO) {

        if (personDTO != null) {
            return new ResponseEntity<>(userService.insert(personDTO), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
