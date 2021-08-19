package com.management;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.util.Scanner;

public class PollingBooth{
    private ArrayList<Constituency> constituencies;

    public PollingBooth(){
        File directory= new File("CitizenData");
        int length=directory.listFiles().length;
        System.out.println(length);
        constituencies=new ArrayList<Constituency>();
        for(int i=0;i<length;i++){
            constituencies.add(new Constituency(String.valueOf(i)));
        }
    }

    protected ArrayList<Constituency> getConstituencies() {
        return constituencies;
    }

    protected void automateVoting(){
        for(int i=0;i<10;i++){
            double[] ps=new double[constituencies.get(i).getPollingManagementDesk().getCount()];
            double sum=0;
            double temp;
            int n= constituencies.get(i).getPollingManagementDesk().getCount();

            for(int j=0;j<n;j++){
                ps[j]=10- constituencies.get(i).getPollingManagementDesk().getPopularityScore(j);
                sum+=ps[j];
            }

            for(int j=0;j<n/2;j++){
                temp = ps[j];
                ps[j] = ps[n-j -1];
                ps[n-j-1] = temp;
            }

            for(int j=0;j<n;j++){
                ps[j]/=sum;
            }

            for(int k = 0; k< constituencies.get(i).getVoterManagementDesk().getCount(); k++){
                Random rando = new Random();
                int a=rando.nextInt( 100) + 1;
                if(a<75){
                    double low=0,high=ps[n-1];
                    int flag=0;
                    double rand=Math.random();
                    for (int j=n-1;j==1;j--) {
                        if (rand > low && rand < high) {
                            constituencies.get(i).getPollingManagementDesk().updateVote(j + 1); //reason for j+1:check updateVote
                            flag = 1;
                            break;
                        }
                        low += ps[j];
                        high += ps[j - 1];

                    }

                    if (flag==0){
                        constituencies.get(i).getPollingManagementDesk().updateVote(2);
                    }

                    constituencies.get(i).getVoterManagementDesk().markVoted(constituencies.get(i).getVoterManagementDesk().getAadharFromIndex(k));
                }


            }

        }
    }

    public void registerVote(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your Aadhar:");
        String aadhar=sc.nextLine();
        int constituencyno=Integer.parseInt(String.valueOf(aadhar.charAt(8)));


        if (constituencies.get(constituencyno).getVoterManagementDesk().doesExist(aadhar)){
            constituencies.get(constituencyno).getPollingManagementDesk().showAllCandidates();
            System.out.print("Cast your vote:");

            int vote=sc.nextInt();
            constituencies.get(constituencyno).getPollingManagementDesk().updateVote(vote-1);

            System.out.println("Vote successfully registered!");

            constituencies.get(constituencyno).getVoterManagementDesk().markVoted(aadhar);

        }
        else
            System.out.println("Invalid AadharID entered!");
    }

    protected void automate(){
        int noOfConstituencies=constituencies.size();
        for(Constituency constituency:constituencies){
            int noOfVotersInTheConstituency=constituency.getVoterManagementDesk().getCount();
            int noOfCandidatesInTheConstituency=constituency.getPollingManagementDesk().getCount();
            for (int i = 0; i < noOfVotersInTheConstituency; i++) {
                double randomValueWhichDecideWhetherTheVoterVotes=Math.random();
                if(randomValueWhichDecideWhetherTheVoterVotes>0.25){
                    constituency.getPollingManagementDesk().updateVote((int) (Math.random()*noOfCandidatesInTheConstituency));
                }
            }
        }
    }

    public static void main(String[] args) {
        PollingBooth pollingBooth=new PollingBooth();
        pollingBooth.automateVoting();
        for(int i=0;i<10;i++)
            System.out.println(pollingBooth.constituencies.get(i).getPollingManagementDesk().getTotalNoOfVotes());
        Result result= new Result(pollingBooth);
        result.showStateResult();
        result.stateShortResult();
    }
}
