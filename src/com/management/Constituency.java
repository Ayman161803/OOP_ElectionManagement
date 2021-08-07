package com.management;

import com.management.VoterManagementDesk;
import com.management.populace.Citizen;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Constituency implements Desk{
    private  String Name;
    private VoterManagementDesk voterManagementDesk;
    private com.management.PollingManagementDesk pollingManagementDesk;
    private ArrayList<Citizen> citizens;
    private int noOfEligibleCitizens=0;

    public Constituency(String name) {
        citizens=new ArrayList<>();
        Name = name;
        voterManagementDesk=new VoterManagementDesk();
        pollingManagementDesk=new PollingManagementDesk();
        this.build(name);
    }

    public Constituency(){}

    public String registerIndividual(String data){
        return this.addToList(data);
    }

    public String addToList(String data){
        String filename="Constituency"+data.charAt(data.length()-1)+".txt";
        String[] citizenData=data.split("\\|");
        Citizen citizenConcerned=new Citizen(citizenData[0],citizenData[1],Integer.parseInt(citizenData[2]),(citizenData[3]),citizenData[4],Integer.parseInt(citizenData[5]));
        try {
            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(filename, true));
            out.write(citizenConcerned.toString()+"\n");
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
        return "AadharID generated : "+citizenConcerned.getAadharNumber();
    }

    @Override
    public void openRegistrationPortal() {
        new CitizenRegistrationForm();
    }

    @Override
    public Citizen returnIndividualWithAadharID(String AadharID) {
        return null;
    }

    public double percentageRegistered(){
        return this.voterManagementDesk.getCount()*100.00/noOfEligibleCitizens;
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

    public int getNoOfEligibleCitizens(){
        return noOfEligibleCitizens;
    }

    public int getCount(){return citizens.size();}

    @Override
    public void build(String filename) {
            File myObj;
            myObj = new File(this.Name+".txt");
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
                    citizens.add(new Citizen(citizenData[0],citizenData[1],Integer.parseInt(citizenData[2]),(citizenData[3]),citizenData[4],Integer.parseInt(citizenData[5])));
                    if(Integer.parseInt(citizenData[2])>=18)
                        noOfEligibleCitizens++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            voterManagementDesk.build(this.Name+"Voters.txt");
            pollingManagementDesk.build(this.Name+"Candidates.txt");
    }

    public String getName() {
        return Name;
    }

    public VoterManagementDesk getVoterManagementDesk() {
        return voterManagementDesk;
    }

    public PollingManagementDesk getPollingManagementDesk() {
        return pollingManagementDesk;
    }
}
