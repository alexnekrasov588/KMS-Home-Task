package com.kms.task;

import com.kms.task.service.ATMService;
import com.kms.task.service.AddNotesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AddNotesTests {

    @Autowired
    AddNotesService addNotesService;

    @Autowired
    ATMService atmService;

    @Test
    void checkIfValidTest_Success() {
        String input = "+ USD 150 8";
        boolean res = addNotesService.checkIfValid(input);
        assertTrue(res);
    }

    @Test
    void checkIfValidTest_Fails() {
        String input = "+ USa 150 8";
        boolean res = addNotesService.checkIfValid(input);
        assertFalse(res);
    }

//    @Test
//    void checkAddNotes() {
//        String input = "+ USA 150 8";
//        int val = 150;
//        int num = 8;
//        atmService.addNotes(input);
//        CustomerAccount ca = atmService.getCustomerAccount();
//        List<AddNotesModel> addNotesList = ca.getAddNotesModelAsList();
//        AddNotesModel addNotesModel = addNotesList.get(0);
//        assertEquals(input.substring(3, 6), addNotesModel.getCurrency());
//        assertEquals(val, addNotesModel.getValue());
//        assertEquals(num, addNotesModel.getNumber());
//    }
}
