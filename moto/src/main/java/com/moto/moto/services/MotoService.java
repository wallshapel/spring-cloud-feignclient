package com.moto.moto.services;

import com.moto.moto.entities.Moto;
import com.moto.moto.repositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> index() {
        return motoRepository.findAll();
    }

    public Moto show(Long id) {
        return motoRepository.findById(id).orElse(null);
    }

    public Moto store(Moto moto) {
        return motoRepository.save(moto);
    }

    public List<Moto> byUserId(Long userId) {
        return motoRepository.findByUserId(userId);
    }

}
