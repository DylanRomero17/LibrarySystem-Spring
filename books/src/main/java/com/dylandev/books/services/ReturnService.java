package com.dylandev.books.services;

import com.dylandev.books.entities.Return;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ReturnService {
    Return saveReturn(Return returnDto);

    Return updateReturn(Return returnDto);

    List<Return> getReturns();

    Optional<Return> getReturnById(Long id);

    void deleteReturn(Return returnDto) throws IOException;
}
