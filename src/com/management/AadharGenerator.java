package com.management;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AadharGenerator extends FormWithAadharTextField{
    private JTextField AadharNumberTextField;
    private JPanel panel1;
    private JButton EnterButton;
    private JLabel AadharNumberText;
    private JTextPane textPane1;
    private JFrame frame;
    public AadharGenerator(){
        frame=new JFrame("AadharGeneratorPage");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(290,380));
        frame.setResizable(true);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        EnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isDataInFormat(AadharNumberTextField.getText())){
                    textPane1.setText("Data entered is in wrong format.");
                    StyledDocument doc = textPane1.getStyledDocument();
                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                    return;
                }
                VoterManagementDesk voterManagementDesk= new VoterManagementDesk();
                textPane1.setText(voterManagementDesk.genrateAadharCard(AadharNumberTextField.getText().trim()));
                StyledDocument doc = textPane1.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        });
    }

    public static void main(String[] args) {
        new AadharGenerator();
    }
}
