public class Candidate extends Citizen{
    private Party alliedParty;
    private final Constituency constituency;
    private final int candidateID;

    public Candidate(String name, String address, String aadharNumber, String gender, int age, String DOB, Party alliedParty, Constituency constituency, int candidateID) {
        super(name, address, aadharNumber, gender, age, DOB);
        this.alliedParty = alliedParty;
        this.constituency = constituency;
        this.candidateID = candidateID;
    }

    public void changePartyTo(Party party){
        this.alliedParty=party;
    }


}
