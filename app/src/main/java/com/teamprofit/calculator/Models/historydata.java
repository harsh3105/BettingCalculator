package com.teamprofit.calculator.Models;

public class historydata {

    String first;
    String second;
    String thrid;
    String fouth;
    String Money;
    int image;

    public historydata(String first, String second, String thrid, String fouth, String Money, int image) {
        this.first = first;
        this.second = second;
        this.thrid=thrid;
        this.fouth=fouth;
        this.Money=Money;
        this.image=image;
    }

    public historydata() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThrid() {
        return thrid;
    }

    public void setThrid(String thrid) {
        this.thrid = thrid;
    }

    public String getFouth() {
        return fouth;
    }

    public void setFouth(String fouth) {
        this.fouth = fouth;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
