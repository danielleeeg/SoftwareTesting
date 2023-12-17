/**
 * 
 */
package main;

import java.util.Date;

/**
 * @author Danielle Eeg
 *
 */
public class AppointmentService {
	// Use singleton pattern to create a single instance of the appointment list
		private static AppointmentService instance;
		
		// binary search tree appointment object
		private AppointmentBinarySearchTree appointmentTree;
		
		// create a new instance of the binary search tree
		private AppointmentService() {
			appointmentTree = new AppointmentBinarySearchTree();
		}
		
		// Public method to access the singleton instance
	    public static AppointmentService getInstance() {
	        if (instance == null) {
	            instance = new AppointmentService();
	        }
	        return instance;
	    }

	    // Public method to interact with the BST
	    public AppointmentBinarySearchTree getAppointmentTree() {
	        return appointmentTree;
	    }
	    
	    // public method for creating a new appointment and adding to appointmentTree
	    public void newAppointment(Appointment appointment) {
	    	
	    	// check if a appointment with the same ID has already been added
	    	if (appointmentTree.search(appointment.getId()) != null) {
	    		throw new IllegalArgumentException("Appointment with id " + appointment.getId() + " already exists.");
	    	}
	    	
	    	// add appointment to tree
	    	appointmentTree.insert(appointment);
	    }
	    
	    // public method for deleting appointment from appointmenttree based on ID
	    public void deleteAppointment(String id) {
	    	appointmentTree.delete(id);
	    }
	    
	    // Public method for updating a appointment date
	    public void updateDate(String id, Date date) {
	    	//search for appointment in tree
	    	Appointment appointment = appointmentTree.search(id);
	    	
	    	// throw exception if no appointment found
	    	if (appointment == null) {
	    		throw new IllegalArgumentException("No appointment found with id " + id + ".");
	    	}
	    	// otherwise update date
	    	else {
	    		appointment.setDate(date);
	    	}
	    }
	    
	 // Public method for updating a appointment description
	    public void updateDescription(String id, String description) {
	    	//search for appointment in tree
	    	Appointment appointment = appointmentTree.search(id);
	    	
	    	// throw exception if no appointment found
	    	if (appointment == null) {
	    		throw new IllegalArgumentException("No appointment found with id " + id + ".");
	    	}
	    	// otherwise update description
	    	else {
	    		appointment.setDescription(description);
	    	}
	    }

}