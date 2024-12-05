package com.dylandev.books.controllers;

import com.dylandev.books.entities.Employee;
import com.dylandev.books.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200/")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        try{
            Employee savedEmployee = employeeServiceImpl.saveEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee (@RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeServiceImpl.updateEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeServiceImpl.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeServiceImpl.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws IOException{
        Optional<Employee> employee = employeeServiceImpl.getEmployeeById(id);
        if(employee.isPresent()){
            employeeServiceImpl.deleteEmployee(employee.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
