package com.convertertask.models;

public class ConvertModel {
    private final String from;
    private final String to;
    private final double amount;

    public double getAmount() {
        return amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public ConvertModel(){
        from = "";
        to = "";
        amount = 0;
    }

    public ConvertModel(String from, String to, double amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
