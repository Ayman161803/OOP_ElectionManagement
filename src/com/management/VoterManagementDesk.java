package com.management;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.management.populace.Candidate;
import com.management.populace.Citizen;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class VoterManagementDesk implements Desk{
    private  ArrayList<Voter> voterList;

    public VoterManagementDesk() {
        this.voterList = new ArrayList<>();
    }

    public int getCount(){
        return voterList.size();
    }

    public String registerIndividual(String AadharNumber){
        if(this.isAadharNumberValid(AadharNumber)){
            return "Invalid Aadhar Number";
        }
        Citizen citizen=returnIndividualWithAadharID( AadharNumber);
        String fileName="Constituency"+AadharNumber.charAt(8)+"Voters.txt";
        if(citizen==null){
            return "Error : AadharID not found.";
        }
        else if (!isEligibleByAge(citizen)){
            return "Error : Age of the citizen is below 18";
        }
        else{
            String str=citizen.getName()+"|"+citizen.getDOB()+"|"+citizen.getAge()+"|"+citizen.getGender()+"|"+citizen.getAddress()+"|"+citizen.getConstituency()+"|"+citizen.getAadharNumber();
            return addToList(str);
        }
    }

    public String addToList(String str){
        String AadharID=(str.split("\\|")[str.split("\\|").length-1]).trim();
        String numberConstituency="";
        for(int i=8;i<=8+AadharID.length()-13;i++){
            numberConstituency+=AadharID.charAt(i);
        }
        System.out.println(AadharID.length());
        String fileName= "./VoterData/Constituency"+numberConstituency+"Voters.txt";
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(str+"\n");
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
        return "Registration Successful";
    };


    public int memberOfGender(String gender){
        int count=0;
        for(int  i=0;i<voterList.size();i++){
            Voter voter= voterList.get(i);
            if(voter.getGender().toLowerCase(Locale.ROOT).equals(gender.toLowerCase(Locale.ROOT)))
                count++;
        }
        return count;
    }

    public void displayVotersRegistered(){
        for(int i=0;i<voterList.size();i++)
            System.out.println(voterList.get(i));
    }

    private boolean isEligibleByAge(Citizen citizen){
        return citizen.getAge()>=18;
    }

    public void build(String filename){
        File myObj = new File(filename);
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data==null || data.equals("")){
                    continue;
                }
                String[] citizenData=data.split("\\|");
                voterList.add(new Voter(citizenData[0],citizenData[1],Integer.parseInt(citizenData[2]),(citizenData[3]),citizenData[4],Integer.parseInt(citizenData[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected boolean doesExist(String aadhar){
        for(int i=0;i<voterList.size();i++){
            System.out.println(voterList.get(i).getAadharNumber());
            System.out.println(aadhar+"\n");
            if(aadhar.trim().equals(voterList.get(i).getAadharNumber().trim())){
                return true;
            }
        }
        return false;
    }

    protected String getAadharFromIndex(int n){
        return voterList.get(n).getAadharNumber();
    }

    protected void markVoted(String aadhar){
        for (Voter voter : voterList) {
            if (aadhar.equals(voter.getAadharNumber())) {
                voter.markVoted();
            }
        }
    }

    public void openAadharCardGenerationPortal (){
        new AadharGenerator();
    }

    public String genrateAadharCard(String AadharNumber){
        if(isAadharNumberValid(AadharNumber)){
            return "Invalid Aadhar Number";
        }
        Citizen citizen=returnIndividualWithAadharID(AadharNumber);
        if(citizen==null){
            return "Error : Aadhar ID not found";
        }
        Document doc = new Document();
        String name = citizen.getName();
        String DOB = citizen.getDOB();
        String gender = citizen.getGender();
        String num = citizen.getAadharNumber();
        try
        {
//generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("AadharCard.pdf"));
            System.out.println("PDF created.");
//opens the PDF
            doc.open();
//adds paragraph to the PDF file

            Image img = Image.getInstance("aadhar.jpg");
            img.scaleToFit(300f, 600f);
            img.setAbsolutePosition(150f, 720f);
            doc.add(img);
            Image img1 = Image.getInstance("unisex-avatar.png");
            img1.scaleAbsolute(70f, 70f);
            img1.setAbsolutePosition(390f, 630f);
            Image img2 = Image.getInstance("aadhar1.jpg");
            img2.scaleAbsolute(320f,30f);
            img2.setAbsolutePosition(150f,580f);
            doc.add(img1);
            doc.add(img2);
            Font f =new Font(Font.FontFamily.TIMES_ROMAN,12f,Font.NORMAL, BaseColor.BLACK);
            Paragraph p = new Paragraph("Name : ",f);
            p.setSpacingBefore(100f);
            p.setIndentationLeft(125f);
            p.add(name+'\n');
            p.add("Date of birth : "+ DOB+'\n');
            p.add("Gender : "+gender);
            p.add("\n");
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15f,Font.BOLD,BaseColor.BLACK);
            Paragraph p1 = new Paragraph(num,f2);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setSpacingBefore(10f);
            doc.add(p);
            doc.add(p1);
//close the PDF file
            doc.close();

//closes the writer
            writer.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            File pdfFile = new File("AadharCard.pdf");
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "AadharCard generation successful!";
    }

    public void openRegistrationPortal(){
        new VoterRegistrationForm();
    }

    public double maleRatio(){
        int totcount = 0,maleCount= 0;
        String gender = "male";
        for (int i = 0; i < voterList.size(); i++) {
            Voter voter =  voterList.get(i);
            if(voter.hasVoted()){
                totcount++;
                if(voter.getGender().toLowerCase(Locale.ROOT).equals(gender.toLowerCase(Locale.ROOT))){
                    maleCount++;
                }
            }
        }

        return 100*maleCount/totcount;
    }

    public double femaleRatio(){
        int totcount =0,femaleCount=0;
        String gender = "female";
        for (int i = 0; i < voterList.size(); i++) {
            Voter voter =  voterList.get(i);
            if(voter.hasVoted()){
                totcount++;
                if(voter.getGender().toLowerCase(Locale.ROOT).equals(gender.toLowerCase(Locale.ROOT))){
                    femaleCount++;
                }
            }
        }

        return 100*femaleCount/totcount;
    }

    public Voter returnVoter(String AadharID){
        for (int i = 0; i < voterList.size(); i++) {
            if(voterList.get(i).getAadharNumber().equals(AadharID)){
                return voterList.get(i);
            }
        }
        return  null;
    }
}
