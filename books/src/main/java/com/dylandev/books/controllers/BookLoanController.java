package com.dylandev.books.controllers;

import com.dylandev.books.entities.Book;
import com.dylandev.books.entities.BookLoan;
import com.dylandev.books.services.BookLoanServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bookLoan")
@CrossOrigin("http://localhost:4200/")
public class BookLoanController {

    @Autowired
    private BookLoanServiceImpl bookLoanServiceImpl;

    @PostMapping
    public ResponseEntity<?> saveBookLoan(@Valid @RequestBody BookLoan bookLoan, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + bindingResult.getAllErrors());
        }
        try{
            return ResponseEntity.ok(bookLoanServiceImpl.saveBookLoan(bookLoan));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Data integrity error: " + e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<BookLoan> updateBookLoan(@RequestBody BookLoan bookLoan){
        try{
            return ResponseEntity.ok(bookLoanServiceImpl.updateBookLoan(bookLoan));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BookLoan>> getAllBookLoans(){
        return new ResponseEntity<>(bookLoanServiceImpl.getBookLoans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookLoan> getBookLoanById(@PathVariable Long id){
        Optional<BookLoan> bookLoan = bookLoanServiceImpl.getBookLoanById(id);
        return bookLoan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookLoan(@PathVariable Long id) throws Exception{
        Optional<BookLoan> bookLoan = bookLoanServiceImpl.getBookLoanById(id);
        if(bookLoan.isPresent()){
            bookLoanServiceImpl.deleteBookLoan(bookLoan.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
