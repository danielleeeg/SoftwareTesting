package main;

/**
 * @author Danielle Eeg
 *
 */
class AppointmentNode {
    Appointment appointment;
    AppointmentNode left, right;
    
    // create node method
    public AppointmentNode(Appointment appointment) {
        this.appointment = appointment;
        left = right = null;
    }
    
}
public class AppointmentBinarySearchTree {
    private AppointmentNode root;

    // Set node as null
    public AppointmentBinarySearchTree() {
        root = null;
    }
    
 // Insert a method used to test the constructor: accessor method for the root
    public AppointmentNode getRoot(){
    	return this.root;
    }
   

    // Insert a Appointment into the BST based on the id
    public void insert(Appointment appointment) {
        root = insertRecursive(root, appointment);
    }
    
    

    // Helper method for inserting a Appointment recursively
    private AppointmentNode insertRecursive(AppointmentNode root, Appointment appointment) {
    	
    	// if no node exists yet, create a new node containing appointment
        if (root == null) {
            return new AppointmentNode(appointment);
        }

        // Compare the id for insertion
        int compareValue = appointment.getId().compareTo(root.appointment.getId());
        
        // if the value is less than the current root, recurse left
        if (compareValue < 0) {
            root.left = insertRecursive(root.left, appointment);
        } 
        // if the value is greater than the current root, recuses right
        else if (compareValue > 0) {
            root.right = insertRecursive(root.right, appointment);
        } 
        
        // otherwise, the ID already exists in the tree; throw an error
        else {
            // Appointment with the same ID already exists, throw error
        	throw new IllegalArgumentException("Appointment ID already exists in appointment list");
        }

        return root;
    }

    // Delete a Appointment from the BST based on the id
    public void delete(String id) {
        root = deleteRec(root, id);
    }

    // Helper method for deleting a Appointment recursively
    private AppointmentNode deleteRec(AppointmentNode root, String id) {
        if (root == null) {
            return root;
        }

        // Compare the id for deletion
        int compareValue = id.compareTo(root.appointment.getId());
        
        // if the value is lass than the root, recurse left
        if (compareValue < 0) {
            root.left = deleteRec(root.left, id);
        } 
        
        // if the value is greater than the root, recurse right
        else if (compareValue > 0) {
            root.right = deleteRec(root.right, id);
        } 
        
        else {
            // AppointmentNode with only one child or no child
            if (root.left == null) {
                return root.right;
            } 
            
            else if (root.right == null) {
                return root.left;
            }

            // AppointmentNode with two children, get the inorder successor (smallest in the right subtree)
            root.appointment = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.appointment.getId());
        }

        return root;
    }
    // Find the minimum value node in the BST
    private Appointment minValue(AppointmentNode node) {
        Appointment minValue = node.appointment;
        while (node.left != null) {
            minValue = node.left.appointment;
            node = node.left;
        }
        return minValue;
    }
    
    // Find and return a Appointment by id
    public Appointment search(String id) {
        return searchRecursive(root, id);
    }

    // Helper method for searching for a Appointment recursively
    private Appointment searchRecursive(AppointmentNode root, String id) {
        if (root == null) {
            return null; // Appointment not found
        }

        int compareValue = id.compareTo(root.appointment.getId());
        
        // if the root Id is equal, return
        if (compareValue == 0) {
            return root.appointment; // Appointment found
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
