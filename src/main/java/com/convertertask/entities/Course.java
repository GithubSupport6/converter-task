package com.convertertask.entities;

import com.convertertask.util.DateMapper;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    long id;

    double value;

    int nominal;

    Date date;

    @ManyToOne
    @JoinColumn(nullable=false)
    Valute valute;

    @OneToMany(mappedBy = "course")
    List<Convertation> convertations;

    public Course(){
        date = new DateMapper().getSqlDate(new java.util.Date());
    }

    public Course(int nominal, double value){
        this();
        this.nominal = nominal;
        this.value = value;
    }

    public int getNominal() {
        return nominal;
    }

    public double getValue() {
        return value;
    }

    public Date getDate(){
        return date;
    }

    public void setValute(Valute newValute){
        this.valute = newValute;
    }
}
