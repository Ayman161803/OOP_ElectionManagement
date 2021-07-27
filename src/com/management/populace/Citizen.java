package com.management.populace;

import java.util.Locale;

public class Citizen {
    private String name;
    private String address;//FORMAT: Door number, street number, constituency-sector, constituencyName, pincode
    private String aadharNumber;
    private String gender;
    private int age;
    private String DOB;//in DD//MM//YYYY format
    private int constituency;

    public boolean isEligible(){
        return this.age>=18;
    }

    public Citizen(String name, String DOB,int age, String gender,String address,int constituencyNum) {
        int genderCode= gender.toLowerCase(Locale.ROOT).equals("male")?0:1;
        this.name = name;
        this.address = address;
        this.aadharNumber = generateAadharNumber(DOB+(constituencyNum-1),address)+genderCode;
        this.gender = gender;
        this.age = age;
        this.DOB = DOB;
        this.constituency=constituencyNum;
    }

    //data is DDMMYYYY<Constituencynum-1>
    private static String generateAadharNumber(String data,String address){
        address=address.trim();
        String[] DOBdata=data.split("/");
        String DDMMYYYYC="";
        for(int i=0;i<3;i++)
            DDMMYYYYC+=DOBdata[i];
        char[] isbn= new String(DDMMYYYYC).toCharArray();
        int checkSum=0;
        for(int i=10;i>=2;i--){
            checkSum+= i *Integer.parseInt(String.valueOf(isbn[10-i]));
        }
        for(int i=0;i<11;i++){
            if((checkSum+i)%11==0){
                checkSum=i;
                break;
            }
        }
        if(checkSum==0){
            long number=Long.parseLong(DDMMYYYYC)*10;
            return String.format("%010d",number)+address.charAt(0);
        }
        else if (checkSum > 0 && checkSum < 10) {
            long number=Long.parseLong(DDMMYYYYC)*10+checkSum;
            return String.format("%010d",number)+address.charAt(0);
        }
        else if(checkSum==10) {
            long number = Long.parseLong(DDMMYYYYC);
            return String.format("%09dX", number)+address.charAt(0);
        }
        return null;
    }

    public int getConstituency() {
        return constituency;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getDOB() {
        return DOB;
    }

    @Override
    public String toString() {
        return  name+"|"+DOB+"|"+age+"|"+gender+"|"+address+"|"+constituency+"|"+aadharNumber;
    }

    public void generateAadharCard(){}

}
