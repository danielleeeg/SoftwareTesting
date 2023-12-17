package main;

/**
 * @author Danielle Eeg
 *
 */
class TaskNode {
    Task task;
    TaskNode left, right;
    
    // create node method
    public TaskNode(Task task) {
        this.task = task;
        left = right = null;
    }
    
}
public class TaskBinarySearchTree {
    private TaskNode root;

    // Set node as null
    public TaskBinarySearchTree() {
        root = null;
    }
    
 // Insert a method used to test the constructor: accessor method for the root
    public TaskNode getRoot(){
    	return this.root;
    }
   

    // Insert a Task into the BST based on the id
    public void insert(Task task) {
        root = insertRecursive(root, task);
    }
    
    

    // Helper method for inserting a Task recursively
    private TaskNode insertRecursive(TaskNode root, Task task) {
    	
    	// if no node exuists yet, create a new node containing task
        if (root == null) {
            return new TaskNode(task);
        }

        // Compare the id for insertion
        int compareValue = task.getId().compareTo(root.task.getId());
        
        // if the value is less than the current root, recurse left
        if (compareValue < 0) {
            root.left = insertRecursive(root.left, task);
        } 
        // if the value is greater than the current root, recuses right
        else if (compareValue > 0) {
            root.right = insertRecursive(root.right, task);
        } 
        
        // otherwise, the ID already exists in the tree; throw an error
        else {
            // Task with the same ID already exists, throw error
        	throw new IllegalArgumentException("Task ID already exists in task list");
        }

        return root;
    }

    // Delete a Task from the BST based on the id
    public void delete(String id) {
        root = deleteRec(root, id);
    }

    // Helper method for deleting a Task recursively
    private TaskNode deleteRec(TaskNode root, String id) {
        if (root == null) {
            return root;
        }

        // Compare the id for deletion
        int compareValue = id.compareTo(root.task.getId());
        
        // if the value is lass than the root, recurse left
        if (compareValue < 0) {
            root.left = deleteRec(root.left, id);
        } 
        
        // if the value is greater than the root, recurse right
        else if (compareValue > 0) {
            root.right = deleteRec(root.right, id);
        } 
        
        else {
            // TaskNode with only one child or no child
            if (root.left == null) {
                return root.right;
            } 
            
            else if (root.right == null) {
                return root.left;
            }

            // TaskNode with two children, get the inorder successor (smallest in the right subtree)
            root.task = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.task.getId());
        }

        return root;
    }
    // Find the minimum value node in the BST
    private Task minValue(TaskNode node) {
        Task minValue = node.task;
        while (node.left != null) {
            minValue = node.left.task;
            node = node.left;
        }
        return minValue;
    }
    
    // Find and return a Task by id
    public Task search(String id) {
        return searchRecursive(root, id);
    }

    // Helper method for searching for a Task recursively
    private Task searchRecursive(TaskNode root, String id) {
        if (root == null) {
            return null; // Task not found
        }

        int compareValue = id.compareTo(root.task.getId());
        
        // if the root Id is equal, return
        if (compareValue == 0) {
            return root.task; // Task found
        } 
        
        // if the root ID is less, search left
        else if (compareValue < 0) {
            return searchRecursive(root.left, id); 
        } 
        
        // if the root id is greater than, search right
        else {
            return searchRecursive(root.right, id); 
        }
    }
    
    
}
