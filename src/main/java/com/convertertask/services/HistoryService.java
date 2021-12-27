package com.convertertask.services;


import com.convertertask.entities.Convertation;
import com.convertertask.repositories.ConvertationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class HistoryService {

    @Autowired
    ConvertationRepository convertationRepository;

    public List<Convertation> getHistory(){
        Optional<List<Convertation>> query = Optional.ofNullable(convertationRepository.findAll());
        if (query.isPresent()){
            return query.get();
        }
        else {
            return new LinkedList<>();
        }
    }

    public void addConvertation(Convertation convertation){
        convertationRepository.save(convertation);
    }

}
