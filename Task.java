package main;

/**
 * @author Danielle Eeg
 *
 */

public class Task {
	private String id;
	private String name;
	private String description;
	
	// variables for setting boundaries
	private int idBoundary = 10;
	private int nameBoundary = 20;
	private int descriptionBoundary = 50;
	
	// Constructor
	public Task(String id, String name, String description) {
		
		// validate all inputs
		validateInput(id, idBoundary);
		validateInput(name, nameBoundary);
		validateInput(description, descriptionBoundary);
		
		// assign private variables
		this.id = id;
		this.name = name;
		this.description = description;
		
	}
	
	// Function for validating all inputs based on a boundary value
	@SuppressWarnings("unused")
	private void validateInput(final String input, final int boundary) {
		if (input == null || input.length() > boundary ) {
			throw new IllegalArgumentException("Value must be between 0 and " + Integer.toString(boundary) + " characters.");
		}
	}
	
	/*
	 * Accessor methods
	 */
	
	// accessor for taskId
	public String getId() {
		return this.id;
	}
	
	// accessor for task name
	public String getName() {
		return this.name;
	}
	
	// accessor for task description
	public String getDescription() {
		return this.description;
	}
	
	/*
	 * Mutator methods
	 */
	
	// mutator for task name
	public void setName(String name) {
		
		// validate input based on boundary
		validateInput(name, nameBoundary);
		
		// update name if validation passes
		this.name = name;
	}
	
	// mutator for task description
	public void setDescription(String description) {
		
		// validate description based on boundary
		validateInput(description, descriptionBoundary);
		
		// update description if validation passes
		this.description = description;
	}
	
}
