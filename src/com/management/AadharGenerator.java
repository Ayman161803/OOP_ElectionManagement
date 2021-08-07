package com.management;

import javax.swing.*;

public class AadharGenerator implements Form{
    private JTextField AadharNumberTextField;
    private JPanel panel1;
    private JButton EnterButton;
    private JLabel AadharNumberText;
    private JTextPane textPane1;
    private JFrame frame;
    public AadharGenerator(){
        VoterManagementDesk voterManagementDesk= new VoterManagementDesk();
        voterManagementDesk.genrateAadharCard("nb");
    }
    @Override
    public boolean isDataInFormat() {
        return false;
    }
}
