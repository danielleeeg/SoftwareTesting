package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Contact;

class ContactTest {
	
	protected String contactId, firstName, lastName, phoneNumber, address, testCharacter;
	protected int idBoundary, nameBoundary, lastNameBoundary, phoneNumberBoundary, addressBoundary;
	
	@BeforeEach
	protected void setUp() {
		contactId = "123456";
		firstName = "Danielle";
		lastName = "Eeg";
		phoneNumber = "0123456789";
		address = "123 Main Street";
		
		// test character for creating strings of set length
		testCharacter = "1";
		
		// boundary values
		idBoundary = 10;
		nameBoundary = 10;
		lastNameBoundary = 10;
		phoneNumberBoundary = 10;
		addressBoundary = 30;
	}
	
	// Method for creating a string of a specified length
	// this is used to generate strings for testing boundaries
	private String repeat(String character, int length) {
		String outputString = new String(new char[length]).replace("\0", character);
		return outputString;
	}
	
	@Test
	void givenContactConstructedWithValidInput_whenAccessorMethodsCalled_thenCorrectValuesReturned() {
		//Contact contact = new Contact("123456", "Danielle", "Eeg", "0123456789", "123 Main Street");
		//set up variables
		Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, address);
		assertTrue(contact.getId().equals("123456"));
		assertTrue(contact.getFirstName().equals("Danielle"));
		assertTrue(contact.getLastName().equals("Eeg"));
		assertTrue(contact.getPhoneNumber().equals("0123456789"));
		assertTrue(contact.getAddress().equals("123 Main Street"));

	}

	@Test
	void givenIdLongerThanBoundary_whenContactConstructed_thenExceptionThrown() {
		// Contact with too long of an ID should throw exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Contact(repeat(testCharacter, idBoundary + 1), firstName, lastName, phoneNumber, address);
		});
	}
	
	@Test
	void givenIdEqualToBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that contact with 10 char (boundary) ID does not throw exception
		Assertions.assertAll( () -> {
		    new Contact(repeat(testCharacter, idBoundary), firstName, lastName, phoneNumber, address);
		});
	}
	
	@Test
	void givenIdLessThanBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that contact with less than 10 char (boundary) ID does not throw exception
		Assertions.assertAll( () -> {
		    new Contact(repeat(testCharacter, idBoundary - 1), firstName, lastName, phoneNumber, address);
		});
	}
	
	@Test
	void givenNullId_whenContactConstructed_thenExceptionThrown() {
		
		// test that contact with null ID throws exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Contact(null, firstName, lastName, phoneNumber, address);
		});
	}
	
	@Test
	
	
	void givenFirstNameLongerThanBoundary_whenContactConstructed_thenExceptionThrown() {
		// Test that contact with greater than 10 char (boundary) first name throws exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(contactId, repeat(testCharacter, nameBoundary + 1), lastName, phoneNumber, address);
		});
	}
	
	@Test
	void givenFirstNameEqualToBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that contact with 10 char (boundary) first name does not throw exception
		Assertions.assertAll( () -> {
			new Contact(contactId, repeat(testCharacter, nameBoundary), lastName, phoneNumber, address);
		});
	}
	
	@Test
	void givenFirstNameLessThanBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that contact with less than 10 char (boundary) first name does not throw exception
		Assertions.assertAll( () -> {
			new Contact(contactId, repeat(testCharacter, nameBoundary - 1), lastName, phoneNumber, address);
		});
	}
	
	@Test
	void givenFirstNameNull_whenContactConstructed_thenExceptionThrown() {
		
		// Test that contact with null first name throws exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Contact(contactId, null, lastName, phoneNumber, address);
		});
	}
	
	@Test
	void givenContact_whenContactFirstNameUpdated_thenAccessorMethodReturnsUpdatedValue() {
		
		// create new contact 
		Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, address);
		
		// update first name
		contact.setFirstName("Susan");
		
		// test that first name was updated
		assertTrue(contact.getFirstName().equals("Susan"));
	}
	
	@Test
	void givenLastNameLongerThanBoundary_whenContactConstructed_thenExceptionThrown() {
		
		// Test that contact with greater than 10 char (boundary) last name does not throw exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(contactId, firstName, repeat(testCharacter, lastNameBoundary + 1), phoneNumber, address);
		});
	}
	
	@Test
	void givenLastNameEqualToBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that contact with 10 char (boundary) last name does not throw exception
		Assertions.assertAll( () -> {
			new Contact(contactId, firstName, repeat(testCharacter, lastNameBoundary), phoneNumber, address);
		});
	}
	
	@Test
	void givenLastNameLessThanBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that contact with less than 10 char (boundary) last name does not throw exception
		Assertions.assertAll( () -> {
			new Contact(contactId, firstName, repeat(testCharacter, lastNameBoundary - 1),  phoneNumber, address);
		});
	}
	
	@Test
	void givenLastNameNull_whenContactConstructed_thenExceptionThrown() {
		
		// Test that contact with null last name throws exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Contact(contactId, firstName, null, phoneNumber, address);
		});
	}

	@Test
	void givenContact_whenContactLastNameUpdated_thenAccessorMethodReturnsUpdatedValue() {
		
		// create new contact
		Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, address);
		
		// set last name
		contact.setLastName("Jones");
		
		// make sure last name was updated
		assertTrue(contact.getLastName().equals("Jones"));
	}
	
	@Test
	void givenPhoneNumberLongerThanBoundary_whenContactConstructed_thenExceptionThrown() {
		
		// Test that contact with longer then 10 char (boundary) phone number throws exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(contactId, firstName, lastName, repeat(testCharacter, phoneNumberBoundary + 1), address);
		});
	}
	
	@Test
	void givenPhoneNumberEqualToBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that contact with 10 char (boundary) phone numer does not throw exception
		Assertions.assertAll( () -> {
			new Contact(contactId, firstName, lastName, repeat(testCharacter, phoneNumberBoundary), address);
		});
	}
	@Test
	void givenPhoneNumberLessThanBoundary_whenContactConstructed_thenExceptionThrown() {
		
		// Test that contact with less than 10 char (boundary) phone number throws exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(contactId, firstName, lastName, repeat(testCharacter, phoneNumberBoundary - 1), address);
		});
	}
	
	@Test
	void givenPhoneNumberNull_whenContactConstructed_thenExceptionThrown() {
		
		// Test that contact with null phone number throws exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Contact(contactId, firstName, lastName, null, address);
		});
	}
	
	@Test
	void givenContact_whenContactPhoneNumberUpdated_thenAccessorMethodReturnsUpdatedValue() {
		
		// create a contact with default inputs
		Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, address);
		
		// Update contact phone number
		contact.setPhoneNumber("9876543210");
		
		// Assert that the contact phone number is the updated version
		assertTrue(contact.getPhoneNumber().equals("9876543210"));
	}
	@Test
	void givenAddressLongerThanBoundary_whenContactConstructed_thenExceptionThrown() {
		
		// Test that contact with too long of an address throws an exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(contactId, firstName, lastName, phoneNumber, repeat(testCharacter, addressBoundary + 1));
		});
	}
	
	@Test
	void givenAddressEqualToBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that a contact with an address at the boundary length (30 characters) does not throw an exception 
		Assertions.assertAll( () -> {
			new Contact(contactId, firstName, lastName, phoneNumber, repeat(testCharacter, addressBoundary));
		});
	}
	
	@Test
	void givenAddressLessThanBoundary_whenContactConstructed_thenNoExceptionThrown() {
		
		// Test that a contact with an address less than the boundary length (30 characters) does not throw an exception
		Assertions.assertAll(() -> {
			new Contact(contactId, firstName, lastName, phoneNumber, repeat(testCharacter, addressBoundary - 1));
		});
	}
	
	@Test
	void givenAddressNull_whenContactConstructed_thenExceptionThrown() {
		
		// Test that a contact with a null address throws an exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Contact(contactId, firstName, lastName, phoneNumber,null);
		});
	}
	
	@Test
	void givenContact_whenContactAddressUpdated_thenAccessorMethodReturnsUpdatedValue() {
		
		// Create a new contact
		Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, address);
		
		// Update the contact address
		contact.setAddress("321 1st Ave");
		
		// Test that the updated address is equal to the updated version
		assertTrue(contact.getAddress().equals("321 1st Ave"));
	}

	
	
}
