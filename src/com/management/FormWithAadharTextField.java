package com.management;

public abstract class FormWithAadharTextField implements Form{
    @Override
    public boolean isDataInFormat(String aadhar) {
        if(aadhar.length()<12){
            return false;
        }
        for(int i = 0; i<aadhar.length(); i++){
            if((!(aadhar.charAt(i)>='0'&&aadhar.charAt(i)<='9')) && aadhar.charAt(i)!='X')
                return false;
        }
        return true;
    }
}
