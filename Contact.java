
package main;


public class Contact {

	private String contactId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	/* validateIdentifiers ensures the entered ID, name, and/or last name meets specifications (under 10 characters and not null)
	 * @param id: string unique identifier, name, or last name for contact
	 */
	@SuppressWarnings("unused")
	private void validateIdentifier(final String id) {
		if (id == null || id.length() > 10) {
			throw new IllegalArgumentException(id + " too long. Must be between 0 and 10 characters.");
		}
		
	}
	
	/* validatePhoneNumber ensures the entered phone number meets specifications (10 characters and not null)
	 * @param id: string phone number
	 */
	@SuppressWarnings("unused")
	private void validatePhoneNumber(final String phone) {
		
		if (phone == null || phone.length() != 10) {
			throw new IllegalArgumentException("Phone numer must be 10 characters.");
		}
	}
	
	/* validateAddress ensures the entered address number meets specifications (no more than 30 characters and not null)
	 * @param id: string address
	 */
	@SuppressWarnings("unused")
	private void validateAddress(final String address) {
		if (address == null || address.length() > 30) {
			throw new IllegalArgumentException(address + "too long. Must be between 0 and 30 characters.");
		}

	}
	
	/* Constructor; requires input because fields cannot be null
	 */
	public Contact(String contactId, String firstName, String lastName, String phoneNumber, String address) {
		validateIdentifier(contactId);
		validateIdentifier(firstName);
		validateIdentifier(lastName);
		validatePhoneNumber(phoneNumber);
		validateAddress(address);
		
		
		// Once all data has been validated, assign private variables to constructor variables
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	/* Mutator methods:
	 * The only attributes that can be mutated are first name, last name, phone number, and address. 
	 * The contact ID cannot be changed.
	 * 
	 * setFirstName: mutator method for first name; validates input before setting private variable
	 * @param: String first name of contact
	 * @return: none
	 */
	
	public void setFirstName(String firstName) {
		validateIdentifier(firstName);
		
		this.firstName = firstName;

	}
	/* setLastName: mutator method for last name; validates input before setting private variable
	 * @param: String last name of contact
	 * @return: none
	 */

	public void setLastName(String lastName) {
		validateIdentifier(lastName);
		this.lastName = lastName;
	}
	
	/* setPhoneNumber: mutator method for phone number; validates input before setting private variable
	 * @param: String phone number of contact
	 * @return: none
	 */
	
	public void setPhoneNumber(String phoneNumber) {
		validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}
	
	/* setAddress: mutator method for address; validates input before setting private variable
	 * @param: String address of contact
	 * @return: none
	 */
	public void setAddress(String address) {
		validateAddress(address);
		this.address = address;
	}
	
	
	/* Accessor Methods: used to return the value of each of the private variables for contact:
	 * contact ID, first name, last name, phone number, address
	 * 
	 * getContactId: returns the contact ID of a contact 
	 * @param: none
	 * @return: String contact ID 
	 */
	public String getId() {
		return this.contactId;
	}
	
	/* getFirstName: returns the first name of a contact 
	 * @param: none
	 * @return: String first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/* getLastName: returns the last name of a contact 
	 * @param: none
	 * @return: String last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/* getPhoneNumber: returns the phone number of a contact 
	 * @param: none
	 * @return: String phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	/* getAddress: returns the address of a contact 
	 * @param: none
	 * @return: String address
	 */
	public String getAddress() {
		return this.address;
	}
}
