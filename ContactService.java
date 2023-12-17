package main;

public class ContactService {

	// Use singleton pattern to create a single instance of the contact list
	private static ContactService instance;
	
	// binary search tree contact object
	private ContactBinarySearchTree contactTree;
	
	// create a new instance of the binary search tree
	private ContactService() {
		contactTree = new ContactBinarySearchTree();
	}
	
	// Public method to access the singleton instance
    public static ContactService getInstance() {
        if (instance == null) {
            instance = new ContactService();
        }
        return instance;
    }

    // Public method to interact with the BST
    public ContactBinarySearchTree getContactTree() {
        return contactTree;
    }
    
    // Public method for creating a new contact and adding to contactTree
    public void newContact(Contact contact ) {
    	// Check if a contact with the same contactId already exists
		if (contactTree.search(contact.getId()) != null) {
			throw new IllegalArgumentException("Contact with ID " + contact.getId() + " already exists.");
		}

    	//Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, address);
    	contactTree.insert(contact);
    }
    
    // Public method for deleting contact from contact tree based on contact ID
    public void deleteContact(String contactId) {
    	contactTree.delete(contactId);
    }
    
    // Methods for updating contacts in tree
    /* updateFirstName: updates first name of contact in tree
     * @param: contactId (String): unique identifier used to search for contact
     * @param: firstName (String): name to update in contact
     * */
    public void updateFirstName(String contactId, String firstName) {
    	
    	// search for contact based on contact ID
    	Contact searchContact = contactTree.search(contactId);
    	
    	// if no contact found, throw exception
    	if (searchContact == null) {
    		throw new IllegalArgumentException("Contact ID " + contactId + " not found");
    	}
    	
    	// if contact found, set first name
    	else {
    		searchContact.setFirstName(firstName);
    	}
    }
    
    /* updateLastName: updates last name of contact in tree
     * @param: contactId (String): unique identifier used to search for contact
     * @param: lastName (String): name to update in contact
     * */
    public void updateLastName(String contactId, String lastName) {
    	// Search for contact with contactID in binary search tress
    	Contact searchContact = contactTree.search(contactId);
    	
    	// throw error if no contact found with such ID
    	if (searchContact == null) {
    		throw new IllegalArgumentException("Contact ID " + contactId + " not found");
    	}
    	
    	// Update address if contact found
    	else {
    		searchContact.setLastName(lastName);
    	}
    }
    
    /* updatePhoneNumber: updates phone number of contact in tree
     * @param: contactId (String): unique identifier used to search for contact
     * @param: phoneNumber(String): phone number to update in contact
     * */
    public void updatePhoneNumber(String contactId, String phoneNumber) {
    	
    	// Search for contact with contactID in binary search tree
    	Contact searchContact = contactTree.search(contactId);
    	
    	
    	// throw error if no contact found with such ID
    	if (searchContact == null) {
    		throw new IllegalArgumentException("Contact ID " + contactId + " not found");
    	}
    	
    	// Update phone number if contact found
    	else {
    		searchContact.setPhoneNumber(phoneNumber);
    	}
    }
    
    /* updateAddress: updates phone number of contact in tree
     * @param: contactId (String): unique identifier used to search for contact
     * @param: address(String): phone number to update in contact
     * */
    public void updateAddress(String contactId, String address) {
    	// Search for contact with contactID in binary search tress
    	Contact searchContact = contactTree.search(contactId);
    	
    	// throw error if no contact found with such ID
    	if (searchContact == null) {
    		throw new IllegalArgumentException("Contact ID " + contactId + " not found");
    	}
    	// Update address if contact found
    	else {
    		searchContact.setAddress(address);
    	}
    }
	

}
