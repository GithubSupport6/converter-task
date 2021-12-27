package com.convertertask.repositories;

import com.convertertask.entities.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ValuteRepository extends JpaRepository<Valute,Long> {

    public List<Valute> findByCharCode(String charCode);

}
