package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;

import main.AppointmentBinarySearchTree;
import main.Appointment;
/**
 * @author Danielle Eeg
 *
 */
class AppointmentBinarySearchTreeTest {
	Appointment testAppointment1, testAppointment2, testAppointment3, testAppointment4, testAppointment5;
	Date testDate;
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	
	@BeforeEach
	void setUp() throws Exception {
		// Date one hour in future
		testDate = new Date(System.currentTimeMillis() + 3600 * 1000);
				
		testAppointment1 = new Appointment("1", testDate, "left of base");
		testAppointment2 = new Appointment("2", testDate, "base of tree");
		testAppointment3 = new Appointment("3", testDate, "right of base");
		testAppointment4 = new Appointment("1", testDate, "Equal to another appointment");
		testAppointment5 = new Appointment("5", testDate, "Used for testing min value search function");
		
	}
	// test create a binary search tree
	@Test
	void givenAppointmentBinarySearchTreeClass_whenContructorCalled_thenAppointmentBinarySearchTreeWithNullRootExists() {
		// create a binary search tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
		
		// verify that the root is null, and there are therefore, no other items in the tree
		assertEquals(null, tree.getRoot());
	}

	// test to ensure appointments can be added, but only when they have unique IDs
	@Test
	void givenAppointmentBinarySearchTree_whenNodesAdded_thenOnlyUniqueIdAppointmentsAdded() {
		// create a binary search tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
		
		// insert appointment2 into binary searchTree
		tree.insert(testAppointment2);
		
		// Verify the test was added to the root
		assertEquals(testAppointment2, tree.search("2"));
		
		// insert another appointment so that the value will be inserted to the left of the root
		tree.insert(testAppointment1);
		
		// Verify the test was added to the root
		assertEquals(testAppointment1, tree.search("1"));
		
		// insert another appointment so that the value will be inserted to the right of the root
		tree.insert(testAppointment3);
		
		// Verify the test was added to the root
		assertEquals(testAppointment3, tree.search("3"));
		
		// insert another appointment so the value will be rejected with an illegal argument exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			tree.insert(testAppointment4);;
		});
	}
	
	// test for deleting a node from an empty tree
	@Test
	void givenEmptyTree_whenItemDeleted_thenThrowError() {
		// test deleting a node from an empty tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
		
		// delete random ID from tree
		tree.delete("24");
		
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
	}
	
	// test for deleting a node from the left branch of a tree
	@Test
	void givenTreeWithLeftBranch_whenLeftNodeDeleted_thenLeftNodeNull() {
		// test deleting a node from an empty tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert appointment2 into binary searchTree
		tree.insert(testAppointment2);
		
		// Verify the appointment was added to the root
		assertEquals(testAppointment2, tree.search("2"));
		
		// insert another appointment so that the value will be inserted to the left of the root
		tree.insert(testAppointment1);
		
		// Verify the test was added to the root
		assertEquals(testAppointment1, tree.search("1"));
		
		
		// Delete node to left of root
		tree.delete("1");
		
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("1"));
			
	}
	
	// test for deleting a node from the right branch of a tree
	@Test
	void givenTreeWithRightBranch_whenNodeDeleted_thenRightNodeNull() {
		// test deleting a node from an empty tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert appointment2 into binary searchTree
		tree.insert(testAppointment2);
		
		// Verify the appointment was added to the root
		assertEquals(testAppointment2, tree.search("2"));
		
		// insert another appointment so that the value will be inserted to the right of the root
		tree.insert(testAppointment3);
		
		// Verify the test was added to the root
		assertEquals(testAppointment3, tree.search("3"));
		
		// Delete node to right of the root
		tree.delete("3");
				
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("3"));		
			
	}
	
	// test for deleting a node with no children to the right
	@Test
	void givenNodeWithOnlyLeftChildren_whenParentDeleted_thenLeftChildrenBecomeRoot() {
		// test deleting a node from an empty tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert appointment2 into binary searchTree
		tree.insert(testAppointment2);
		
		// Verify the test was added to the root
		assertEquals(testAppointment2, tree.search("2"));
		
		// insert another appointment so that the value will be inserted to the left of the root
		tree.insert(testAppointment1);
		
		// Verify the appointment was added to the root
		assertEquals(testAppointment1, tree.search("1"));
		
		// Delete root with no right children
		tree.delete("2");
		
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("2"));
		
		// Make sure the left node became the root
		assertNotNull(tree.getRoot());
		
	}
	
	// test for deleting node with two children (one left and one right)
	@Test
	void givenNodeWithTwoChildren_whenRootDeleted_thenNodeNoLongerInTree() {
		// test deleting a node from an empty tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert appointment2 into binary searchTree
		tree.insert(testAppointment2);
		
		// Verify the appointment was added to the root
		assertEquals(testAppointment2, tree.search("2"));
		
		// insert another appointment so that the value will be inserted to the left of the root
		tree.insert(testAppointment1);
		
		// Verify the appointment was added
		assertEquals(testAppointment1, tree.search("1"));
		
		// insert another appointment so that the value will be inserted to the right of the root
		tree.insert(testAppointment3);
		
		// Verify the appointment was added 
		assertEquals(testAppointment3, tree.search("3"));
		
		// Delete root node
		tree.delete("2");
		
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("2"));
		
		// Verfiy root not null
		assertNotNull(tree.getRoot());
					
	}
	
	// test for deleting a node where min value search is required
	@Test
	void givenRightBranchWithRightChild_whenRootDeleted_rightNodeBecomesRoot() {
		// test deleting a node from an empty tree
		AppointmentBinarySearchTree tree = new AppointmentBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		
		// insert another appointment so that the value will be the root
		tree.insert(testAppointment2);
				
		// Verify the test was added to the root
		assertEquals(testAppointment2, tree.search("2"));

		// insert appointment2 into binary searchTree, left of root
		tree.insert(testAppointment5);
				
		// Verify the test was added to the tree
		assertEquals(testAppointment5, tree.search("5"));
		
		// insert appointment2 into binary searchTree, left of root
		tree.insert(testAppointment1);
				
		// Verify the test was added to the tree
		assertEquals(testAppointment1, tree.search("1"));
		
		// insert appointment2 into binary searchTree, left of root
		tree.insert(testAppointment3);
				
		// Verify the test was added to the tree
		assertEquals(testAppointment3, tree.search("3"));		
		
		// delete root
		tree.delete("2");
		
		// Verify the test was added to the root
		assertEquals(null, tree.search("2"));
		
		// Verfiy root not null
		assertNotNull(tree.getRoot());
		
			
	}
	
	// Tear down
	@AfterEach
	void tearDown() throws Exception {
		testAppointment1 = null;
		testAppointment2 = null;
		testAppointment3 = null;
		testAppointment4 = null;
		testAppointment5 = null;
		
	}

}
