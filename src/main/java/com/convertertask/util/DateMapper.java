package com.convertertask.util;

import org.springframework.stereotype.Component;

@Component
public class DateMapper {

    public java.sql.Date getSqlDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }

}
