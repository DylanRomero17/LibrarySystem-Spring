package com.dylandev.books.services;

import com.dylandev.books.entities.Return;
import com.dylandev.books.repositories.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnServiceImpl implements ReturnService{

    @Autowired
    private ReturnRepository returnRepository;

    @Override
    public Return saveReturn(Return returnDto) {
        return returnRepository.save(returnDto);
    }

    @Override
    public Return updateReturn(Return returnDto) {
        return returnRepository.save(returnDto);
    }

    @Override
    public List<Return> getReturns() {
        return returnRepository.findAll();
    }

    @Override
    public Optional<Return> getReturnById(Long id) {
        return returnRepository.findById(id);
    }

    @Override
    public void deleteReturn(Return returnDto) throws IOException {
        returnRepository.deleteById(returnDto.getId());
    }
}
