package com.company;

import com.company.VoterManagementDesk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Constituency {
    private final String Name;
    private VoterManagementDesk voterManagementDesk;
    private PollingManagementDesk pollingManagementDesk;
    private ArrayList<Citizen> citizens;

    public Constituency(String name) {
        Name = name;
        voterManagementDesk=new VoterManagementDesk();
        pollingManagementDesk=new PollingManagementDesk();
    }

    public void buildConstituency(String filename){
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
                citizens.add(new Citizen(citizenData[0],citizenData[1],citizenData[2],citizenData[3],Integer.parseInt(citizenData[4]),citizenData[5]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registerCitizen(Citizen citizenToBeAdded){
        citizens.add(citizenToBeAdded);
    }

    public double percentageRegistered(){
        return this.voterManagementDesk.getCount()*100.00/this.citizens.size();
    }

    public double percentageOfGender(String gender){
        gender=gender.toLowerCase(Locale.ROOT);
        int count=0;
        for(int i=0;i<citizens.size();i++){
            if(citizens.get(i).getName().toLowerCase(Locale.ROOT).equals(gender))
                count++;
        }
        return 100.00*count/citizens.size();
    }

    public double voterTurnOut(){
        return 100.00*this.pollingManagementDesk.getTotalNoOfVotes()/voterManagementDesk.getCount();
    }

    //shows result on command line
    public void showResult(){

    }

}
