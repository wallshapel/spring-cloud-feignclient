package com.moto.moto.repositories;

import com.moto.moto.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    List<Moto> findByUserId(Long userId);
}
