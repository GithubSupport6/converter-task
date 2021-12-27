package com.convertertask.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Component
public class CbrfService {

    @Value("${cbrf.connection-string}")
    String CONNECTION_STRING;// = "http://www.cbr.ru/scripts/XML_daily.asp";
    String KEYWORD = "Valute";

    RestTemplate restTemplate;

    public CbrfService(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    private String makeStringDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (day.length() < 2) day  = "0" + day;
        String month = String.valueOf(cal.get(Calendar.MONTH));
        if (day.length() < 2) month  = "0" + month;

        int year = cal.get(Calendar.YEAR);
        return day + "/" + month + "/" + year;
    }

    public Document getActualData(){
        try {
            String actualDate = makeStringDate(new Date());
            Optional<String> xmlString = Optional.ofNullable(restTemplate.getForObject(CONNECTION_STRING + "?date_req=" + actualDate, String.class));
            if (!xmlString.isPresent()){
                return null;
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new StringReader(xmlString.get())));
            return document;
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
