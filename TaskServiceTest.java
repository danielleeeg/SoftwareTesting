/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Task;
import main.TaskService;

/**
 * @author Danielle Eeg
 *
 */
class TaskServiceTest {

	protected TaskService taskService;
	protected String id, name, description;
	
	@BeforeEach
	void setUp(){
		taskService = TaskService.getInstance();
		
		// class parameters that pass each of the requirements
		id = "123456";
		name = "add";
		description = "testing";
		
	}
	
	
	// Method for testing the NewTask method 
	@Test
	void givenEmptyTree_whenTaskAdded_thenTaskStoredInTree() {
		// make sure no contact exists with id
		assertNull(taskService.getTaskTree().search("0"));
		
		// create a new task
		Task task = new Task("0", name, description);
		
		// add task to taskService
		taskService.newTask(task);
		
		// search the task to maje sure its not null
		assertNotNull(taskService.getTaskTree().search("0"));
	}
	
	// Method for deleting the NewTask method 
	@Test
	void givenTaskInTree_whenTaskDeleted_thenTaskNoLongerInTree() {
		// create a new task
		Task task = new Task("1", name, description);
		
		// add task to taskService
		taskService.newTask(task);
			
		// search the task to make sure its not null
		assertNotNull(taskService.getTaskTree().search("1"));
		
		// delete task
		taskService.deleteTask("1");
		
		// make sure task was properly deleted
		assertNull(taskService.getTaskTree().search("1"));
		
	}
	
	// method for testing to make sure only unique Ids can be added
	@Test
	void givenTaskInTree_whenTaskWithSameIdInserted_thenExceptionThrown() {
		// create a new task
		Task task = new Task("2", name, description);
		
		// add task to taskService
		taskService.newTask(task);
					
		// search the task to make sure its not null
		assertNotNull(taskService.getTaskTree().search("2"));
		
		// create a new task
		Task task2 = new Task("2", "test", "description");
				
		// add task to taskService
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    taskService.newTask(task2);
		});
							
	}
	
	// test to verify a tasks name after it has been updated
	@Test
	void givenTaskInTree_whenNameUpdated_thenTaskInTreeHasUpdatedName() {
		// create a new task
		Task task = new Task("3", name, description);
		
		// add task to taskService
		taskService.newTask(task);
		
		// Assert task name
		assertEquals("add", taskService.getTaskTree().search("3").getName());
		
		// Update last name
		taskService.updateName("3", "substract");

		// Assert task name
		assertEquals("substract", taskService.getTaskTree().search("3").getName());
	}
	
	// test to verify a task's description after it has been updated
	@Test
	void givenTaskInTree_whenDescriptionUpdated_thenTaskInTreeHasUpdatedDescription() {
		// create a new task
		Task task = new Task("4", name, description);
		
		// add task to taskService
		taskService.newTask(task);
		
		// Assert task name
		assertEquals("testing", taskService.getTaskTree().search("4").getDescription());
		
		// Update last name
		taskService.updateDescription("4", "check check");

		// Assert task name
		assertEquals("check check", taskService.getTaskTree().search("4").getDescription());
	}
	
	// test to verify that trying to update the name of a task that does not exist will throw an error
		@Test
	void givenTaskTree_whenNameUpdatedForNonExistantTask_thenExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		taskService.updateName("5", name);
		});
	}
	
	// test to verify that trying to update the description of a task that does not exist will throw an error
	@Test
	void givenTaskTree_whenDescriptionUpdatedForNonExistantTask_thenExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			taskService.updateDescription("5", description);
		});
	}
	
	// Tear down
	@AfterEach
	void tearDown(){
		taskService = null;
		id = null;
		name = null;
		description = null;
		
	}
}
