import java.util.ArrayList;

public class VoterManagementDesk {
    ArrayList<Voter> voterList;
    ArrayList<String> status;

    public int getCount(){
        return voterList.size();
    }

    public void registerIndividual(Citizen citizen){
        if(citizen.isEligible()){
            Voter voterGettingAddedToList=new Voter(citizen.getName(),citizen.getAddress(), citizen.getAadharNumber(), citizen.getGender(),citizen.getAge(), citizen.getDOB(), voterList.size()+1);
            voterList.add(voterGettingAddedToList);
            status.add("registered");
        }
        else{
            if(citizen.getAge()<18){
                Voter voterGettingAddedToList=new Voter(citizen.getName(),citizen.getAddress(), citizen.getAadharNumber(), citizen.getGender(),citizen.getAge(), citizen.getDOB(), voterList.size()+1);
                voterList.add(voterGettingAddedToList);
                status.add("UnderAge hence cannot be registered.");
            }
            else if(citizen.isAadharNumberValid()){
                Voter voterGettingAddedToList=new Voter(citizen.getName(),citizen.getAddress(), citizen.getAadharNumber(), citizen.getGender(),citizen.getAge(), citizen.getDOB(), voterList.size()+1);
                voterList.add(voterGettingAddedToList);
                status.add("Aadhar number invalid ");
            }
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

    //removes all entries with status other than registered
    public void finaliseVoterList(){

    }

    public void displayVotedRegistered(){
        for(int i=0;i<voterList.size();i++)
            System.out.println(voterList.get(i));
    }

    public void generateVoterID(Voter voter){

    }


}
