package com.management;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.management.populace.Candidate;
import com.management.populace.Party;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Result {

     private ArrayList<Constituency> constituencies = new ArrayList<>();
     private ArrayList<Party> parties = new ArrayList<>();

    public Result(PollingBooth pollingBooth){
        String str = "Constituency";
        ArrayList<String> arr = new ArrayList<>();
        Constituency con[] = pollingBooth.getConstituencies();
        for (int i = 0; i < 10; i++) {
            constituencies.add(con[i]);
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
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\StateShortResult.pdf"));
            System.out.println("PDF created.");

            doc.open();

            Paragraph obj = new Paragraph();
            obj.setAlignment(Element.ALIGN_CENTER);
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN,25f,Font.BOLD,BaseColor.BLACK);
            obj.setFont(font1);
            obj.add("Short Result !!");
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

    public void showCandidateResult(String aadharID){
        Candidate per = null;
        for (int i = 0; i < 10; i++) {
            per = constituencies.get(i).getPollingManagementDesk().returnCandidate(aadharID);
            if(per==null){
                continue;
            }
            else {
                break;
            }
        }
        String name,gender,partyName,aadharNum;
        boolean result;
        int age,constituency;
        name= per.getName();
        gender = per.getGender();
        partyName = per.getAlliedPartyName();
        constituency =per.getConstituency();
        result = per.hasWon();
        age = per.getAge();
        aadharNum = per.getAadharNumber();

        Document doc = new Document();

        try{
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\CandidatesResult.pdf"));
            System.out.println("PDF created");

            doc.open();

            Paragraph obj = new Paragraph();
            obj.setAlignment(Element.ALIGN_CENTER);
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN,25f,Font.BOLDITALIC,BaseColor.BLACK);
            obj.setFont(font1);
            obj.add("Candidate Detail");
            Paragraph obj1 = new Paragraph();
            obj1.setSpacingBefore(10f);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN,14f,Font.BOLD,BaseColor.BLACK);
            obj1.setFont(font2);
            obj1.add("Name       : "+name +"\n");
            obj1.add("Age        : "+age +"\n");
            obj1.add("Gender     : "+gender+"\n");
            obj1.add("Aadhar number :"+aadharNum+"\n");
            obj1.add("Party Name : "+partyName+"\n");
            obj1.add("Constituency : "+constituency+"\n");
            String res;
            if(result){
                res="Won";
            }
            else {
                res ="Lost";
            }
            obj1.add("Result of Election :"+res+"\n");

            doc.add(obj);
            doc.add(obj1);


            doc.close();

            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void showConstituencyResult(int constituency){
        double male,female;
        male = constituencies.get(constituency).getVoterManagementDesk().maleRatio();
        female = constituencies.get(constituency).getVoterManagementDesk().femaleRatio();
        Candidate winner = constituencies.get(constituency).getPollingManagementDesk().getWinner();
        String name,party,gender,aadharNum;
        name = winner.getName();
        party = winner.getAlliedPartyName();
        aadharNum = winner.getAadharNumber();
        double voterTurnOut = constituencies.get(constituency).voterTurnOut();

        Document doc = new Document();

        try{
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\Constituency"+constituency+"Result.pdf"));
            System.out.println("PDF created");

            doc.open();

            Paragraph obj = new Paragraph();
            obj.setAlignment(Element.ALIGN_CENTER);
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN,25f,Font.BOLDITALIC,BaseColor.BLACK);
            obj.setFont(font1);
            obj.add("Constituency "+constituency+ "  Result !!");
            Paragraph obj1 = new Paragraph();
            obj1.setSpacingBefore(8f);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN,14f,Font.BOLD,BaseColor.BLACK);
            obj1.setFont(font2);
            obj1.add("Winner Name    :  "+name+"\n");
            obj1.add("Party Name       :   "+party+"\n");
            obj1.add("Aadhar Number     :   "+aadharNum+"\n");
            obj1.add("\n\n\n");
            obj1.add("Voter Turn Out \n");
            obj1.add(" "+voterTurnOut+"%  has  voted in Constituency "+constituency+"\n");
            obj1.add("\n\n\n");
            obj1.add("Male and Female Ratio \n");
            obj1.add(" "+male+"%  has voted in Constituency\n");
            obj1.add(" "+female+"% voted in  Constituency\n");

            String[] names = constituencies.get(constituency).getPollingManagementDesk().partiesName();
            double[] votes = constituencies.get(constituency).getPollingManagementDesk().partiesVotePercentage();


            int width = 500,height=400;
            DefaultPieDataset dataSet = new DefaultPieDataset();
            for (int i = 0; i < names.length; i++) {
                dataSet.setValue(names[i],votes[i]);
            }
            JFreeChart chart = ChartFactory.createPieChart(
                    "Party Vote Distribution", dataSet, true, true, false);

            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(width, height);
            Graphics2D graphics2d = template.createGraphics(width, height,
                    new DefaultFontMapper());
            Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
                    height);

            chart.draw(graphics2d, rectangle2d);

            graphics2d.dispose();
            Image img =Image.getInstance(template);
            img.scaleAbsolute(300,200);
            img.setAbsolutePosition(150f,25f);


            doc.add(obj);
            doc.add(obj1);
            doc.add(img);

            doc.close();

            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
