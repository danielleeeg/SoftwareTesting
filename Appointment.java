/**
 * 
 */
package main;

import java.util.Date;

/**
 * @author Danielle Eeg
 *
 */
public class Appointment {
	private String id;
	private Date date;
	private String description;
	
	private int idBoundary = 10;
	private int descriptionBoundary = 50;
	
	
	//Constructor
	public Appointment(String id, Date date, String description){
		
		// Validate inputs
		validateStringInput(id, idBoundary);
		validateDateInput(date);
		validateStringInput(description, descriptionBoundary);
		
		// Assign private variables
		this.id = id;
		this.date = date;
		this.description = description;
	}
	
	
	// method for validating strings; coded generically to accommodate different boundaries
	private void validateStringInput(String input, int boundary) {
		
		// Throw an exception if the input is null or longer than the boundary
		if (input == null || input.length() > boundary) {
			throw new IllegalArgumentException("Value must not be null and it must have less than " + boundary + "characters.");
		}
	}
	
	// method for validating dates --  must be in the future
	private void validateDateInput(Date date) {
				
		// Throw an exception if the date field is null or in the past
		if (date == null || date.before(new Date())) {
			throw new IllegalArgumentException("Date must not be null or already past");
		}
	}
	
	
	// Accessor methods:
	// Method for getting the appointment ID
	public String getId() {
		
		// return appointment Id
		return this.id;
	}
	
	// Method for getting appointment date
	public Date getDate() {
		
		// Return appointment date
		return this.date;
	}
	
	// Method for getting appointment description
	public String getDescription() {
		
		// return appointment description
		return this.description;
	}
	
	// Mutator methods
	// Method for updating appointment date
	public void setDate(Date date) {
		
		// validate date, throw exception if in past or null
		validateDateInput(date);
		
		// update date if no exception thrown
		this.date = date;
	}
	
	// Method for updating appointment description
	public void setDescription(String description) {
		
		// Validate that description is within bounds and not null
		validateStringInput(description, descriptionBoundary);
		
		// Update description if no exception thrown
		this.description = description;
	}
	
}
