package com.management;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoterRegistration extends JFrame{
    private JTextField AadharNumberTextField;
    private JPanel panel1;
    private JButton EnterButton;
    private JLabel AadharNumberText;
    private JTextPane textPane1;
    private JFrame frame;
    private static int count=0;
    public VoterRegistration(){
        frame=new JFrame("VoterRegistrationPage");
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
                textPane1.setText(VoterManagementDesk.registerIndividual(AadharNumberTextField.getText()));
                StyledDocument doc = textPane1.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        });
    }

}
