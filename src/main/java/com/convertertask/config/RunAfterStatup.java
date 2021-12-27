package com.convertertask.config;

import com.convertertask.entities.Valute;
import com.convertertask.services.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RunAfterStatup {

    @Autowired
    ValuteService valuteService;

    public RunAfterStatup(){
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup(){
        List<Valute> valutes = valuteService.getActualValutes();
        valuteService.saveValutes(valutes);
    }
}
