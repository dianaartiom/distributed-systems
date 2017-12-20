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
import utm.pad.lab3.repository.BookRepository;
import utm.pad.lab3.services.ReserveService;

@Controller
@RequestMapping("/reservation")
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @Autowired
    BookRepository bookRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> getAll() {
        return new ResponseEntity(reserveService.findAll(), HttpStatus.OK);
    }

    /* trebuie de verificat count-ul */
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> newReserve(@RequestBody ReserveDTO reserveDTO, @RequestBody PersonDTO personDTO) {
            return new ResponseEntity<>(reserveService.insert(reserveDTO, personDTO), HttpStatus.OK);
    }
}