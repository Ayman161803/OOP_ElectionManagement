package com.management;

import com.management.populace.Citizen;

public interface Desk {
    int getCount();
    void build(String filename);
    String addToList(String data);
    void openRegistrationPortal();
    Citizen returnIndividualWithAadharID(String AadharID);
}
