package com.convertertask.services;

import com.convertertask.util.DateMapper;
import com.convertertask.entities.Valute;
import com.convertertask.repositories.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


import java.util.LinkedList;
import java.util.List;

@Component
public class ValuteService {


    @Autowired
    ValuteRepository valuteRepository;

    @Autowired
    DateMapper dateMapper;

    @Autowired
    CbrfService cbrfService;

    @Autowired
    ParserService parserService;

    public ValuteService(){}

    public Valute getByCharCode(String charCode){
        return valuteRepository.findByCharCode(charCode).get(0);
    }

    public List<Valute> getActualValutes()  {
        try {
            Document document = cbrfService.getActualData();
            NodeList list = document.getElementsByTagName("Valute");
            LinkedList<Valute> valutes = new LinkedList<>();
            for (int i = 0;i<list.getLength();i++){
                valutes.add(parserService.parseValute(list.item(i)));
            }
            return valutes;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveValutes(List<Valute> valutes){
        valuteRepository.saveAll(valutes);
    }

    public List<String> getValutesNamesAndCodes(){
        List<Valute> valutes = valuteRepository.findAll();
        List<String> names = new LinkedList<>();
        valutes.stream().forEach(e -> {
            names.add(e.getCharCode() + " (" + e.getName() + ")");
        });
        return names;
    }


}
