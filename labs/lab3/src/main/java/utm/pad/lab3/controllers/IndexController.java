package utm.pad.lab3.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> json(){
        return new ResponseEntity(HttpStatus.OK);
    }
}