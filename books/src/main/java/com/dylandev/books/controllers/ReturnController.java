package com.dylandev.books.controllers;

import com.dylandev.books.entities.Return;
import com.dylandev.books.services.ReturnServiceImpl;
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
@RequestMapping("/return")
@CrossOrigin("http://localhost:4200/")
public class ReturnController {

    @Autowired
    private ReturnServiceImpl returnServiceImpl;

    @PostMapping
    public ResponseEntity<?> saveReturn(@Valid @RequestBody Return returnDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation error: " + bindingResult.getAllErrors());
        }
        try{
            return ResponseEntity.ok(returnServiceImpl.saveReturn(returnDTO));
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Data integrity error: " + e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Return> updateReturn(@RequestBody Return returnDTO){
        try{
            return ResponseEntity.ok(returnServiceImpl.updateReturn(returnDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Return>> getAllReturns(){
        return new ResponseEntity<>(returnServiceImpl.getReturns(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReturn(@PathVariable Long id) throws Exception{
        Optional<Return> returnDTO = returnServiceImpl.getReturnById(id);
        if(returnDTO.isPresent()){
            returnServiceImpl.deleteReturn(returnDTO.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
