package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Appointment;

class AppointmentTest {
	private Date testDatePast, testDateFuture;
	private String description, id;
	private int idBoundary, descriptionBoundary;
	
	@BeforeEach
	void setUp() throws Exception {
		
		
		// Date one second in past
		testDatePast = new Date(System.currentTimeMillis() - 1000);
		
		// Date one second in future
		testDateFuture = new Date(System.currentTimeMillis() + 1000);
		
		// Create id and description variables that will pass tests
		id = repeat("1", 6);
		description = "description";
		
		idBoundary = 10;
		descriptionBoundary = 50;
		
				
	}
	
	// Method for creating a string of a specified length
	// this is used to generate strings for testing boundaries
	private String repeat(String character, int length) {
		String outputString = new String(new char[length]).replace("\0", character);
		return outputString;
	}


	@Test
	void givenValidInputData_whenContructorIsCalled_thenAllDataInAppointmentIsSaved() {
		//Create a new appointment
		Appointment appointment = new Appointment(id,  testDateFuture, description);
		
		// Verify id was updated
		assertTrue(appointment.getId().equals("111111"));
		
		// because time sensitive, use same variable to test
		assertTrue(appointment.getDate().equals(testDateFuture));
		
		// verify description was updated
		assertTrue(appointment.getDescription().equals("description"));
	}
	
	@Test
	void givenAppointment_whenIdIsLongerThanBoundary_thenThrowException() {
		//verify that creating an appointment with too long of an ID throws an error
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Appointment(repeat("1", idBoundary + 1), testDateFuture, description);
		});
	}
	
	@Test
	void givenAppointment_whenIdIsEqualToBoundary_thenNoExceptionThrown() {
		//verify that creating an appointment ID equal to boundary length does not throw an error
		Assertions.assertAll(() -> {
		    new Appointment(repeat("1", idBoundary), testDateFuture, description);
		});
	}
	
	@Test
	void givenAppointment_whenIdIsShorterThanBoundary_thenNoExceptionThrown() {
		//verify that creating an appointment with an ID shorter than the boundary does not throw an error.
		Assertions.assertAll(() -> {
		    new Appointment(repeat("1", idBoundary - 1), testDateFuture, description);
		});
	}
	
	@Test
	void givenAppointment_whenIdNull_thenThrowException() {
		//verify that creating an appointment with null ID throws an error
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Appointment(null, testDateFuture, description);
		});
	}
	
	
	@Test
	void givenAppointment_whenDateInPast_thenThrowException() {
		//verify that creating an appointment with date in past throws an error
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Appointment(id, testDatePast, description);
		});

	}
	
	@Test
	void givenAppointment_whenDateIsCurrentTime_thenNoExceptionThrown() {
		//verify that creating an appointment with current date does not throw error;
		// since setUp is run before each test, the date should be accurate to the second.
		Assertions.assertAll(() -> {
		    new Appointment(id, new Date(), description);
		});

	}
	
	@Test
	void givenAppointment_whenDateInFuture_thenNoExceptionThrown() {
		//verify that creating an appointment with future date does not throw error;
		// since setUp is run before each test, the date should be accurate to the second.
		Assertions.assertAll(() -> {
		    new Appointment(id, testDateFuture, description);
		});

	}
	
	@Test
	void givenAppointment_whenDateNull_thenThrowException() {
		//verify that creating an appointment with null date throws an error
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Appointment(id, null, description);
		});

	}
	
	@Test
	void givenAppointment_whenDescriptionLongerBoundary_thenThrowException() {
		//verify that creating an appointment with too long of an Description throws an error
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Appointment(id, testDateFuture, repeat("1", descriptionBoundary + 1));
		});
	}
	
	@Test
	void givenAppointment_whenDescriptionEqualToBoundary_thenNoExceptionThrown() {
		//verify that creating an appointment Description equal to boundary length does not throw an error
		Assertions.assertAll(() -> {
		    new Appointment(id, testDateFuture, repeat("1", descriptionBoundary));
		});
	}
	
	@Test
	void givenAppointment_whenDescriptionShorterThanBoundary_thenNoExceptionThrown() {
		//verify that creating an appointment with an Description shorter than the boundary does not throw an error.
		Assertions.assertAll(() -> {
		    new Appointment(id, testDateFuture, repeat("1", descriptionBoundary - 1));
		});
	}
	
	@Test
	void givenAppointment_whenNullDescription_thenThrowError() {
		//verify that creating an appointment with a null Description throws an error
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    new Appointment(id, testDateFuture, null);
		});
	}
	
	@Test
	void givenAppointment_whenSetDate_thenGetDate() {
		// Create an appointment object
		Appointment appointment = new Appointment(id, testDateFuture, description);
		
		// Check that the appointment date is equal to testDateFuture
		assertEquals(testDateFuture, appointment.getDate());
		
		// Create a new date
		Date newDate = new Date(System.currentTimeMillis()+3600*1000);
		
		// Update date of appointment
		appointment.setDate(newDate);
		
		// check that date was updated
		assertEquals(newDate, appointment.getDate());
	}
	
	@Test
	void givenAppointment_whenSetDescription_thenGetDescription() {
		// Create an appointment object
		Appointment appointment = new Appointment(id, testDateFuture, description);
		
		// Check that description was properly set
		assertEquals(description, appointment.getDescription());
		
		// Update the description
		appointment.setDescription("This is a test");
		
		// Check that description was updated
		assertEquals("This is a test", appointment.getDescription());
	}

}
