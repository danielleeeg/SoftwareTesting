package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.TaskBinarySearchTree;
import main.Task;
/**
 * @author Danielle Eeg
 *
 */
class TaskBinarySearchTreeTest {
	Task testTask1, testTask2, testTask3, testTask4, testTask5;

	@BeforeEach
	void setUp() throws Exception {
		testTask1 = new Task("1", "left", "left of base");
		testTask2 = new Task("2", "root", "base of tree");
		testTask3 = new Task("3", "right", "right of base");
		testTask4 = new Task("1", "duplicate", "Equal to another task");
		testTask5 = new Task("5", "right 2", "Used for testing min value search function");
		
	}
	// test create a binary search tree
	@Test
	void givenTaskBinarySearchTreeClass_whenContructorCalled_thenTaskBinarySearchTreeWithNullRootExists() {
		// create a binary search tree
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
		
		// verify that the root is null, and there are therefore, no other items in the tree
		assertEquals(null, tree.getRoot());
	}

	// test to ensure tasks can be added, but only when they have unique IDs
	@Test
	void givenTaskBinarySearchTree_whenNodesAdded_thenOnlyUniqueIdTasksAdded() {
		// create a binary search tree
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
		
		// insert task2 into binary searchTree
		tree.insert(testTask2);
		
		// Verify the test was added to the root
		assertEquals(testTask2, tree.search("2"));
		
		// insert another task so that the value will be inserted to the left of the root
		tree.insert(testTask1);
		
		// Verify the test was added to the root
		assertEquals(testTask1, tree.search("1"));
		
		// insert another task so that the value will be inserted to the right of the root
		tree.insert(testTask3);
		
		// Verify the test was added to the root
		assertEquals(testTask3, tree.search("3"));
		
		// insert another task so the value will be rejected with an illegal argument exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			tree.insert(testTask4);;
		});
	}
	
	// test for deleting a node from an empty tree
	@Test
	void givenEmptyTree_whenItemDeleted_thenThrowError() {
		// test deleting a node from an empty tree
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
		
		// delete random ID from tree
		tree.delete("24");
		
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
	}
	
	// test for deleting a node from the left branch of a tree
	@Test
	void givenTreeWithLeftBranch_whenLeftNodeDeleted_thenLeftNodeNull() {
		// test deleting a node from an empty tree
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert task2 into binary searchTree
		tree.insert(testTask2);
		
		// Verify the task was added to the root
		assertEquals(testTask2, tree.search("2"));
		
		// insert another task so that the value will be inserted to the left of the root
		tree.insert(testTask1);
		
		// Verify the test was added to the root
		assertEquals(testTask1, tree.search("1"));
		
		
		// Delete node to left of root
		tree.delete("1");
		
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("1"));
			
	}
	
	// test for deleting a node from the right branch of a tree
	@Test
	void givenTreeWithRightBranch_whenNodeDeleted_thenRightNodeNull() {
		// test deleting a node from an empty tree
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert task2 into binary searchTree
		tree.insert(testTask2);
		
		// Verify the task was added to the root
		assertEquals(testTask2, tree.search("2"));
		
		// insert another task so that the value will be inserted to the right of the root
		tree.insert(testTask3);
		
		// Verify the test was added to the root
		assertEquals(testTask3, tree.search("3"));
		
		// Delete node to right of the root
		tree.delete("3");
				
		// Make sure the node was truly deleted
		assertEquals(null, tree.search("3"));		
			
	}
	
	// test for deleting a node with no children to the right
	@Test
	void givenNodeWithOnlyLeftChildren_whenParentDeleted_thenLeftChildrenBecomeRoot() {
		// test deleting a node from an empty tree
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert task2 into binary searchTree
		tree.insert(testTask2);
		
		// Verify the test was added to the root
		assertEquals(testTask2, tree.search("2"));
		
		// insert another task so that the value will be inserted to the left of the root
		tree.insert(testTask1);
		
		// Verify the task was added to the root
		assertEquals(testTask1, tree.search("1"));
		
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
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		// insert task2 into binary searchTree
		tree.insert(testTask2);
		
		// Verify the task was added to the root
		assertEquals(testTask2, tree.search("2"));
		
		// insert another task so that the value will be inserted to the left of the root
		tree.insert(testTask1);
		
		// Verify the task was added
		assertEquals(testTask1, tree.search("1"));
		
		// insert another task so that the value will be inserted to the right of the root
		tree.insert(testTask3);
		
		// Verify the task was added 
		assertEquals(testTask3, tree.search("3"));
		
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
		TaskBinarySearchTree tree = new TaskBinarySearchTree();
			
		// Assert root is null
		assertEquals(null, tree.getRoot());
		
		
		// insert another task so that the value will be the root
		tree.insert(testTask2);
				
		// Verify the test was added to the root
		assertEquals(testTask2, tree.search("2"));

		// insert task2 into binary searchTree, left of root
		tree.insert(testTask5);
				
		// Verify the test was added to the tree
		assertEquals(testTask5, tree.search("5"));
		
		// insert task2 into binary searchTree, left of root
		tree.insert(testTask1);
				
		// Verify the test was added to the tree
		assertEquals(testTask1, tree.search("1"));
		
		// insert task2 into binary searchTree, left of root
		tree.insert(testTask3);
				
		// Verify the test was added to the tree
		assertEquals(testTask3, tree.search("3"));		
		
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
		testTask1 = null;
		testTask2 = null;
		testTask3 = null;
		testTask4 = null;
		testTask5 = null;
		
	}

}
