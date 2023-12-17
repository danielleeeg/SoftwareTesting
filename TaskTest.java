package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Task;

class TaskTest {
	protected String id, name, description, testCharacter;
	protected int idBoundary, nameBoundary, descriptionBoundary;
	
	@BeforeEach
	protected void setUp() {
		// class parameters that pass each of the requirements
		id = "123456";
		name = "add";
		description = "testing";
		
		// Character to use for all tests of boundary limits
		testCharacter = "1";
		
		// Boundaries for length of each class variable
		idBoundary = 10;
		nameBoundary = 20;
		descriptionBoundary = 50;
	}
	

	
	// Method for creating a string of a specified length
	// this is used to generate strings for testing boundaries
	private String repeat(String character, int length) {
		String outputString = new String(new char[length]).replace("\0", character);
		return outputString;
	}

	// Test test build
	@Test
	void givenTaskWithValidInput_whenAccessorMethodsCalled_thenAccurateValuesReturned() {
		// create new task
		Task task = new Task(id, name, description);
		
		// check that each variable was properly assigned to task
		assertTrue(task.getId().equals("123456"));
		assertTrue(task.getName().equals("add"));
		assertTrue(task.getDescription().equals("testing"));
	}
	
	// Test the build of a task with too long of an ID
	@Test
	void givenIdBoundaryLength_whenTaskConstructedWithIdLongerThanBoundary_thenExceptionThrown() {
		
		// Create a string with length equal to the idBoundary + 1 
		String testString = repeat(testCharacter, idBoundary + 1);
		
		// Make sure an exception is thrown when ID too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Task(testString, name, description);
		});

	}
	
	// Test the build of a task with an ID shorter than the boundary
	@Test
	void givenIdBoundaryLength_whenTaskConstructedWithIdShorterThanBoundary_thenNoExceptionThrown() {
		
		// Create a string with one less character than the boundary allows
		String testString = repeat(testCharacter, idBoundary - 1);
		
		// Ensure it passes without exception
		Assertions.assertAll( () -> {
		    new Task(testString, name, description);
		});

	}
	
	// Test the build of a task with an ID shorter than the boundary
	@Test
	void givenIdBoundaryLength_whenTaskConstructedWithIdEqualToBoundary_thenNoExceptionThrown() {
		
		// Create a string of lenth equal to the boundary allows
		String testString = repeat(testCharacter, idBoundary);
		
		// Ensure it passes without exception
		Assertions.assertAll( () -> {
		    new Task(testString, name, description);
		});

	}
	
	
	// Test to make sure an exception is thrown for a null ID
	@Test
	void givenIdBoundaryLength_whenTaskConstructedWithNullId_thenExceptionThrown() {
		
		// Ensure an exception is thrown for a null ID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Task(null, name, description);
		});

	}
	
	
	// Test to ensure exception is thrown if name is greater than boundary
	@Test
	void givenNameBoundaryLength_whenTaskConstructedWithNameLongerThanBoundary_thenExceptionThrown() {
		
		// Create a string one character greater than the name boundary
		String testString = repeat(testCharacter, nameBoundary + 1);
		
		// Make sure exception is thrown for too long of a name
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Task(id, testString, description);
		});

	}
	
	// Test to ensure  no exception thrown for character lengths less than boundary
	@Test
	void givenNameBoundaryLength_whenTaskConstructedWithNameShorterThanBoundary_thenNoExceptionThrown() {
		
		// Create a string with a character length one less than the boundary
		String testString = repeat(testCharacter, nameBoundary - 1);
		
		// Ensure it passes without exception
		Assertions.assertAll( () -> {
		    new Task(id, testString, description);
		});

	}
	
	// Test to ensure no exception is thrown when name is equal to boundary length
	@Test
	void givenNameBoundaryLength_whenTaskConstructedWithNameEqualToBoundary_thenNoExceptionThrown() {
		
		// Create a string with a character length equal to the boundary
		String testString = repeat(testCharacter, nameBoundary);
		
		// Ensure it passes without exception
		Assertions.assertAll( () -> {
		    new Task(id, testString, description);
		});

	}
	
	// Test for exception for null name
	@Test
	void givenNullName_whenTaskConstructed_thenExceptionThrown() {
		
		// Ensure an exception is thrown when the name is left null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Task(id, null, description);
		});

	}
	
	// Test to ensure an exception is thrown for descriptions longer than the boundary
	@Test
	void givenDescriptionLongerThanBoundary_whenTaskConstructed_thenExceptionThrown() {
		
		// Create a string one character longer than the boundary allows
		String testString = repeat(testCharacter, descriptionBoundary + 1);
		
		// Ensure an exception is thrown when the description is longer than the boundary allows
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Task(id, name, testString);
		});

	}
	
	
	// Test that no exception is thrown when the description is shorter than the boundary
	@Test
	void givenDescriptionShorterThanBoundary_whenTaskConstructed_thenNoExceptionThrown() {
		
		// Create a string with one less character than the boundary allows
		String testString = repeat(testCharacter, descriptionBoundary - 1);
		
		// Ensure it passes without exception
		Assertions.assertAll(() -> {
		    new Task(id, name, testString);
		});

	}
	
	
	// Test that no exception is thrown when the description is the same length as the boundary
	@Test
	void givenDescriptionEqualToBoundary_whenTaskConstructed_thenNoExceptionThrown() {
		
		// Create string with character length equal to boundary
		String testString = repeat(testCharacter, descriptionBoundary);
		
		// Ensure it passes without exception
		Assertions.assertAll(() -> {
		    new Task(id, name, testString);
		});

	}
	
	// Test that exception is thrown when description is left null
	@Test
	void givenNullDescription_whenTaskConstructed_thenExceptionThrown() {
		
		// Ensure the appropriate exception is thrown when description is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Task(id, name, null);
		});

	}
	
	@Test
	void givenTask_whenNameUpdated_thenStoredTaskHasUpdatedName() {
		Task testTask = new Task(id, name, description);
		
		// Assert that name is equal to test name
		assertTrue(testTask.getName().equals("add"));
		
		// Update task name
		testTask.setName("substract");
		
		// Assert than name has been updated
		assertTrue(testTask.getName().equals("substract"));
	}
	
	@Test
	void givenTask_whenDescriptionUpdated_thenStoredTaskHasUpdatedDescription() {
		Task testTask = new Task(id, name, description);
		
		// Assert that name is equal to test name
		assertTrue(testTask.getDescription().equals("testing"));
		
		// Update task name
		testTask.setDescription("calculate");
		
		// Assert than name has been updated
		assertTrue(testTask.getDescription().equals("calculate"));
	}
	
	// Tear down method
	@AfterEach
	protected void tearDown() {
		id = null;
		name = null;
		description = null;
		testCharacter = null;				
	}
}
