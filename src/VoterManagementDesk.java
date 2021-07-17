import java.util.ArrayList;
import java.util.Locale;

public class VoterManagementDesk {
    private static ArrayList<Voter> voterList;
    private static ArrayList<String> status;

    public int getCount(){
        return voterList.size();
    }

    public static void registerIndividual(Citizen citizen){
        if(isEligible(citizen)){
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

    public int memberOfGender(String gender){
        int count=0;
        for(int  i=0;i<voterList.size();i++){
            Voter voter= voterList.get(i);
            if(voter.getGender().toLowerCase(Locale.ROOT).equals(gender.toLowerCase(Locale.ROOT)))
                count++;
        }
        return count;
    }

    //removes all entries with status other than registered
    public void finaliseVoterList(){
        for(int i=0;i< voterList.size();i++){
            if(!status.get(i).equals("registered")){
                status.remove(i);
                voterList.remove(i);
            }
        }
    }

    public void displayVotersRegistered(){
        for(int i=0;i<voterList.size();i++)
            System.out.println(voterList.get(i));
    }

    private static boolean isEligible(Citizen citizen){
        return citizen.getAge()>=18;
    }
}
