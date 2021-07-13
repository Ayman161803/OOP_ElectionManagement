public class VotingCentre {
    private String address;
    private boolean isActive;

    public void Close() {
        isActive = false;
    }

    public void Open(){
        this.isActive=true;
    }

    public void vote(Citizen citizen){

    }

    public VotingCentre(String address) {
        this.address = address;
    }

}
