package com.convertertask.entities;


import com.convertertask.util.DateMapper;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "valute")
public class Valute {

    @Id
    @GeneratedValue
    long id;

    int numCode;


    String charCode;

    String name;

    public String getName(){
        return name;
    }

    public String getCharCode(){
        return charCode;
    }

    public Valute(){
    }

    @OneToMany(mappedBy = "valute")
    List<Course> courses;

    public Valute(long id, int numCode, String charCode, String name){
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
    }

}
