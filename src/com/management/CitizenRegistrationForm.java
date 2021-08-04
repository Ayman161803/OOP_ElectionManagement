package com.management;

import com.management.Constituency;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                ReturnMessage.setText(constituency.addToList(dataFromForm));
                StyledDocument doc = ReturnMessage.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        });
    }

    @Override
    public boolean isDataInFormat() {
        return false;
    }
}
