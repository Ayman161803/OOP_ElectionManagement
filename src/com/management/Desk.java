package com.management;

import com.management.populace.Citizen;

public interface Desk {
    int getCount();
    void build(String filename);
    String addToList(String data);
    void openRegistrationPortal();
    Citizen returnIndividualWithAadharID(String AadharID);
    String registerIndividual(String data);
    default boolean isAadharNumberValid(String AadharNumber){
        char[] numbers=AadharNumber.toCharArray();
        int checkSum=0;
        for(int i=0;i<9;i++){
            checkSum+=(i+1)*Integer.parseInt(String.valueOf(numbers[i]));
        }
        if(numbers[9]=='X'){
            checkSum+=10;
            return checkSum%11==0;
        }
        checkSum+=Integer.parseInt(String.valueOf(numbers[9]));
        return checkSum%11==0;
    }
}
