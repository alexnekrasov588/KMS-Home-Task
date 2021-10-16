package com.kms.task.service;

import com.kms.task.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MainDisplayService {

    @Autowired
    private ATMService atmService;

    /**
     * Here is the main display of the ATM
     * By User selected action there is implementation of ATMService
     */
    public void mainDisplay() {

        Scanner input = new Scanner(System.in);
        String selection;
        selection = input.nextLine();

        String userAction = checkUserAction(selection);

        if (userAction != null) {
            switch (userAction) {
                case Constants.PLUS_OPERATOR:
                    atmService.addNotes(selection);
                    break;
                case Constants.MINUS_OPERATOR:
                    atmService.getCash(selection);
                    break;
                case Constants.QUESTION_MARK:
                    atmService.printCash();
                    break;
            }
        } else {
            mainDisplay();
        }
    }

    private String checkUserAction(String selection) {

        Set<String> actions = new HashSet(Arrays.asList(Constants.PLUS_OPERATOR, Constants.MINUS_OPERATOR, Constants.QUESTION_MARK));
        String[] splitUserSelection = selection.split(" ");

        if (actions.contains(splitUserSelection[0])) {
            return splitUserSelection[0];
        }
        return null;
    }
}