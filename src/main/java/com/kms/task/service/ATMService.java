package com.kms.task.service;

import com.kms.task.model.CustomerAccount;
import com.kms.task.model.GetCashModel;
import com.kms.task.service.Interface.IATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATMService implements IATMService {

    @Autowired
    private MainDisplayService mainDisplayService;

    @Autowired
    private AddNotesService addNotesService;

    @Autowired
    private GetCashService getCashService;

    @Autowired
    private PrintCashService printCashService;

    CustomerAccount customerAccount = new CustomerAccount();

    /**
     * Here is the implementation of AddNotes Interface.
     * This method adds new notes to Customer Account.
     *
     * @param userInput User input from the console - <currency> <values> <number>
     */
    @Override
    public void addNotes(String userInput) {

        boolean isValidInput = addNotesService.checkIfValid(userInput);

        addNotesService.createAddNoteModel(isValidInput, userInput);

        mainDisplayService.mainDisplay();
    }

    /**
     * Here is the implementation of GetCash Interface.
     * This method withdraws cash from Customer Account by user request.
     *
     * @param userInput User input - <currency> <amount>
     */
    @Override
    public void getCash(String userInput) {

        String currency = getCashService.getCurrency(userInput);

        int amount = getCashService.getAmount(userInput);

        List<GetCashModel> getCashModelList = getCashService.getCash(currency, amount);

        getCashService.printGetCashResult(getCashModelList);

        getCashService.subtractCash(getCashModelList, currency);

        mainDisplayService.mainDisplay();
    }

    /**
     * Here is the implementation of PrintCash Interface
     * This method prints Customer Account values ordered by  <currency> <value> in asc order
     */
    @Override
    public void printCash() {

        printCashService.printCash();

        mainDisplayService.mainDisplay();
    }

    @Override
    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    @Override
    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }
}