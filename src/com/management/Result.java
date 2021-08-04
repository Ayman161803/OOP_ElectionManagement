package com.management;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.management.populace.Candidate;
import com.management.populace.Citizen;
import com.management.populace.Party;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Result {

     ArrayList<Constituency> constituencies;
     ArrayList<Party> parties;

    public Result(PollingBooth pollingBooth){
        String str = "Constituency";
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            constituencies.add(new Constituency(str+i));
            constituencies.get(i).build();
            ArrayList<Candidate> obj =  constituencies.get(i).getPollingManagementDesk().getCandidates();
            int n = obj.size();
            for (int j = 0; j < n; j++) {
                if(!arr.contains(obj.get(j).getAlliedPartyName())){
                    arr.add(obj.get(j).getAlliedPartyName());
                }
            }
        }
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            parties.add(new Party(arr.get(i)));
        }
    }

    public int winnerParty(){
        int max =0,index=0;
        for (int i = 0; i < this.parties.size(); i++) {
            if(max<parties.get(i).getSeatsWon()){
                max = parties.get(i).getSeatsWon();
                index=i;
            }
        }
        return index;

    }

    public double stateVoterTurnout(){
        double n = constituencies.size();
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += constituencies.get(i).voterTurnOut();
        }
        double result =0;
        result = sum/n;
        return  result;

    }

    public void stateShortResult(){
        int n = this.winnerParty();
        String partyName = parties.get(n).getName();
        String CM = parties.get(n).getCMNAme();
        int seats[] = new int[parties.size()];
        String party[] = new String[parties.size()];
        for (int i = 0; i < parties.size(); i++) {
            party[i]= parties.get(i).getName();
            seats[i] = parties.get(i).getSeatsWon();
        }
        double voterturnout = stateVoterTurnout();


        Document doc = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\CandidatesList.pdf"));
            System.out.println("PDF created.");

            doc.open();

            Paragraph obj = new Paragraph();
            obj.setAlignment(Element.ALIGN_CENTER);
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN,25f,Font.BOLD,BaseColor.BLACK);
            obj.setFont(font1);
            Paragraph obj1 = new Paragraph();
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN,14f,Font.BOLD,BaseColor.BLACK);
            obj1.setFont(font2);
            obj1.add("Winning Party                 :      " +partyName+"\n");
            obj1.add("Upcoming Chief Minister       :      " + CM+"\n");
            Paragraph obj2 = new Paragraph();
            obj2.setAlignment(Element.ALIGN_CENTER);
            obj2.setFont(new Font(Font.FontFamily.TIMES_ROMAN,18f,Font.BOLD,BaseColor.BLACK));
            obj2.add("List Of Parties  with number of seats won \n");
            Paragraph obj3 = new Paragraph();
            obj3.setFont(font2);
            for (int i = 0; i < parties.size(); i++) {
                obj3.add(party[i]+"  :   "+seats[i]+"\n" );
            }

            doc.add(obj);
            doc.add(obj1);
            doc.add(obj2);
            doc.add(obj3);

            doc.close();

            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
