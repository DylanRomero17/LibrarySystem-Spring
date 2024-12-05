package com.dylandev.books.services;

import com.dylandev.books.entities.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployees();

    Optional<Employee> getEmployeeById(Long id);

    void deleteEmployee(Employee employee) throws IOException;
}
