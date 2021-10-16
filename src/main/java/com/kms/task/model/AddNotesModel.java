package com.kms.task.model;

import java.util.Objects;

public class AddNotesModel extends ATMModel {

    private String currency;

    public AddNotesModel(String currency, int value, int number) {
        super(value, number);
        this.currency = currency;
    }

    public AddNotesModel() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddNotesModel that = (AddNotesModel) o;
        return Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getValue());
    }

    public AddNotesModel merge(AddNotesModel other) {
        assert(this.equals(other));
        return new AddNotesModel(this.getCurrency(), this.getValue(), this.getNumber() + other.getNumber());
    }

//    @Override
//    public int compareTo(AddNotesModel o) {
//        return this.getCurrency().compareTo(o.getCurrency());
//    }
}