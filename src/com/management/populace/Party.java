package com.management.populace;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {
    private ArrayList<Candidate> partyMembers;
    private Candidate chiefMinisterFace;
    private String name;

    public Party( String name) {
        this.name = name;
    }
    public void chooseCM(){
        double max= 0;
        int n = this.partyMembers.size();
        for (int i = 0; i < n; i++) {
            if(max<this.partyMembers.get(i).getPopularityScore()){
                max = partyMembers.get(i).getPopularityScore();
                this.chiefMinisterFace = partyMembers.get(i);
            }
        }

    }

    public Candidate showCM(){
        return chiefMinisterFace;
    }

    public String getCMNAme(){
        return  chiefMinisterFace.getName();
    }

    public String getName() {
        return name;
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
            this.chooseCM();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeCMto(Candidate candidate){
        this.chiefMinisterFace=candidate;
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
