package com.kms.task.model;

public class CurrencyModel {

    private int ten;

    private int twenty;

    private int thirty;

    private int fifty;

    private int oneHundred;

    private int oneHundredAndFifty;

    public CurrencyModel() {
    }

    public CurrencyModel(int ten, int twenty, int thirty, int fifty, int oneHundred, int oneHundredAndFifty) {
        this.ten = ten;
        this.twenty = twenty;
        this.thirty = thirty;
        this.fifty = fifty;
        this.oneHundred = oneHundred;
        this.oneHundredAndFifty = oneHundredAndFifty;
    }

    public int getTen() {
        return ten;
    }

    public void setTen(int ten) {
        this.ten = ten;
    }

    public int getTwenty() {
        return twenty;
    }

    public void setTwenty(int twenty) {
        this.twenty = twenty;
    }

    public int getThirty() {
        return thirty;
    }

    public void setThirty(int thirty) {
        this.thirty = thirty;
    }

    public int getFifty() {
        return fifty;
    }

    public void setFifty(int fifty) {
        this.fifty = fifty;
    }

    public int getOneHundred() {
        return oneHundred;
    }

    public void setOneHundred(int oneHundred) {
        this.oneHundred = oneHundred;
    }

    public int getOneHundredAndFifty() {
        return oneHundredAndFifty;
    }

    public void setOneHundredAndFifty(int oneHundredAndFifty) {
        this.oneHundredAndFifty = oneHundredAndFifty;
    }
}
