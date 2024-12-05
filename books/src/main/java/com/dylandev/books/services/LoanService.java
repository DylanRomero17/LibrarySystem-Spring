package com.dylandev.books.services;

import com.dylandev.books.entities.Loan;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    Loan saveLoan(Loan loan);

    Loan updateLoan(Loan loan);

    List<Loan> getLoans();

    Optional<Loan> getLoanById(Long id);

    void deleteLoan(Loan loan) throws IOException;
}
