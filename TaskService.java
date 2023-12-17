package main;

/**
 * @author Danielle Eeg
 *
 */
public class TaskService {
	// Use singleton pattern to create a single instance of the task list
		private static TaskService instance;
		
		// binary search tree task object
		private TaskBinarySearchTree taskTree;
		
		// create a new instance of the binary search tree
		private TaskService() {
			taskTree = new TaskBinarySearchTree();
		}
		
		// Public method to access the singleton instance
	    public static TaskService getInstance() {
	        if (instance == null) {
	            instance = new TaskService();
	        }
	        return instance;
	    }

	    // Public method to interact with the BST
	    public TaskBinarySearchTree getTaskTree() {
	        return taskTree;
	    }
	    
	    // public method for creating a new task and adding to taskTree
	    public void newTask(Task task) {
	    	
	    	// check if a task with the same ID has already been added
	    	if (taskTree.search(task.getId()) != null) {
	    		throw new IllegalArgumentException("Task with id " + task.getId() + " already exists.");
	    	}
	    	
	    	// add task to tree
	    	taskTree.insert(task);
	    }
	    
	    // public method for deleting task from tasktree based on ID
	    public void deleteTask(String id) {
	    	taskTree.delete(id);
	    }
	    
	    // Public method for updating a task name
	    public void updateName(String id, String name) {
	    	//search for task in tree
	    	Task task = taskTree.search(id);
	    	
	    	// throw exeption if no task found
	    	if (task == null) {
	    		throw new IllegalArgumentException("No task found with id " + id + ".");
	    	}
	    	// otherwise update name
	    	else {
	    		task.setName(name);
	    	}
	    }
	    
	 // Public method for updating a task description
	    public void updateDescription(String id, String description) {
	    	//search for task in tree
	    	Task task = taskTree.search(id);
	    	
	    	// throw exeption if no task found
	    	if (task == null) {
	    		throw new IllegalArgumentException("No task found with id " + id + ".");
	    	}
	    	// otherwise update name
	    	else {
	    		task.setDescription(description);
	    	}
	    }

}