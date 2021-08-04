package com.management;

public class AadharGenerator implements Form{
    public AadharGenerator(){
        VoterManagementDesk voterManagementDesk= new VoterManagementDesk();
        voterManagementDesk.genrateAadharCard("nb");
    }
    @Override
    public boolean isDataInFormat() {
        return false;
    }
}
