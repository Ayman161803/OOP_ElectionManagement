package com.company;

public class Candidate extends Citizen{
    private String alliedPartyName;
    private final String constituencyName;
    private final int candidateID;
    private boolean hasWon;

    public Candidate(String name, String address, String aadharNumber, String gender, int age, String DOB, String constituency, int candidateID) {
        super(name, address, aadharNumber, gender, age, DOB);
        this.alliedPartyName = null;
        this.constituencyName = constituency;
        this.candidateID = candidateID;
    }

    public Candidate(String name, String address, String aadharNumber, String gender, int age, String DOB, String constituency, int candidateID,String AlliedPartyName) {
        super(name, address, aadharNumber, gender, age, DOB);
        this.alliedPartyName = AlliedPartyName;
        this.constituencyName = constituency;
        this.candidateID = candidateID;
    }

    public void setAlliedParty(String alliedParty) {
        this.alliedPartyName = alliedParty;
    }

    public String toString(){
        return "Name : "+this.getName()+"\n"+"Party : "+alliedPartyName;
    }

    protected void setHasWon(boolean bool){
        this.hasWon=bool;
    }

    public boolean hasWon(){
        return this.hasWon;
    }
}
