package utm.pad.lab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utm.pad.lab3.builders.BookBuilder;
import utm.pad.lab3.dtos.BookDTO;
import utm.pad.lab3.services.BookService;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> getAllBooks() {
        return new ResponseEntity(bookService.findAll(), OK);
    }

    /* pentru mine ca sa pot introduce carti */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> createBook(@RequestBody BookDTO bookDTO) {

        return new ResponseEntity(bookService.insert(BookBuilder.get(bookDTO)), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> getBookById(@PathVariable String id) {
        return new ResponseEntity(bookService.findById(id), OK);
    }

}