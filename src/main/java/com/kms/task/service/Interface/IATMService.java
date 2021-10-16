package com.kms.task.service.Interface;

import com.kms.task.model.CustomerAccount;

public interface IATMService {

    void addNotes(String userInput);

    void getCash(String userInput);

    void printCash();

    CustomerAccount getCustomerAccount();

    void setCustomerAccount(CustomerAccount customerAccount);
}
