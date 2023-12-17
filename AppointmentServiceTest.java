package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Appointment;
import main.AppointmentService;

class AppointmentServiceTest {
	private AppointmentService appointmentService;
	private String description;
	private Date date;
	@BeforeEach
	void setUp() throws Exception {
		appointmentService = AppointmentService.getInstance();
		
		// set parameters that will pass tests
		date = new Date(System.currentTimeMillis()+3600000);
		description = "testing";
		
	}
	
	// Test adding a new appointment to the service
	@Test
	void givenEmptyTree_whenAppointmentAdded_thenAppointmentCanBeFoundInTree() {
		// Make sure the appointment does not yet exist in the system
		assertNull(appointmentService.getAppointmentTree().search("0"));
		
		// Create a new appointment
		Appointment appointment = new Appointment("0", date, description);
		
		// Add appointment to the appointment service
		appointmentService.newAppointment(appointment);
		
		// Verify the appointment was added based on ID
		assertNotNull(appointmentService.getAppointmentTree().search("0"));
				
	}
	
	// Method for testing the deletion of an appointment
	@Test
	void givenTreeWithAppointment_whenAppointmentDeleted_thenAppointmentNoLongerExists() {
		// add an appointment to the service
		Appointment appointment = new Appointment("1", date, description);
		
		// Add to appointment tree
		appointmentService.newAppointment(appointment);
		
		// Verify the appointment was added
		assertNotNull(appointmentService.getAppointmentTree().search("1"));
		
		// Delete appointment from tree
		appointmentService.deleteAppointment("1");
		
		// Verify the appointment was deleted
		assertNull(appointmentService.getAppointmentTree().search("1"));
	}
	
	// Method for verifying that only unique ids can be added to appointment service
	@Test
	void givenTreeWithAppointment_whenDuplicateIdAdded_thenExceptionThrown() {
		// add an appointment to the service
		Appointment appointment = new Appointment("2", date, description);
		
		// Add to appointment tree
		appointmentService.newAppointment(appointment);
		
		// Verify the appointment was added
		assertNotNull(appointmentService.getAppointmentTree().search("2"));
		
		// add an appointment to the service
		Appointment appointment2 = new Appointment("2", date, description);
		
		// Assert that an error is thrown when trying to add the duplicate ID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			appointmentService.newAppointment(appointment2);
		});

	}
	
	// Method for verifying that a date can be updated for an appointment
	@Test
	void givenAppointmentTreeWithAppointment_whenAppointmentDateUpdated_thenDateSavedToAppointmentNode() {
		// add appointment 
		Appointment appointment = new Appointment("3", date, description);
		
		// add to service
		appointmentService.newAppointment(appointment);
		
		// Verify the date of the appointment
		assertEquals(date, appointmentService.getAppointmentTree().search("3").getDate());
		
		// Create a new date to update to
		Date newDate = new Date(System.currentTimeMillis()+7200000);
		
		// Update date
		appointmentService.updateDate("3", newDate);
		
		// Verify the date was properly updated
		assertEquals(newDate, appointmentService.getAppointmentTree().search("3").getDate());
	}
	
	// Method for verifying that a description can be updated for an appointment
	@Test 
	void givenAppointmentTreeWithAppointment_whenAppointmentDescriptionUpdated_thenDescriptionSavedToAppointmentNode(){
		// add appointment
		Appointment appointment = new Appointment("4", date, description);
		
		// add to service
		appointmentService.newAppointment(appointment);
		
		// Verify the description of the appointment in the service
		assertEquals(description, appointmentService.getAppointmentTree().search("4").getDescription());
		
		// Update description
		appointmentService.updateDescription("4", "This is a test of updating the description");
		
		// Verify the description was properly updated
		assertEquals("This is a test of updating the description", appointmentService.getAppointmentTree().search("4").getDescription());
		
	}
	
	// Method for verifying that an exception is thrown when a date is being updated for a null appointment
		@Test
		void givenEmptyTree_whenDateUpdated_thenExceptionThrown() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				appointmentService.updateDate("5", new Date(System.currentTimeMillis()+1000));
				});
			}

		

	
	// Method for verifying that an exception is thrown when a description is being updated for a null appointment
	@Test
	void givenEmptyTree_whenDescriptionUpdated_thenExceptionThrown() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			appointmentService.updateDescription("6", "test null description");
			});
		}

	

	

}
