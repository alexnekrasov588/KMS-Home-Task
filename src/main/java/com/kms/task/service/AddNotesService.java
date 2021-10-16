package com.kms.task.service;

import com.kms.task.model.AddNotesModel;
import com.kms.task.model.NotesModel;
import com.kms.task.service.Interface.IAddNotesService;
import com.kms.task.utils.Constants;
import com.kms.task.utils.GlobalLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Service
public class AddNotesService implements IAddNotesService {

    @Autowired
    ATMService atmService;

    @Autowired
    GlobalLoggerService globalLoggerService;

    /**
     * This method generating all kind of ATM Notes.
     *
     * @return All exist Notes: {10, 20, 30 , 50 , 100, 150}
     */
    @Override
    public Set<NotesModel> getGeneratedNotes() {

        Set<NotesModel> notesSet = new java.util.HashSet<>(Collections.emptySet());
        for (int i = 1; i <= 3; i++) {
            int tenNote = 10;
            int fiveNote = 5;

            int firstNote = tenNote * i;
            int secondNote = fiveNote * tenNote * i;

            NotesModel firstNoteAsModel = new NotesModel();
            NotesModel secondNoteAsModel = new NotesModel();
            firstNoteAsModel.setNoteValue(firstNote);
            secondNoteAsModel.setNoteValue(secondNote);

            notesSet.add(firstNoteAsModel);
            notesSet.add(secondNoteAsModel);
        }
        return notesSet;
    }

    /**
     * This method validating User String input request by all conditions
     *
     * @param userInput String - input request
     * @return "true" if valid "false" if not
     */
    @Override
    public boolean checkIfValid(String userInput) {

        boolean isValidCurrency = false;
        boolean isValidValue = false;
        boolean isValidNumber = false;
        boolean isUppercase;
        String[] splitInput = userInput.split(" ");

        String currency = splitInput[1];
        String value = splitInput[2];
        String number = splitInput[3];

        char[] currencyAsChars = currency.toCharArray();

        isUppercase = isStringContainsUpperCase(currencyAsChars);

        if (currencyAsChars.length == 3 && isUppercase) {
            isValidCurrency = true;
        }

        // if value contains only digits
        if (value.matches("^[0-9]+$")) {

            Set<NotesModel> notesSet = getGeneratedNotes();
            int valueAsInt = Integer.parseInt(value);

            // if values is one of the atm notes
            for (NotesModel note : notesSet) {
                if (note.getNoteValue() == valueAsInt) {
                    isValidValue = true;
                    break;
                }
            }
        }

        // if number contains only digits
        if (number.matches("^[0-9]+$")) {
            isValidNumber = true;
        }
        return isValidCurrency && isValidValue && isValidNumber;
    }

    public void createAddNoteModel(boolean isValidResult, String userInput) {
        if (isValidResult) {
            createModel(userInput);
        } else {
            globalLoggerService.printLog(Constants.ERROR);
        }
    }

    /**
     * This method creates AddNotes model and set it to Customer Account
     *
     * @param validInput - valid user input request
     */
    private void createModel(String validInput) {
        String[] splitInput = validInput.split(" ");

        String currency = splitInput[1];
        String value = splitInput[2];
        String number = splitInput[3];

        int valueAsInt = Integer.parseInt(value);
        int numberAsInt = Integer.parseInt(number);

        ArrayList<AddNotesModel> addNotesModelAsList = new ArrayList<>(Collections.emptyList());
        AddNotesModel addNotesModel = new AddNotesModel(currency, valueAsInt, numberAsInt);
        addNotesModelAsList.add(addNotesModel);
        atmService.customerAccount.setAddNotesModelAsList(addNotesModelAsList);
        globalLoggerService.printLog(Constants.OK);
    }

    private boolean isStringContainsUpperCase(char[] charArr) {
        for (char c : charArr) {
            if (!Character.isUpperCase(c))
                return false;
        }
        return true;
    }
}