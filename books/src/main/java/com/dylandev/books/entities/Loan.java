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
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate loanDate;

    @NotBlank
    private String loanStatus;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee employee;

}
