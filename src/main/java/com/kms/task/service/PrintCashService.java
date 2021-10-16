package com.kms.task.service;

import com.kms.task.model.AddNotesModel;
import com.kms.task.model.CustomerAccount;
import com.kms.task.service.Interface.IPrintCash;
import com.kms.task.utils.Constants;
import com.kms.task.utils.GlobalLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PrintCashService implements IPrintCash {

    @Autowired
    private ATMService atmService;

    @Autowired
    private GlobalLoggerService globalLoggerService;

    @Override
    public void printCash() {

        CustomerAccount customerAccount = atmService.getCustomerAccount();
        ArrayList<AddNotesModel> addNotesModels = customerAccount.getAddNotesModelAsList();

        // Merging AddNotes Models by same value and currency
        if (addNotesModels.size() != 0) {
            ArrayList<AddNotesModel> mergedList = new ArrayList<>();
            for (AddNotesModel p : addNotesModels) {
                int index = mergedList.indexOf(p);
                if (index != -1) {
                    mergedList.set(index, mergedList.get(index).merge(p));
                } else {
                    mergedList.add(p);
                }
            }
            // This method use Comparator interface to sort AddNotes by currency and value in asc order
            order(mergedList);

            // Prints merged && ordered AddNotesModel List
            for (AddNotesModel addNotesModel : mergedList) {
                if (addNotesModel.getNumber() > 0) {
                    globalLoggerService.printLog(addNotesModel.getCurrency() + " " +
                            addNotesModel.getValue() + " " +
                            addNotesModel.getNumber());
                }
            }
            globalLoggerService.printLog(Constants.OK);
        }
    }

    private void order(List<AddNotesModel> addNotesModelAsList) {
        Collections.sort(addNotesModelAsList, new Comparator() {

            public int compare(Object o1, Object o2) {
                // Currency
                String x1 = ((AddNotesModel) o1).getCurrency();
                String x2 = ((AddNotesModel) o2).getCurrency();
                int sComp = x1.compareTo(x2);

                if (sComp != 0) {
                    return sComp;
                }
                // Value
                Integer z1 = ((AddNotesModel) o1).getValue();
                Integer z2 = ((AddNotesModel) o2).getValue();
                return z1.compareTo(z2);
            }
        });
    }
}