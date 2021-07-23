import com.company.Constituency;

import java.util.ArrayList;

public class State {
    ArrayList<Party>  parties;
    Constituency[] constituencies;

    public void buildState(){
        constituencies=new Constituency[10];
        for(int i=0;i<10;i++){
            constituencies[i]=new Constituency("Constituency"+(i+1));
        }
    }
}
