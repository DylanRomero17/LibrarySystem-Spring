package com.dylandev.books.repositories;

import com.dylandev.books.entities.Book;
import com.dylandev.books.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}





