package com.management;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.management.populace.Citizen;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class VoterManagementDesk implements Desk,Registrar{
    private  ArrayList<Voter> voterList;

    public VoterManagementDesk() {
        this.voterList = new ArrayList<>();
    }

    public int getCount(){
        return voterList.size();
    }

    public String registerIndividual(String AadharNumber){
        if(isAadharNumberValid(AadharNumber)){
            return "Invalid Aadhar Number";
        }
        Citizen citizen=returnCitizenWithAadharNumber(AadharNumber);
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
        String fileName= "Constituency"+(Integer.parseInt(str.split("\\|")[str.split("\\|").length-2])-1)+"Voter.txt";
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
        return "Registration Successful";
    };

    private Citizen returnCitizenWithAadharNumber(String AadharNumber){
        String filename="Constituency"+AadharNumber.charAt(8)+".txt";
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
                String[] citizenData=data.split("\\|");
                if(citizenData[citizenData.length-1].equals(AadharNumber)) {
                    System.out.println(citizenData[2]);
                    return new Citizen(citizenData[0], citizenData[1], Integer.parseInt(citizenData[2]), (citizenData[3]), citizenData[4], Integer.parseInt(citizenData[5]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isAadharNumberValid(String AadharNumber){
        char[] numbers=AadharNumber.toCharArray();
        int checkSum=0;
        for(int i=0;i<9;i++){
            checkSum+=(i+1)*Integer.parseInt(String.valueOf(numbers[i]));
        }
        if(numbers[9]=='X'){
            checkSum+=10;
            return checkSum%11==0;
        }
        checkSum+=Integer.parseInt(String.valueOf(numbers[9]));
        return checkSum%11==0;
    }

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
                voterList.add(new Voter(citizenData[0],citizenData[1],citizenData[2],Integer.parseInt(citizenData[3]),citizenData[4],Integer.parseInt(citizenData[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected boolean doesExist(String aadhar){
        for(int i=0;i<voterList.size();i++){
            if(aadhar.equals(voterList.get(i).getAadharNumber())){
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

    public String genrateAadharCard(String AadharNumber){
        if(isAadharNumberValid(AadharNumber)){
            return "Invalid Aadhar Number";
        }
        Citizen citizen=returnCitizenWithAadharNumber(AadharNumber);
        Document doc = new Document();
        String name = citizen.getName();
        String DOB = citizen.getDOB();
        String gender = citizen.getGender();
        String num = citizen.getAadharNumber();

        try
        {
//generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\AadharCard.pdf"));
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
        return "AadharCard generation successful!";
    }

}
