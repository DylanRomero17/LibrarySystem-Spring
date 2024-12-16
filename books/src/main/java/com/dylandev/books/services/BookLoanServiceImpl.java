package com.dylandev.books.services;

import com.dylandev.books.entities.BookLoan;
import com.dylandev.books.repositories.BookLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookLoanServiceImpl implements BookLoanService{

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Override
    public BookLoan saveBookLoan(BookLoan bookLoan) {
        return bookLoanRepository.save(bookLoan);
    }

    @Override
    public BookLoan updateBookLoan(BookLoan bookLoan) {
        return bookLoanRepository.save(bookLoan);
    }

    @Override
    public List<BookLoan> getBookLoans() {
        return bookLoanRepository.findAll();
    }

    @Override
    public Optional<BookLoan> getBookLoanById(Long id) {
        return bookLoanRepository.findById(id);
    }

    @Override
    public void deleteBookLoan(BookLoan bookLoan) throws IOException {
        bookLoanRepository.deleteById(bookLoan.getId());
    }
}
