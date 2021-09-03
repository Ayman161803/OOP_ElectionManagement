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
                String[] citizenData =data.split("\\|");
                if(citizenData[citizenData.length-1].equals(AadharID.trim())){
                    return (new Citizen(citizenData[0], citizenData[1], Integer.parseInt(citizenData[2]), (citizenData[3]), citizenData[4], Integer.parseInt(citizenData[5])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
