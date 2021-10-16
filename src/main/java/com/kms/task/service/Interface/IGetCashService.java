package com.kms.task.service.Interface;

import com.kms.task.model.GetCashModel;

import java.util.List;

public interface IGetCashService {

    String getCurrency(String userInput);

    int getAmount(String userInput);

    List<GetCashModel> getCash(String currency, int amount);

    void subtractCash(List<GetCashModel> getCashModelList, String currency);
}