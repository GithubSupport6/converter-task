package com.convertertask.services;

import com.convertertask.entities.Course;
import com.convertertask.entities.Valute;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ConverterService {

    int digitsAfterPoint = 3;
    int maxScale = 100;

    public BigDecimal convert(double count, Course from, Course to){

        if (count == 0){
            return BigDecimal.ZERO;
        }

        //Let FROM and TO
        //One FROM equals this count of roubles
        BigDecimal roublesFrom = new BigDecimal(from.getValue()).divide(new BigDecimal(from.getNominal()));

        //One TO equals this count of roubles
        BigDecimal roublesTo = new BigDecimal(to.getValue()).divide(new BigDecimal(to.getNominal()));

        //Count of FROM equals res
        BigDecimal res = roublesFrom.divide(roublesTo,maxScale,RoundingMode.HALF_UP);

        res = res.multiply(new BigDecimal(count));

        return res.setScale(digitsAfterPoint, RoundingMode.HALF_UP);
    }

}
