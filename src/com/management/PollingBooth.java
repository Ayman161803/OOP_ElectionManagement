package com.management;

import com.management.Constituency;

import java.util.Scanner;
import java.lang.Math;

public class PollingBooth{
    public static void main(String[] args) {
        Constituency[] c=new Constituency[10];

        for(int i=0;i<10;i++){
            c[i]=new Constituency("Constituency"+(i));
            c[i].build();
        }

        Scanner sc=new Scanner(System.in);

        for (int i=0;i<7;i++){
            System.out.print("Enter your Aadhar:");
            String aadhar=sc.nextLine();
            int constituencyno=Integer.parseInt(String.valueOf(aadhar.charAt(8)));


            if (c[constituencyno].getVoterManagementDesk().doesExist(aadhar)){
                c[constituencyno].getPollingManagementDesk().showAllCandidates();
                System.out.print("Cast your vote:");

                int vote=sc.nextInt();
                c[constituencyno].getPollingManagementDesk().updateVote(vote-1);

                System.out.println("Vote successfully registered!");

                c[constituencyno].getVoterManagementDesk().markVoted(aadhar);
                break;
            }
            else
                System.out.println("Wrong VoterID entered!");

        }

        //automation
        for(int i=0;i<10;i++){
            double[] ps=new double[c[i].getPollingManagementDesk().getCount()];
            double sum=0;
            double t;
            int n=c[i].getPollingManagementDesk().getCount();

            for(int j=0;j<n;j++){
                ps[j]=10-c[i].getPollingManagementDesk().getPopularityScore(j);
                sum+=ps[j];
            }

            for(int j=0;j<n/2;j++){
                t = ps[j];
                ps[i] = ps[n-j -1];
                ps[n-j-1] = t;
            }

            for(int j=0;j<n;j++){
                ps[j]/=sum;
            }

            for(int k=0;k<c[i].getVoterManagementDesk().getCount();k++){
                double rand=Math.random();
                double low=0,high=ps[n-1];
                int flag=0;

                for (int j=n-1;j==1;j--) {
                    if (rand > low && rand < high) {
                        c[i].getPollingManagementDesk().updateVote(j + 1); //reason for j+1:check updateVote
                        flag = 1;
                        break;
                    }
                    low += ps[j];
                    high += ps[j - 1];

                }

                if (flag==0){
                    c[i].getPollingManagementDesk().updateVote(1);
                }

                c[i].getVoterManagementDesk().markVoted(c[i].getVoterManagementDesk().getAadhar(k));

            }

        }
    }
}