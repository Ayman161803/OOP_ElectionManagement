package com.management;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CandidateAdditionPortal implements Form {

    private JTextField AadharNumberTextField;
    private JPanel panel1;
    private JButton EnterButton;
    private JLabel AadharNumberText;
    private JTextPane textPane1;
    private JTextField Party;
    private JFrame frame;
    public CandidateAdditionPortal() {frame=new JFrame("CandidateRegistrationPage");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(290,280));
        frame.setResizable(true);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        EnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isDataInFormat(AadharNumberTextField.getText())) {
                    textPane1.setText("Data entered is in wrong format.");
                    StyledDocument doc = textPane1.getStyledDocument();
                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                    return;
                }
                PollingManagementDesk pollingManagementDesk= new PollingManagementDesk();
                System.out.println(Party.getText());
                textPane1.setText( pollingManagementDesk.registerIndividual(AadharNumberTextField.getText()+"|"+Party.getText()));
                StyledDocument doc = textPane1.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        });
    }

    @Override
    public boolean isDataInFormat(String aadhar) {
        if(aadhar.trim().length()<12){
            return false;
        }
        for(int i = 0; i<aadhar.length(); i++){
            if((!(aadhar.charAt(i)>='0'&&aadhar.charAt(i)<='9')) && aadhar.charAt(i)!='X')
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new CandidateAdditionPortal();
    }
}
