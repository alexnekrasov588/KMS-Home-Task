package com.kms.task.model;

import java.util.ArrayList;
import java.util.Collections;

public class CustomerAccount {

    ArrayList<AddNotesModel> addNotesModelAsList = new ArrayList<>(Collections.emptyList());

    public CustomerAccount() {
    }

    public CustomerAccount(ArrayList<AddNotesModel> addNotesModelAsList) {
        this.addNotesModelAsList = addNotesModelAsList;
    }

    public ArrayList<AddNotesModel> getAddNotesModelAsList() {
        return addNotesModelAsList;
    }

    public void setAddNotesModelAsList(ArrayList<AddNotesModel> addNotesModelAsList) {
        ArrayList<AddNotesModel> newList = new ArrayList<>(Collections.emptyList());
        newList.addAll(this.addNotesModelAsList);
        newList.addAll(addNotesModelAsList);
        this.addNotesModelAsList = newList;
    }

    public void setAddNotesModelAsListAfterSubtract(ArrayList<AddNotesModel> addNotesModelAsList) {
        this.addNotesModelAsList = addNotesModelAsList;
    }
}
