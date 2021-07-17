import java.util.ArrayList;
import java.util.List;

public class Voter extends Citizen{

    private long VoterID;
    private boolean hasVoted=false;
    private int pollingBoothNumber;

    public Voter(String name, String address, String aadharNumber, String gender, int age, String DOB,long voterID) {
        super(name,address,aadharNumber,gender,age,DOB);
        VoterID = voterID; }

    public void markVoted() {
        this.hasVoted = true;
    }

    @Override
    public String toString() {
        return "Voter{name='" + this.getName() + '\'' + ", address='" + this.getName() + '\'' + ", aadharNumber='" + this.getAadharNumber() + '\'' + ", gender='" + this.getGender() + '\'' + ", age=" + this.getAge() + ", DOB='" + this.getDOB() + '\''  + "VoterID=" + VoterID + ", hasVoted=" + hasVoted + '}';
    }

    public void assignBooth(PollingBooth pollingBooth){
        this.pollingBoothNumber=pollingBooth.getID();
    }

    public void generateVoterIDCard(){}

    public boolean hasVoted(){return this.hasVoted;}
}
