
import java.util.ArrayList;

public class Constituency {
    private String Name;
    private ArrayList<Citizen> population=new ArrayList<Citizen>();
    private ArrayList<Contestant> candidates=new ArrayList<Contestant>();
    private int populationCount;
    private VotingCentre[] votingCentres;

    //returns the nearest VotingCentre. Planning to use google maps API to encode address
    public VotingCentre nearestVotingCentre(Citizen citizen) {

    }

    //returns a bool corresponding to whether the candidate is eligible for elections or not
    private boolean isCandidateEligible(Contestant contestant){

    }

    public void takeContestantsApplications(String filename){

    }

    //add contestant to the list of candidates
    private void addContestant(Contestant contestant){
        this.candidates.add(contestant);
    }

    //building the entire constituency from the citizens data in txt file
    //accordingly populationCount is updated and the number of voting centres are decided
    public void buildConstituency(String fileName ){

    }

    //create voting centres at optimal locations depending on the population count
    public void createVotingCentres(){}

    public double voterTurnout(){ }

    public double percentageRegistered(){ }
}
