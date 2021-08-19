package com.management;

import com.management.populace.Citizen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface Desk {
    int getCount();
    void build(String filename);
    String addToList(String data);
    String registerIndividual(String data);
    default boolean isAadharNumberValid(String AadharNumber){
        char[] numbers=AadharNumber.toCharArray();
        int checkSum=0;
        for(int i=0;i<9;i++){
            checkSum+=(i+1)*Integer.parseInt(String.valueOf(numbers[i]));
        }
        if(numbers[9+AadharNumber.length()-12]=='X'){
            checkSum+=10;
            return checkSum%11==0;
        }
        checkSum+=Integer.parseInt(String.valueOf(numbers[9+AadharNumber.length()-12]));
        return checkSum%11==0;
    }
    static String generateAadharID(String data,String ConstituencyNum,String address){
        address=address.trim();
        String[] DOBdata=data.split("/");
        String DDMMYYYYC="";
        for(int i=0;i<3;i++)
            DDMMYYYYC+=DOBdata[i];
        DDMMYYYYC+=ConstituencyNum.charAt(0);
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
        if(checkSum!=0){
            long number=Long.parseLong(DDMMYYYYC)*10;
            return DOBdata+String.valueOf(ConstituencyNum)+String.valueOf(checkSum) +address.charAt(0);
        }
        else {
            long number = Long.parseLong(DDMMYYYYC);
            return DOBdata+String.valueOf(ConstituencyNum)+"X" +address.charAt(0);
        }
    }

     default Citizen returnIndividualWithAadharID(String AadharID) {
        File myObj;
        String numberConstituency="";
        for(int i=8;i<=8+AadharID.length()-12;i++){
            numberConstituency+=AadharID.charAt(i);
        }
        myObj = new File("CitizenData/Constituency"+numberConstituency+".txt");
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data==null){
                    continue;
                }
                System.out.println(data);
                String[] citizenData =data.split("\\|");
                if(citizenData[citizenData.length-1].equals(AadharID.trim())){
                    System.out.print(data);
                    return (new Citizen(citizenData[0], citizenData[1], Integer.parseInt(citizenData[2]), (citizenData[3]), citizenData[4], Integer.parseInt(citizenData[5])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
