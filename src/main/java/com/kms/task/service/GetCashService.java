package com.kms.task.service;

import com.kms.task.model.AddNotesModel;
import com.kms.task.model.CurrencyModel;
import com.kms.task.model.CustomerAccount;
import com.kms.task.model.GetCashModel;
import com.kms.task.service.Interface.IGetCashService;
import com.kms.task.utils.Constants;
import com.kms.task.utils.GlobalLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GetCashService implements IGetCashService {

    @Autowired
    private MainDisplayService mainDisplayService;

    @Autowired
    private ATMService atmService;

    @Autowired
    private GlobalLoggerService globalLoggerService;

    int[] keysArray = new int[]{150, 100, 50, 30, 20, 10};

    @Override
    public String getCurrency(String userInput) {

        String[] splitInput = userInput.split(" ");
        return splitInput[1];
    }

    @Override
    public int getAmount(String userInput) {

        String[] splitInput = userInput.split(" ");
        String amount = splitInput[2];
        return Integer.parseInt(amount);
    }

    /**
     * By requested currency it generates AddNotes from Customer Account with requested currency
     * and passed to getGeneratedValues method
     * @param currency - from user request
     * @param amount - from user request
     * @return List of GetCashModels with <value> <number>
     */
    @Override
    public List<GetCashModel> getCash(String currency, int amount) {

        List<GetCashModel> getCashModelList = Collections.emptyList();

        CustomerAccount customerAccount = atmService.getCustomerAccount();

        List<AddNotesModel> addNotesModelAsList = customerAccount.getAddNotesModelAsList();

        List<AddNotesModel> addNotesWithValidCurrencyAsList = new java.util.ArrayList<>(Collections.emptyList());

        for (AddNotesModel addNotesModel : addNotesModelAsList) {
            if (addNotesModel.getCurrency().equals(currency)) {
                addNotesWithValidCurrencyAsList.add(addNotesModel);
            }
        }
        int sum = getCurrencySum(addNotesWithValidCurrencyAsList);
        if (amount > sum) {
            globalLoggerService.printLog(Constants.ERROR);
            mainDisplayService.mainDisplay();
        }

        int[] generatedValues = getGeneratedValues(addNotesWithValidCurrencyAsList, amount);

        if (generatedValues != null) {
            getCashModelList = generateGetCashModel(generatedValues);
        } else {
            globalLoggerService.printLog(Constants.ERROR);
            mainDisplayService.mainDisplay();
        }
        return getCashModelList;
    }

    /**
     * This method subtracting the requested amount from Customer Account
     * @param getCashModelList - Response from getCash request
     * @param currency - currency from the user request
     */
    @Override
    public void subtractCash(List<GetCashModel> getCashModelList, String currency) {

        ArrayList<AddNotesModel> getCashRequest = new ArrayList<>(Collections.emptyList());
        for (GetCashModel getCashModel : getCashModelList) {
            if (getCashModel.getNumber() > 0) {
                AddNotesModel addNotesModel = new AddNotesModel();
                addNotesModel.setCurrency(currency);
                addNotesModel.setNumber(getCashModel.getNumber());
                addNotesModel.setValue(getCashModel.getValue());
                getCashRequest.add(addNotesModel);
            }
        }

        CustomerAccount customerAccount = atmService.getCustomerAccount();
        List<AddNotesModel> addNotesModelAsList = customerAccount.getAddNotesModelAsList();

        ArrayList<AddNotesModel> mergedList = new ArrayList<>();
        for (AddNotesModel p : addNotesModelAsList) {
            int index = mergedList.indexOf(p);
            if (index != -1) {
                mergedList.set(index, mergedList.get(index).merge(p));
            } else {
                mergedList.add(p);
            }
        }

        for (AddNotesModel notesModel : getCashRequest) {
            for (AddNotesModel addNotesModel : mergedList) {
                if (notesModel.getCurrency().equals(addNotesModel.getCurrency()) &&
                        notesModel.getValue() == addNotesModel.getValue()) {
                    addNotesModel.setNumber(addNotesModel.getNumber() - notesModel.getNumber());
                }
            }
        }
        customerAccount.setAddNotesModelAsListAfterSubtract(mergedList);
        atmService.setCustomerAccount(customerAccount);
    }

    private List<GetCashModel> generateGetCashModel(int[] generatedValues) {

        ArrayList<GetCashModel> getCashModelList = new ArrayList<>(Collections.emptyList());
        for (int i = 0; i < generatedValues.length; i++) {
            GetCashModel newGetCashModel = new GetCashModel();
            newGetCashModel.setNumber(generatedValues[i]);
            newGetCashModel.setValue(keysArray[i]);
            getCashModelList.add(newGetCashModel);
        }
        return getCashModelList;
    }

    private int[] getGeneratedValues(List<AddNotesModel> addNotesWithValidCurrencyAsList, int amount) {
        CurrencyModel currencyModel = new CurrencyModel();

        for (AddNotesModel addNotesWithValidCurrency : addNotesWithValidCurrencyAsList) {

            if (addNotesWithValidCurrency.getValue() == 10) {
                currencyModel.setTen(addNotesWithValidCurrency.getNumber() + currencyModel.getTen());
            }
            if (addNotesWithValidCurrency.getValue() == 20) {
                currencyModel.setTwenty(addNotesWithValidCurrency.getNumber() + currencyModel.getTwenty());
            }
            if (addNotesWithValidCurrency.getValue() == 30) {
                currencyModel.setThirty(addNotesWithValidCurrency.getNumber() + currencyModel.getThirty());
            }
            if (addNotesWithValidCurrency.getValue() == 50) {
                currencyModel.setFifty(addNotesWithValidCurrency.getNumber() + currencyModel.getFifty());
            }
            if (addNotesWithValidCurrency.getValue() == 100) {
                currencyModel.setOneHundred(addNotesWithValidCurrency.getNumber() + currencyModel.getOneHundred());
            }
            if (addNotesWithValidCurrency.getValue() == 150) {
                currencyModel.setOneHundredAndFifty(addNotesWithValidCurrency.getNumber() + currencyModel.getOneHundredAndFifty());
            }
        }

        // creating all existing keys
        int[] keysArray = new int[6];
        keysArray[0] = 150;
        keysArray[1] = 100;
        keysArray[2] = 50;
        keysArray[3] = 30;
        keysArray[4] = 20;
        keysArray[5] = 10;

        int[] valuesArray = new int[6];
        valuesArray[0] = currencyModel.getOneHundredAndFifty();
        valuesArray[1] = currencyModel.getOneHundred();
        valuesArray[2] = currencyModel.getFifty();
        valuesArray[3] = currencyModel.getThirty();
        valuesArray[4] = currencyModel.getTwenty();
        valuesArray[5] = currencyModel.getTen();

        int indexOfSingleReturnedValue = -1;
        boolean shouldContinueLooping = true;
        int fullSum = 0;

        for (int i = 0; i < valuesArray.length; i++) {

            if (valuesArray[i] < 1) {
                continue;
            }

            // Iterating over the number of the values from max to min
            for (int j = valuesArray[i]; j > -1; j--) {
                valuesArray[i] = j;
                int multiplied = j * keysArray[i];
                fullSum = multiplied;
                for (int k = 0; k < valuesArray.length; k++) {
                    if (k != i) {
                        fullSum = fullSum + (keysArray[k] * valuesArray[k]);
                    }
                }
                if (multiplied == amount) {
                    indexOfSingleReturnedValue = i;
                    break;
                }
                if (fullSum == amount) {
                    shouldContinueLooping = false;
                    break;
                } else if (fullSum < amount) {
                    break;
                }

                int fullSumAfterDecrementing = 0;

                for (int k = 0; k < valuesArray.length; k++) {

                    if (k == i) {
                        fullSumAfterDecrementing = fullSumAfterDecrementing + ((j - 1) * keysArray[i]);
                    } else {
                        fullSumAfterDecrementing = fullSumAfterDecrementing + (keysArray[k] * valuesArray[k]);
                    }
                }

                if (fullSumAfterDecrementing < amount) {
                    break;
                }
            }
            if (indexOfSingleReturnedValue > -1 || !shouldContinueLooping) {
                break;
            }
        }

        // if the amount exist in signal <value> <number>
        if (indexOfSingleReturnedValue > -1) {
            for (int i = 0; i < valuesArray.length; i++) {
                if (i != indexOfSingleReturnedValue) {
                    valuesArray[i] = 0;
                }
            }
            return valuesArray;
        } else if (fullSum == amount) {
            return valuesArray;
        }
        return null;
    }

    private int getCurrencySum(List<AddNotesModel> addNotesWithValidCurrencyAsList) {
        int sum = 0;
        for (AddNotesModel addNotesWithValidCurrency : addNotesWithValidCurrencyAsList) {
            sum = sum + addNotesWithValidCurrency.getValue() * addNotesWithValidCurrency.getNumber();
        }
        return sum;
    }

    public void printGetCashResult(List<GetCashModel> getCashModelList) {
        for (GetCashModel getCashModel : getCashModelList) {
            if (getCashModel.getNumber() > 0) {
                globalLoggerService.printLog(getCashModel.getValue() + " " + getCashModel.getNumber());
            }
        }
        globalLoggerService.printLog(Constants.OK);
    }
}