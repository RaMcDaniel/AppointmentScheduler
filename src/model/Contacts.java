package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class pertains to contacts.
 *
 */
public class Contacts {
    private int contactID;
    private String contactName;
    public Contacts(int contactID, String contactName ){
        this.contactID = contactID;
        this.contactName = contactName;

    }

    /** This method gets a lsit of contact information and returns a more readable set of objects for the combo box.
     *
     * @param allContactIDs an observable list of contact information
     * @return a more readable list of contact objects
     */
    public static ObservableList<String> getReadable(ObservableList<Contacts> allContactIDs) {
        ObservableList<String> readableContacts = FXCollections.observableArrayList();
        for(Contacts c : allContactIDs){
            String readableLine = (c.getContactID() + " " + c.getContactName());
            readableContacts.add(readableLine);
        }
        return readableContacts;
    }

    /** This method takes a contact name and returns the contact ID.
     *
     * @param contact contact name
     * @return contact ID
     */
    public static int getContactsInt(String contact) {
        char c = contact.charAt(0);
        int num = Character.getNumericValue(c);
        return num;
    }

    /** The following 2 are getters for contact object parameters.
     *
     * @return the specified parameter
     */
    private String getContactName() { return contactName;}
    public int getContactID() {return contactID;}
}
