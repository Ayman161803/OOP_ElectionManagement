package com.management.populace;


public class Candidate extends Citizen {
    private String alliedPartyName;
    private boolean hasWon= false;
    private final double popularityScore;

    public Candidate(String name, String address, String gender, int age, String DOB, int constituencyNum) {
        super(name,DOB,age,gender,address,constituencyNum);
        this.alliedPartyName = null;
        this.popularityScore=5+(int)(Math.random()*5)+Math.random();
    }

    public Candidate(String name, String DOB, int age, String gender, String address, int constituencyNum,String AlliedPartyName) {
        super(name,DOB,age,gender,address,constituencyNum);
        this.alliedPartyName = AlliedPartyName;
        this.popularityScore=5+(int)(Math.random()*5)+Math.random();
    }

    public void setAlliedParty(String alliedParty) {
        this.alliedPartyName = alliedParty;
    }

    public String toString(){
        return "Name : "+this.getName()+"\n"+"Party : "+alliedPartyName;
    }

    public void setHasWon(boolean bool){
        this.hasWon=bool;
    }

    public boolean hasWon(){
        return this.hasWon;
    }

    public double getPopularityScore() {
        return popularityScore;
    }

    public String getAlliedPartyName() {
        return alliedPartyName;
    }
}
