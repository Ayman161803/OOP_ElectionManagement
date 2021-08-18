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
        partyMembers=new ArrayList<Candidate>();
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

    public void add(Candidate candidate){
        partyMembers.add(candidate);
    }
    public Candidate showCM(){
        return chiefMinisterFace;
    }

    public String getCMName(){
        if(this.chiefMinisterFace==null){
            this.chooseCM();
        }
        return  chiefMinisterFace.getName();
    }

    public String getName() {
        return name;
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
