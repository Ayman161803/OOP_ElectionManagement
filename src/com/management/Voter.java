package com.management;

import com.management.populace.Citizen;




public class Voter extends Citizen {

    private boolean hasVoted=false;

    public Voter(String name, String DOB, int age, String gender,String address,int constituencyNum) {
        super(name,DOB,age,gender,address,constituencyNum);
    }

    protected void markVoted() {
        this.hasVoted = true;
    }

    @Override
    public String toString() {
        return "Voter{name='" + this.getName() + '\'' + ", address='" + this.getName() + '\'' + ", aadharNumber='" + this.getAadharNumber() + '\'' + ", gender='" + this.getGender() + '\'' + ", age=" + this.getAge() + ", DOB='" + this.getDOB() + '\''  + ", hasVoted=" + hasVoted + '}';
    }

    public boolean hasVoted(){return this.hasVoted;}
}
