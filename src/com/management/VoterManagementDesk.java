package com.management;

import com.company.populace.Citizen;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class VoterManagementDesk {
    private static ArrayList<Voter> voterList;

    public int getCount(){
        return voterList.size();
    }

    public static String registerIndividual(String AadharNumber){
        Citizen citizen=returnCitizenWithAadharNumber(AadharNumber);
        String fileName="Constituency"+AadharNumber.charAt(AadharNumber.length()-2)+"Voters.txt";
        if(citizen==null){
            return "Error : AadharID not found.";
        }
        else if (!isEligibleByAge(citizen)){
            return "Error : Age of the citizen is below 18";
        }
        else{
            {
                try {
                    String str=citizen.getName()+"|"+citizen.getAddress()+"|"+citizen.getGender()+"|"+citizen.getAge()+"|"+citizen.getDOB()+"|"+citizen.getConstituency();
                    // Open given file in append mode.
                    BufferedWriter out = new BufferedWriter(
                            new FileWriter(fileName, true));
                    out.write(str);
                    out.close();
                }
                catch (IOException e) {
                    System.out.println("exception occoured" + e);
                }
            }
            return "Registration successful!";
        }
    }

    private static Citizen returnCitizenWithAadharNumber(String AadharNumber){
        String filename="Constituency"+AadharNumber.charAt(AadharNumber.length()-2)+".txt";
        File myObj = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data==null){
                    continue;
                }
                String[] citizenData=data.split("\\|");
                if(citizenData[citizenData.length-1].equals(AadharNumber)) {
                    System.out.println(citizenData[2]);
                    return new Citizen(citizenData[0], citizenData[1], Integer.parseInt(citizenData[2]), (citizenData[3]), citizenData[4], Integer.parseInt(citizenData[5]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isAadharNumberValid(String AadharNumber){
        char[] numbers=AadharNumber.toCharArray();
        int checkSum=0;
        for(int i=0;i<10;i++){
            checkSum+=(i+1)*Integer.parseInt(String.valueOf(numbers[i]));
        }
        return checkSum%11==0;
    }

    public int membersBetweenAge(int lowestAge , int highestAge){
        int count=0;
        for(int i=0;i<voterList.size();i++){
            if(voterList.get(i).getAge()>=lowestAge && voterList.get(i).getAge()<=highestAge)
                count++;
        }
        return count;
    }

    public int memberOfGender(String gender){
        int count=0;
        for(int  i=0;i<voterList.size();i++){
            Voter voter= voterList.get(i);
            if(voter.getGender().toLowerCase(Locale.ROOT).equals(gender.toLowerCase(Locale.ROOT)))
                count++;
        }
        return count;
    }

    public void displayVotersRegistered(){
        for(int i=0;i<voterList.size();i++)
            System.out.println(voterList.get(i));
    }

    private static boolean isEligibleByAge(Citizen citizen){
        return citizen.getAge()>=18;
    }

    public void buildList(String filename){
        File myObj = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data==null){
                    continue;
                }
                String[] citizenData=data.split(",");
                voterList.add(new Voter(citizenData[0],citizenData[1],citizenData[2],Integer.parseInt(citizenData[3]),citizenData[4],Integer.parseInt(citizenData[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
