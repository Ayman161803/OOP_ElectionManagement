import java.util.Map;
import java.util.Scanner;

public class SurveyAgency {
    private String name;
    private Map<String,Integer> nameOfConstituencyToNumber;
    private double[][] ideologyTracker;
    private int[] noOfSurveysTaken;

    private void buildAgency(){}

    //resets the ideology tracker according to the survey taken
    public void resetIdeologyTracker(double one,double two,double three,double four,double five){

    }

    public String getName(){return this.name;}

    //returns projected winner for that constituencies
    public Contestant getProjectedWinner(Constituency constituency){
    }

    //returns the chief minister predicted
    public Contestant projectedCheifMinister(){

    }
}
