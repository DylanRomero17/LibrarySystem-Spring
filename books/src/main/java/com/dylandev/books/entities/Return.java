package com.dylandev.books.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate returnDate;

    @NotBlank
    private String status;

    @NotBlank
    private String observation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Loan loan;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee employee;

}
