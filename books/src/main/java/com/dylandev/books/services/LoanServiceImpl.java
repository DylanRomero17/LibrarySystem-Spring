package com.dylandev.books.services;

import com.dylandev.books.entities.Loan;
import com.dylandev.books.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public void deleteLoan(Loan loan) throws IOException {
        loanRepository.deleteById(loan.getId());
    }
}
