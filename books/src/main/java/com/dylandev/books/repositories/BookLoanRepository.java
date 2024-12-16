package com.dylandev.books.repositories;

import com.dylandev.books.entities.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

}
