package com.kms.task.service.Interface;

import com.kms.task.model.NotesModel;

import java.util.Set;

public interface IAddNotesService {

    Set<NotesModel> getGeneratedNotes();

    boolean checkIfValid(String userInput);

}