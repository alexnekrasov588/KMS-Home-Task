package com.kms.task.model;

public class NotesModel extends ATMModel {

    private int noteValue;

    public NotesModel() {
    }

    public NotesModel(int noteValue) {
        this.noteValue = noteValue;
    }

    public int getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(int noteValue) {
        this.noteValue = noteValue;
    }
}
