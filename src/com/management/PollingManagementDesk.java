package com.management;

import com.management.Voter;
import com.management.populace.Candidate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PollingManagementDesk {
    private ArrayList<Candidate> candidates;
    private int[] votesCounter=null;
    private long totalNoOfVotes =0;

    //
    public void registerCandidate(Candidate candidate){
        candidates.add(candidate);
    }

    public void addCandidates(String filename){
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
                String[] candidateData=data.split("\\|");
                candidates.add(new Candidate(candidateData[0],candidateData[1],candidateData[2],candidateData[3],Integer.parseInt(candidateData[4]),candidateData[5],Integer.parseInt(candidateData[6]),Integer.parseInt(candidateData[7]),candidateData[8]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfCandidates(){
        return candidates.size();
    }

    public void registerVote(Voter voter){
        if(votesCounter==null)
            votesCounter=new int[candidates.size()];
        if(!voter.hasVoted()){
            voter.markVoted();
            votesCounter[(int)(Math.random()*candidates.size())]++;
            totalNoOfVotes++;
        }
    }

    public void showAllCandidates(){
        for (int i=0;i<candidates.size();i++){
            System.out.println(candidates.get(i)+"\n");
        }
    }

    public void showWinner(){
        int maxIndex=0;
        for(int i=1;i<votesCounter.length;i++){
            if(votesCounter[i]>votesCounter[maxIndex])
                maxIndex=i;
        }
        System.out.println(candidates.get(maxIndex));
    }

    public Candidate getWinner(){
        int maxIndex=0;
        for(int i=1;i<votesCounter.length;i++){
            if(votesCounter[i]>votesCounter[maxIndex])
                maxIndex=i;
        }
        return candidates.get(maxIndex);
    }

    public void showVotesDistribution(){}//return a bar-graph of sorts as the output

    public long getTotalNoOfVotes(){
        return totalNoOfVotes;
    }
}
