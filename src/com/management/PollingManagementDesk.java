package com.management;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.management.populace.Candidate;
import com.management.populace.Citizen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PollingManagementDesk implements Desk{
    private ArrayList<Candidate> candidates;
    private int[] votesCounter = null;
    private long totalNoOfVotes = 0;

    public PollingManagementDesk() {
        this.candidates = new ArrayList<>();
    }

    public String registerIndividual(String data) {
        return null;
    }

    //call this from register individual
    public boolean isEligibleByAge(){
        return false;
    }

    public void build(String filename) {
        File myObj = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data == null) {
                    continue;
                }
                String[] candidateData = data.split("\\|");
                candidates.add(new Candidate(candidateData[0], candidateData[1], candidateData[2], Integer.parseInt(candidateData[3]), candidateData[4], Integer.parseInt(candidateData[5]), Integer.parseInt(candidateData[6]), candidateData[7]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return candidates.size();
    }

    public void registerVote(Voter voter) {
        if (votesCounter == null)
            votesCounter = new int[candidates.size()];
        if (!voter.hasVoted()) {
            voter.markVoted();
            votesCounter[(int) (Math.random() * candidates.size())]++;
            totalNoOfVotes++;
        }
    }

    public void showAllCandidates() {
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println(candidates.get(i) + "\n");
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
        candidates.get(maxIndex).setHasWon(true);
        return candidates.get(maxIndex);
    }

    public long getTotalNoOfVotes() {
        return totalNoOfVotes;
    }

    protected void updateVote(int n) {
        votesCounter[n]++;
        totalNoOfVotes++;
    }

    public double getPopularityScore(int j) {
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
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\CandidatesList.pdf"));
            System.out.println("PDF created.");

            doc.open();

            Paragraph o = new Paragraph("LIST OF CANDIDATES");
            o.setAlignment(Element.ALIGN_CENTER);
            o.setFont(new Font(Font.FontFamily.TIMES_ROMAN,15f,Font.BOLD, BaseColor.BLACK));
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

    public void openRegistrationPortal(){new CandidateAdditionPortal();}

    @Override
    public Citizen returnIndividualWithAadharID(String AadharID) {
        return null;
    }

    public Candidate returnCandidate(String AadharID){
        for (int i = 0; i < candidates.size(); i++) {
            if(candidates.get(i).getAadharNumber().equals(AadharID))
                return candidates.get(i);
        }
        return null;
    }

    @Override
    public String addToList(String data) {
        return null;
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

}

