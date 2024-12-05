package com.dylandev.books.controllers;

import com.dylandev.books.entities.Loan;
import com.dylandev.books.services.LoanServiceImpl;
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
@RequestMapping("/loan")
@CrossOrigin("http://localhost:4200/")
public class LoanController {

    @Autowired
    private LoanServiceImpl loanServiceImpl;

    @PostMapping
    public ResponseEntity<?> saveLoan(@Valid @RequestBody Loan loan, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + bindingResult.getAllErrors());
        }
        try{
            Loan savedLoan = loanServiceImpl.saveLoan(loan);
            return ResponseEntity.ok(savedLoan);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Data integrity error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan){
        try{
            Loan savedLoan = loanServiceImpl.updateLoan(loan);
            return ResponseEntity.ok(savedLoan);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans(){
        return new ResponseEntity<>(loanServiceImpl.getLoans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id){
        Optional<Loan> loan = loanServiceImpl.getLoanById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) throws Exception{
        Optional<Loan> loan = loanServiceImpl.getLoanById(id);
        if(loan.isPresent()){
            loanServiceImpl.deleteLoan(loan.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
