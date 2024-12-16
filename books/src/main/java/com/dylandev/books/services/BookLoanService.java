package com.dylandev.books.services;

import com.dylandev.books.entities.BookLoan;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookLoanService {
    BookLoan saveBookLoan(BookLoan bookLoan);

    BookLoan updateBookLoan(BookLoan bookLoan);

    List<BookLoan> getBookLoans();

    Optional<BookLoan> getBookLoanById(Long id);

    void deleteBookLoan(BookLoan bookLoan) throws IOException;
}
