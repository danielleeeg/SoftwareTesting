package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import main.ContactService;
import main.Contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ContactServiceTest {

    private ContactService contactService;

	protected String contactId, firstName, lastName, phoneNumber, address;

    @BeforeEach
    void setUp() {
    	
    	// create an instance of contactService
        contactService = ContactService.getInstance();
      
        // create the default test parameters (all passing)
		firstName = "Danielle";
		lastName = "Eeg";
		phoneNumber = "0123456789";
		address = "123 Main Street";
    }
   
    @Test
    void givenContactService_whenContactAdded_thenContactFoundInTree() {
    	// create a new contact
    	Contact contact = new Contact("1", firstName, lastName, phoneNumber, address);
    	
        // Test adding a new contact
        contactService.newContact(contact);
        assertNotNull(contactService.getContactTree().search("1"));
    }

    @Test
    void givenContactInService_whenContactDeleted_thenContactSearchReturnsNull() {
    	// Create a contact
    	Contact contact = new Contact("2", firstName, lastName, phoneNumber, address);
    	
        // Test deleting a contact
        contactService.newContact(contact);
        
        // make sure new contact exists
        assertNotNull(contactService.getContactTree().search("2"));
        
        // delete contact
        contactService.deleteContact("2");
        
        // make sure contact was deleted
        assertNull(contactService.getContactTree().search("2"));
    }
    
    @Test
	void givenContactAddedToService_whenContactWithDuplicateIdAdded_thenExceptionThrown() {
		// Test that attempting to add two contacts with the same contactId to ContactService fails
		ContactService contactService = ContactService.getInstance();

		// Add the first contact
		Contact contact1 = new Contact("123", firstName, lastName, phoneNumber, address);
		contactService.newContact(contact1);

		// Attempt to add a second contact with the same contactId
		Contact contact2 = new Contact("123", "Jane", "Smith", "1234567890", "1 1st Street");

		// Expect an exception to be thrown
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    contactService.newContact(contact2);
		});
	}

    @Test
    void givenContactInTree_whenNameUpdated_thenUpdatedNameSavedToContactInTree() {
        
    	// Create a contact
    	Contact contact = new Contact("3", firstName, lastName, phoneNumber, address);
    	
    	// Test updating the first name of a contact
        contactService.newContact(contact);

        // Verify the initial name
        assertEquals("Danielle", contactService.getContactTree().search("3").getFirstName());

        // Update the first name
        contactService.updateFirstName("3", "Bob");

        // Verify the updated name
        assertEquals("Bob", contactService.getContactTree().search("3").getFirstName());
    }

    @Test
    void givenContactInTree_whenLastNameUpdated_thenUpdatedLastNameSavedToContactInTree() {
    	// Create a contact
    	Contact contact = new Contact("4", firstName, lastName, phoneNumber, address);
    	
    	// Test updating the last name of a contact
        contactService.newContact(contact);

        // Verify the initial name
        assertEquals("Eeg", contactService.getContactTree().search("4").getLastName());

        // Update the last name
        contactService.updateLastName("4", "Jones");

        // Verify the updated name
        assertEquals("Jones", contactService.getContactTree().search("4").getLastName());
    }
    
    @Test
    void givenNoContactExistsWithId24_whenContact24LastNameUpdated_thenExceptionThrown() {
    	
    	// Make sure exception thrown for a contact whose ID does not exist in system
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		 contactService.updateLastName("24", lastName);
		});
    }

    @Test
    void givenContactInTree_whenPhoneNumberUpdated_thenUpdatedPhoneNumberSavedToContactInTree() {
    	// Create a contact
    	Contact contact = new Contact("5", firstName, lastName, phoneNumber, address);
    	
    	// Test updating the phone number of a contact
        contactService.newContact(contact);

        // Verify the initial phone number
        assertEquals("0123456789", contactService.getContactTree().search("5").getPhoneNumber());

        // Update the phone number
        contactService.updatePhoneNumber("5", "1112223333");

        // Verify the updated phone number
        assertEquals("1112223333", contactService.getContactTree().search("5").getPhoneNumber());
    }

    @Test
    void givenNoContactExistsWithId24_whenContact24PhoneNumberUpdated_thenExceptionThrown() {
    	
    	// Make sure exception thrown for a contact whose ID does not exist in system
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		 contactService.updatePhoneNumber("24", phoneNumber);
		});
    }
    
    @Test
    void givenContactInTree_whenPhoneNumberUpdated_thenUpdatedAddressSavedToContactInTree() {
    	// Create a contact
    	Contact contact = new Contact("6", firstName, lastName, phoneNumber, address);
    	
    	// Test updating the address of a contact
        contactService.newContact(contact);

        // Verify the initial address
        assertEquals("123 Main Street", contactService.getContactTree().search("6").getAddress());

        // Update the address
        contactService.updateAddress("6", "789 Maple St");

        // Verify the updated address
        assertEquals("789 Maple St", contactService.getContactTree().search("6").getAddress());
    }
    
    @Test
    void givenNoContactExistsWithId24_whenContact24AddressUpdated_thenExceptionThrown() {

    	// Make sure exception thrown for a contact whose ID does not exist in system
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		 contactService.updateAddress("24", address);
		});
    }
    

    @Test
    void givenNoContactExistsWithId24_whenContact24FirstNameUpdated_thenExceptionThrown() {
        // Test updating a nonexistent contact
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("24", "NewFirstName");
        });
    }
}
