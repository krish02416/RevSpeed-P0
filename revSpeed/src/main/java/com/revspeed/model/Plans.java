package com.revspeed.model;

public class Plans {

    private String planName;//normal plan , medium , high
    private int speed;
    private int validity;
    private String data_limit;
    private String fup;
    private double price;
    private String quata;
    private String no_of_device;
    private String Stream;// sd , hd, 4k
    private String no_of_ott;//10 ,20, 30

    public Plans(){}
    //getter methods
    public Plans(String planName) {
        this.planName = planName;
    }

    public String getPlanName() {
        return planName;
    }

    public int getSpeed() {
        return speed;
    }

    public int getValidity() {
        return validity;
    }

    public String getData_limit() {
        return data_limit;
    }

    public String getFup() {
        return fup;
    }

    public double getPrice() {
        return price;
    }

    public String getQuata() {
        return quata;
    }

    public String getNo_of_device() {
        return no_of_device;
    }

    public String getStream() {
        return Stream;
    }

    public String getNo_of_ott() {
        return no_of_ott;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public void setData_limit(String data_limit) {
        this.data_limit = data_limit;
    }

    public void setFup(String fup) {
        this.fup = fup;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuata(String quata) {
        this.quata = quata;
    }

    public void setNo_of_device(String no_of_device) {
        this.no_of_device = no_of_device;
    }

    public void setStream(String stream) {
        Stream = stream;
    }

    public void setNo_of_ott(String no_of_ott) {
        this.no_of_ott = no_of_ott;
    }

    public String toString() {
        return "Plans{" +
                "planName='" + planName + '\'' +
                ", speed=" + speed +
                ", validity=" + validity +
                ", data_limit='" + data_limit + '\'' +
                ", fup='" + fup + '\'' +
                ", price=" + price +
                ", quata='" + quata + '\'' +
                ", no_of_device='" + no_of_device + '\'' +
                ", Stream='" + Stream + '\'' +
                ", no_of_ott='" + no_of_ott + '\'' +
                '}';
    }
}
