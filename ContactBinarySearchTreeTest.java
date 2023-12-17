package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.ContactBinarySearchTree;
import main.Contact;
/**
 * @author Danielle Eeg
 *
 */
class ContactBinarySearchTreeTest {
	Contact testContact1, testContact2, testContact3, testContact4, testContact5;

	@BeforeEach
	void setUp() throws Exception {
		testContact1 = new Contact("1", "Anne", "Jones", "1234567890", "left of base");
		testContact2 = new Contact("2", "Betty","Johns", "0987654321",  "base of tree");
		testContact3 = new Contact("3", "Candy", "Jorge", "0123456789", "right of base");
		testContact4 = new Contact("1", "Dani", "Johnson", "9876543210", "Equal to another contact");
		testContact5 = new Contact("5", "Elaine", "Joseph","0246813579", "Min value search function");
		
	}
	// test create a binary search tree
	@Test
	void givenContactBinarySearchTreeClass_whenContructorCalled_thenContactBinarySearchTreeWithNullRootExists() {
		// create a binary search tree
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
		
		// verify that the root is null, and there are therefore, no other items in the tree
		assertEquals(null, tree.getRoot());
	}

	// test to ensure contacts can be added, but only when they have unique IDs
	@Test
	void givenContactBinarySearchTree_whenNodesAdded_thenOnlyUniqueIdContactsAdded() {
		// create a binary search tree
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
		
		// insert contact2 into binary searchTree
		tree.insert(testContact2);
		
		// Verify the test was added to the root
		assertEquals(testContact2, tree.search("2"));
		
		// insert another contact so that the value will be inserted to the left of the root
		tree.insert(testContact1);
		
		// Verify the test was added to the root
		assertEquals(testContact1, tree.search("1"));
		
		// insert another contact so that the value will be inserted to the right of the root
		tree.insert(testContact3);
		
		// Verify the test was added to the root
		assertEquals(testContact3, tree.search("3"));
		
		// insert another contact so the value will be rejected with an illegal argument exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			tree.insert(testContact4);;
		});
	}
	
	// test for deleting a node from an empty tree
	@Test
	void givenEmptyTree_whenItemDeleted_thenThrowError() {
		// test deleting a node from an empty tree
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
		
		// delete random ID from tree
		tree.delete("24");
		
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
	}
	
	// test for deleting a node from the left branch of a tree
	@Test
	void givenTreeWithLeftBranch_whenLeftNodeDeleted_thenLeftNodeNull() {
		// test deleting a node from an empty tree
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert contact2 into binary searchTree
		tree.insert(testContact2);
		
		// Verify the contact was added to the root
		assertEquals(testContact2, tree.search("2"));
		
		// insert another contact so that the value will be inserted to the left of the root
		tree.insert(testContact1);
		
		// Verify the test was added to the root
		assertEquals(testContact1, tree.search("1"));
		
		
		// Delete node to left of root
		tree.delete("1");
		
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("1"));
			
	}
	
	// test for deleting a node from the right branch of a tree
	@Test
	void givenTreeWithRightBranch_whenNodeDeleted_thenRightNodeNull() {
		// test deleting a node from an empty tree
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert contact2 into binary searchTree
		tree.insert(testContact2);
		
		// Verify the contact was added to the root
		assertEquals(testContact2, tree.search("2"));
		
		// insert another contact so that the value will be inserted to the right of the root
		tree.insert(testContact3);
		
		// Verify the test was added to the root
		assertEquals(testContact3, tree.search("3"));
		
		// Delete node to right of the root
		tree.delete("3");
				
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("3"));		
			
	}
	
	// test for deleting a node with no children to the right
	@Test
	void givenNodeWithOnlyLeftChildren_whenParentDeleted_thenLeftChildrenBecomeRoot() {
		// test deleting a node from an empty tree
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert contact2 into binary searchTree
		tree.insert(testContact2);
		
		// Verify the test was added to the root
		assertEquals(testContact2, tree.search("2"));
		
		// insert another contact so that the value will be inserted to the left of the root
		tree.insert(testContact1);
		
		// Verify the contact was added to the root
		assertEquals(testContact1, tree.search("1"));
		
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
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert contact2 into binary searchTree
		tree.insert(testContact2);
		
		// Verify the contact was added to the root
		assertEquals(testContact2, tree.search("2"));
		
		// insert another contact so that the value will be inserted to the left of the root
		tree.insert(testContact1);
		
		// Verify the contact was added
		assertEquals(testContact1, tree.search("1"));
		
		// insert another contact so that the value will be inserted to the right of the root
		tree.insert(testContact3);
		
		// Verify the contact was added 
		assertEquals(testContact3, tree.search("3"));
		
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
		ContactBinarySearchTree tree = new ContactBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		
		// insert another contact so that the value will be the root
		tree.insert(testContact2);
				
		// Verify the test was added to the root
		assertEquals(testContact2, tree.search("2"));

		// insert contact2 into binary searchTree, left of root
		tree.insert(testContact5);
				
		// Verify the test was added to the tree
		assertEquals(testContact5, tree.search("5"));
		
		// insert contact2 into binary searchTree, left of root
		tree.insert(testContact1);
				
		// Verify the test was added to the tree
		assertEquals(testContact1, tree.search("1"));
		
		// insert contact2 into binary searchTree, left of root
		tree.insert(testContact3);
				
		// Verify the test was added to the tree
		assertEquals(testContact3, tree.search("3"));		
		
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
		testContact1 = null;
		testContact2 = null;
		testContact3 = null;
		testContact4 = null;
		testContact5 = null;
		
	}

}
