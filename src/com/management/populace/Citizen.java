package com.management.populace;

import com.management.Constituency;
import com.management.Desk;

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
        this.aadharNumber = generateAadharID(DOB, String.valueOf((constituencyNum-1)),address)+genderCode;
        this.gender = gender;
        this.age = age;
        this.DOB = DOB;
        this.constituency=constituencyNum;
    }

    private static String generateAadharID(String data,String ConstituencyNum,String address){
        address=address.trim();
        String[] DOBdata=data.split("/");
        String DDMMYYYYC="";
        for(int i=0;i<3;i++)
            DDMMYYYYC+=DOBdata[i];
        DDMMYYYYC+=ConstituencyNum;
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
        if(checkSum!=10){
            return DDMMYYYYC+(checkSum) +address.charAt(0);
        }
        else {
            return DDMMYYYYC+"X" +address.charAt(0);
        }
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

}
