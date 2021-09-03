import com.management.*;

import java.io.File;
import java.util.Locale;
import java.util.Objects;

public class PreElection {
    public static void main(String[] args) {
        if(args[0].toLowerCase(Locale.ROOT).equals(("openCandidateAdditionPortal").toLowerCase(Locale.ROOT))){
            new CandidateAdditionPortal();
        }
        else if ((args[0].toLowerCase(Locale.ROOT).equals(("openVoterRegistrationPortal").toLowerCase(Locale.ROOT)))){
            new VoterRegistrationForm();
        }
        else if ((args[0].toLowerCase(Locale.ROOT).equals(("openAadharGenerator").toLowerCase(Locale.ROOT)))){
            new AadharGenerator();
        }
        else if ((args[0].toLowerCase(Locale.ROOT).equals(("openCitizenRegistrationPortal").toLowerCase(Locale.ROOT)))){
            new CitizenRegistrationForm();
        }
        else if((args[0].toLowerCase(Locale.ROOT).equals(("releaseCandidateLists").toLowerCase(Locale.ROOT)))){
            File candidateDirectory=new File("CandidateData");
            int noOfConstituencies= Objects.requireNonNull(candidateDirectory.list()).length;
            for (int i = 0; i < noOfConstituencies; i++) {
                System.out.println(i);
                String filename="CandidateData/Constituency"+i+"Candidates.txt";
                PollingManagementDesk pollingManagementDesk =new PollingManagementDesk(String.valueOf(i));
                pollingManagementDesk.build(filename);
                System.out.println(filename);
                pollingManagementDesk.releaseCandidateList();
            }

        }
        else{
            System.out.println("Invalid Command line argument entered.");
        }
    }
}
