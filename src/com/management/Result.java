package com.management;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.management.populace.Candidate;
import com.management.populace.Citizen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Result {
    public void genrateAadharCard(Citizen obj){
        Document doc = new Document();
        String name = obj.getName();
        String DOB = obj.getDOB();
        String gender = obj.getGender();
        String num = obj.getAadharNumber();

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
            Font f =new Font(Font.FontFamily.TIMES_ROMAN,12f,Font.NORMAL,BaseColor.BLACK);
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

    }

    public void showAllCandidates(){
        PollingManagementDesk obj = new PollingManagementDesk();
        int num = obj.getCandidates().size();
        String name[] = new String[obj.getCandidates().size()];
        String party[] = new String[num];
        int constituency[] = new int[num];
        ArrayList<Candidate> candidate = obj.getCandidates();
        for (int i = 0; i < num; i++) {
            name[i] = candidate.get(i).getName();
            party[i] = candidate.get(i).getAlliedPartyName();
            constituency[i] = candidate.get(i).getConstituency();
        }

        Document doc = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\CandidatesList.pdf"));
            System.out.println("PDF created.");

            doc.open();
            writer.open();

            Paragraph o = new Paragraph("LIST OF CANDIDATES");
            o.setAlignment(Element.ALIGN_CENTER);
            o.setFont(new Font(Font.FontFamily.TIMES_ROMAN,15f,Font.BOLD,BaseColor.BLACK));
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

}
