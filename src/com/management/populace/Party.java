package com.management.populace;

import com.management.Voter;
import com.management.populace.Candidate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {
    ArrayList<Candidate> partyMembers;
    Candidate chiefMinisterFace;
    String name;
    String manifesto;

    public Party(Candidate chiefMinisterFace, String name) {
        this.chiefMinisterFace = chiefMinisterFace;
        this.name = name;
    }

    public Candidate showCM(){
        return chiefMinisterFace;
    }

    public void buildParty(String filename){
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
                String[] candidateData=data.split(",");
                partyMembers.add(new Candidate(candidateData[0],candidateData[1],candidateData[2],Integer.parseInt(candidateData[3]),candidateData[4],Integer.parseInt(candidateData[5]),Integer.parseInt(candidateData[6]),candidateData[7]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeCMto(Candidate candidate){
        this.chiefMinisterFace=candidate;
    }

    public void buildManifesto(String filename){
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
                this.manifesto+=data;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getSeatsWon(){
        int length=partyMembers.size(),count=0;
        for(int i=0;i<length;i++){
            if(partyMembers.get(i).hasWon())
                count++;
        }
        return count;
    }

    public double percentageOfSeatsWon(){
        return 100.00*this.getSeatsWon()/partyMembers.size();
    }

}
