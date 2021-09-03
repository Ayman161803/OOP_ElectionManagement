package com.management;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.management.populace.Candidate;
import com.management.populace.Citizen;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class PollingManagementDesk implements Desk{
    private ArrayList<Candidate> candidates;
    private int[] votesCounter = null;
    private long totalNoOfVotes = 0;
    private String constituencyNumber;

    public PollingManagementDesk(String constituencyNumber) {
        this.constituencyNumber=constituencyNumber;
        this.candidates = new ArrayList<>();
    }

    public PollingManagementDesk(){}

    public String registerIndividual(String AadharNumberAndParty) {
        String AadharNumber=AadharNumberAndParty.split("\\|")[0];
        String Party=AadharNumberAndParty.split("\\|")[1];

        //get citizen from aadharID
        Citizen citizen=returnIndividualWithAadharID( AadharNumber);

        //if no citizen correspnding to aadhar found.
        if(citizen==null){
            return "Error : AadharID not found.";
        }

        //if the citizen is under age
        if(isEligibleByAge(citizen)){
            String str=citizen.getName()+"|"+citizen.getDOB()+"|"+citizen.getAge()+"|"+citizen.getGender()+"|"+citizen.getAddress()+"|"+citizen.getConstituency()+"|"+citizen.getAadharNumber()+"|"+Party;
            return addToList(str);
        }

        return "Citizen is underAge";
    }

    //call this from register individual
    private boolean isEligibleByAge(Citizen citizen){
        return citizen.getAge()>=25;
    }

    public void build(String filename) {
        File myObj = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine() ) {
               String data = myReader.nextLine();
                if (data == null || data=="") {
                    continue;
                }
                String[] candidateData = data.split("\\|");
                candidates.add(new Candidate(candidateData[0], candidateData[1], Integer.parseInt(candidateData[2]), (candidateData[3]), candidateData[4], Integer.parseInt(candidateData[5]), candidateData[7]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        votesCounter=new int[candidates.size()];
       candidates.sort(Comparator.comparingDouble(Candidate::getPopularityScore).reversed());
    }

    public int getCount() {
        return candidates.size();
    }

    public void showAllCandidates() {
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println(i+"."+candidates.get(i) + "\n");
        }
    }

    public void showWinner() {
        int maxIndex = 0;
        for (int i = 1; i < votesCounter.length; i++) {
            if (votesCounter[i] > votesCounter[maxIndex])
                maxIndex = i;
        }
        System.out.println(candidates.get(maxIndex));
    }

    public Candidate getWinner() {
        int maxIndex = 0;
        for (int i = 1; i < votesCounter.length; i++) {
            if (votesCounter[i] > votesCounter[maxIndex])
                maxIndex = i;
        }
        return candidates.get(maxIndex);
    }

    protected void setWinner(){
        int maxIndex = 0;
        for (int i = 1; i < votesCounter.length; i++) {
            if (votesCounter[i] > votesCounter[maxIndex])
                maxIndex = i;
        }
        candidates.get(maxIndex).setHasWon(true);
    }

    protected long getTotalNoOfVotes() {
        return totalNoOfVotes;
    }

    protected void updateVote(int n) {
        votesCounter[n]++;
        totalNoOfVotes++;
    }

    protected double getPopularityScore(int j) {
        return candidates.get(j).getPopularityScore();
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void releaseCandidateList(){
        int num = this.getCandidates().size();
        String name[] = new String[this.getCount()];
        String party[] = new String[num];
        int constituency[] = new int[num];
        for (int i = 0; i < num; i++) {
            name[i] = candidates.get(i).getName();
            party[i] = candidates.get(i).getAlliedPartyName();
            constituency[i] = candidates.get(i).getConstituency();
        }

        Document doc = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("CandidatesList"+String.valueOf(constituencyNumber)+".pdf"));
            System.out.println("PDF created.");

            doc.open();

            Paragraph o = new Paragraph("LIST OF CANDIDATES");
            o.setAlignment(Element.ALIGN_CENTER);
            o.setFont(new Font(Font.FontFamily.TIMES_ROMAN,13f,Font.BOLD, BaseColor.BLACK));
            doc.add(o);
            Paragraph p = new Paragraph();
            for (int i = 0; i < num; i++) {
                p.add("Name : "+name[i]+'\n');
                p.add("Party : " + party[i]+'\n');
                p.add("Constituency : "+constituency[i]+"\n\n");
            }
            doc.add(p);

            doc.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String addToList(String str) {
        //getting aadhar from data
        String AadharID=(str.split("\\|")[str.split("\\|").length-2]);

        //getting party from data
        String PartyOfCandidateGrttingAdded=(str.split("\\|")[str.split("\\|").length-1]);

        //getting constituency from aadharID
        String numberConstituency="";
        for(int i=8;i<=8+AadharID.length()-12;i++){
            numberConstituency+=AadharID.charAt(i);
        }

        //adding data into Candidates files
        String fileName= "CandidateData/Constituency"+numberConstituency+"Candidates.txt";
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(str+"\n");
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }

        //checking if party already registered
        File myObj = new File("PartyData/Parties"+".txt");
        boolean isPartyAlreadyRegistered=false;
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data==null){
                    continue;
                }
                String[] parties=data.split("\\|");
                for(String partyInTheLine:parties){
                    if(partyInTheLine.equals(PartyOfCandidateGrttingAdded))
                        isPartyAlreadyRegistered=true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //adding party if not registered
        if(!isPartyAlreadyRegistered){
            try {
                BufferedWriter out = new BufferedWriter(
                        new FileWriter("PartyData/Parties.txt", true));
                out.write("\n"+PartyOfCandidateGrttingAdded);
                out.close();
            }
            catch (IOException e) {
                System.out.println("exception occurred" + e);
            }
        }

        return "Candidate added successfully";
    }

    public String[] partiesName(){
        String[] name= new String[candidates.size()];
        for (int i = 0; i < candidates.size(); i++) {
            name[i] = candidates.get(i).getAlliedPartyName();
        }
        return name;
    }

    public double[] partiesVotePercentage(){
        double[] result = new double[candidates.size()];
        for (int i = 0; i < getCandidates().size(); i++) {
            result[i]=100.0*votesCounter[i]/totalNoOfVotes;
        }
        return result;
    }

    public Candidate returnCandidate(String AadharID){
        for (int i = 0; i < candidates.size(); i++) {
            if(candidates.get(i).getAadharNumber().equals(AadharID))
                return candidates.get(i);
        }
        return null;
    }

    public Candidate getCandidateWithParty(String Party){
        int length=candidates.size();
        for(int i=0;i<length;i++){
            if(candidates.get(i).getAlliedPartyName().equals(Party)){
                return candidates.get(i);
            }
        }
        return null;
    }
}

