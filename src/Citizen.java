import javax.security.sasl.SaslClient;
import java.util.Scanner;

public class Citizen {
    private String name;
    private String DOB;
    private String fatherName;
    private String Gender;
    private int age;
    private int aadharNumber;
    private String address;
    private boolean voted=false;
    private boolean isEligible;
    private int voterID;

    public Citizen(String name, String DOB, String fatherName, String gender, int age, int aadharNumber, String address, boolean voted, boolean isEligible) {
        this.name = name;
        this.DOB = DOB;
        this.fatherName = fatherName;
        Gender = gender;
        this.age = age;
        this.aadharNumber = aadharNumber;
        this.address = address;
        this.voted = voted;
        this.isEligible = this.age>=18;
    }

    public void register(){
        this.voterID= (int) (1000000*Math.random());
    }

    //generateAadharCard
    public void generateAadharCard(){

    }

    //ouputs a voterID card in pdf/jpeg
    public void generateVoterID(){

    }

    public void giveSurvey(SurveyAgency agency){
        //questions to agree or disagree on then agency.updateIdeologyTracker()
    }
}
