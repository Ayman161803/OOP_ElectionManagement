import java.io.File;

public class PollingBooth {
    private int ID;
    private String name;

    public PollingBooth(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    //takes BalllotPaper in and counts votes
    public void processBallotPaper(String filename){

    }

    private void vote(Voter voter){
        voter.markVoted();
    }

    public int getID() {
        return ID;
    }
}
