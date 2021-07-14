import java.util.ArrayList;

public class VoterManagementDesk {
    ArrayList<Voter> voterList;

    public int getCount(){
        return voterList.size();
    }

    public void registerIndividual(Citizen citizen){
        if(citizen.isEligible()){
            Voter voterGettingAddedToList=new Voter(citizen.getName(),citizen.getAddress(), citizen.getAadharNumber(), citizen.getGender(),citizen.getAge(), citizen.getDOB(), voterList.size()+1);
            voterList.add(voterGettingAddedToList);
        }
    }

    public ArrayList<Voter> getVoterList() {
        return voterList;
    }

    public int membersBetweenAge(int lowestAge , int highestAge){
        int count=0;
        for(int i=0;i<voterList.size();i++){
            if(voterList.get(i).getAge()>=lowestAge && voterList.get(i).getAge()<=highestAge)
                count++;
        }
        return count;
    }

    public void displayVotedRegistered(){
        for(int i=0;i<voterList.size();i++)
            System.out.println(voterList.get(i));
    }

    public void generateVoterID(Voter voter){

    }


}
