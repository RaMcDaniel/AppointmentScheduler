package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacts {
    private int contactID;
    private String contactName;
    public Contacts(int contactID, String contactName ){
        this.contactID = contactID;
        this.contactName = contactName;

    }

    public static ObservableList<String> getReadable(ObservableList<Contacts> allContactIDs) {
        ObservableList<String> readableContacts = FXCollections.observableArrayList();
        for(Contacts c : allContactIDs){
            String readableLine = (c.getContactID() + " " + c.getContactName());
            readableContacts.add(readableLine);
        }
        return readableContacts;
    }

    public static int getContactsInt(String contact) {
        char c = contact.charAt(0);
        int num = Character.getNumericValue(c);
        return num;
    }

    private String getContactName() { return contactName;}

    public int getContactID() {return contactID;}
}
