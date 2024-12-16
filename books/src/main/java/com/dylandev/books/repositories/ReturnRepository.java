package com.dylandev.books.repositories;

import com.dylandev.books.entities.Return;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnRepository extends JpaRepository<Return, Long> {
}
