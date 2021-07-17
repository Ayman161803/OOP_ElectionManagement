import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {
    ArrayList<Candidate> partyMembers;
    Candidate chiefMinisterFace;
    String name;

    public Party(String name) {
        this.name = name;
        this.chiefMinisterFace=null;
    }

    public Party(Candidate chiefMinisterFace, String name) {
        this.chiefMinisterFace = chiefMinisterFace;
        this.name = name;
    }

    public Candidate showCM(){
        if(this.chiefMinisterFace==null)
            return partyMembers.get((int)(Math.random()*partyMembers.size()));
        return chiefMinisterFace;
    }

    public void buildParty(String filename){
        File myObj = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data==null){
                    continue;
                }
                String[] candidateData=data.split(",");
                partyMembers.add(new Candidate(candidateData[0],candidateData[1],candidateData[3],candidateData[4],Integer.parseInt(candidateData[5]),candidateData[6],(candidateData[7]),Integer.parseInt(candidateData[8])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeCMto(Candidate candidate){
        this.chiefMinisterFace=candidate;
    }


}
