package com.kms.task.model;

public abstract class ATMModel {

    private int value;
    private int number;

    public ATMModel() {
    }

    public ATMModel(int value, int number) {
        this.value = value;
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
