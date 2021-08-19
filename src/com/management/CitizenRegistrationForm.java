package com.management;

import com.management.Constituency;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class CitizenRegistrationForm implements Form{
    private JPanel panelAll;
    private JTextField nameTextField;
    private JTextField constituencyTextField;
    private JTextField addressTextField;
    private JTextField ageTextField;
    private JTextField genderTextField;
    private JTextField DOBTextField;
    private JButton enterButton;
    private JLabel nameLabel;
    private JLabel DOBLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JTextPane ReturnMessage;
    private JFrame frame;

    public CitizenRegistrationForm(){
        frame=new JFrame("CitizenRegistrationPage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(290,400));
        frame.setResizable(true);

        frame.add(panelAll);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constituency constituency= new Constituency();
                String dataFromForm=nameTextField.getText()+"|"+DOBTextField.getText()+"|"+ageTextField.getText()+"|"+genderTextField.getText()+"|"+addressTextField.getText()+"|"+constituencyTextField.getText();
                if(!isDataInFormat(dataFromForm)){
                    ReturnMessage.setText("Data entered is in wrong format.");
                    StyledDocument doc = ReturnMessage.getStyledDocument();
                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                    return;
                }
                ReturnMessage.setText(constituency.addToList(dataFromForm));
                StyledDocument doc = ReturnMessage.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        });
    }

    public static void main(String[] args) {
        new CitizenRegistrationForm();
    }

    @Override
    public boolean isDataInFormat(String data) {
        String[] dataArray=data.split("\\|");
        String DOB=dataArray[1].trim();

        System.out.println(DOB);
        //DOB format check
        {
            if(DOB.length()!=10)
                return false;
            if(DOB.charAt(2)!='/' ||DOB.charAt(5)!='/')
                return false;
            for(int i=0;i<DOB.length();i++){
                if(i==2 || i==5)
                    continue;
                if(!(DOB.charAt(i)>='0' && DOB.charAt(i)<='9'))
                    return false;
            }
        }

        String address=dataArray[4].trim();
        //Address format check
        {
            if(address.charAt(0)<'0' || address.charAt(0)>'9')
                return false;
        }

        String age=dataArray[2].trim();
        {
            for(int i=0;i<age.length();i++){
                if(!(age.charAt(i)>='0' && age.charAt(i)<='9'))
                    return false;
            }
        }

        String gender=dataArray[3].trim().toLowerCase(Locale.ROOT);
        if(!(gender.equals("male") || gender.equals("female")))
            return false;
        return true;
    }
}
