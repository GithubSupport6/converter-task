package com.convertertask.repositories;

import com.convertertask.entities.Convertation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertationRepository extends JpaRepository<Convertation,Long> {
}
