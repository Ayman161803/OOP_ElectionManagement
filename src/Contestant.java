public class Contestant extends Citizen{
    private int voteCount;
    private Party partyAlliedWith;
    private int countLead;

    public Contestant(String name, String DOB, String fatherName, String gender, int age, int aadharNumber, String address, boolean voted, boolean isEligible) {
        super(name, DOB, fatherName, gender, age, aadharNumber, address, voted, isEligible);
    }

    public void applyForContesting(Constituency  constituency){

    }

    public int getVoteCount() {
        return voteCount;
    }

}
