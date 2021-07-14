import java.util.ArrayList;

public class Constituency {
    private String Name;
    VoterManagementDesk voterManagementDesk;
    ArrayList<PollingBooth> pollingBoothsList=null;
    Candidate[] candidates;

    public Constituency(String name) {
        Name = name;
    }

    //register voters into voterManagementDesk
    public void registerCandidates(String filename){
    }

    //builds the number of pollingBooths according to number of unique sectors in the constituency
    public void buildBooths(){

    }


}
