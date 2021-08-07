package com.management;

import javax.swing.*;

public class CandidateAdditionPortal implements Form {

    private JTextField AadharNumberTextField;
    private JPanel panel1;
    private JButton EnterButton;
    private JLabel AadharNumberText;
    private JTextPane textPane1;
    private JFrame frame;
    public CandidateAdditionPortal() {
    }

    @Override
    public boolean isDataInFormat() {
        return false;
    }
}
