package com.convertertask.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Convertation {

    @Id
    @GeneratedValue
    long id;

    String fromCode;

    String toCode;


    double result;

    Date date;

    @ManyToOne
    @JoinColumn(nullable = false)
    Course course;


    public Course getCourse() {
        return course;
    }

    public Date getDate() {
        return date;
    }

    public double getResult() {
        return result;
    }

    public long getID() {
        return id;
    }

    public String getFromCode() {
        return fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public Convertation(){}

    public Convertation(String from, String to, double result, java.sql.Date date, Course course){
        this.fromCode = from;
        this.toCode = to;
        this.result = result;
        this.date = date;
        this.course = course;
    }

}
