package main;
class ContactNode {
    Contact contact;
    ContactNode left, right;
    
    // create node method
    public ContactNode(Contact contact) {
        this.contact = contact;
        left = right = null;
    }
}
public class ContactBinarySearchTree {
    private ContactNode root;

    // Set node as null
    public ContactBinarySearchTree() {
        root = null;
    }

    // Insert a method used to test the constructor: accessor method for the root
    public ContactNode getRoot(){
    	return this.root;
    }
    
    // Insert a Contact into the BST based on the contactId
    public void insert(Contact contact) {
        root = insertRecursive(root, contact);
    }

    // Helper method for inserting a Contact recursively
    private ContactNode insertRecursive(ContactNode root, Contact contact) {
    	
    	// if no node exuists yet, create a new node containing contact
        if (root == null) {
            return new ContactNode(contact);
        }

        // Compare the contactId for insertion
        int compareValue = contact.getId().compareTo(root.contact.getId());
        
        // if the value is less than the current root, recurse left
        if (compareValue < 0) {
            root.left = insertRecursive(root.left, contact);
        } 
        // if the value is greater than the current root, recuses right
        else if (compareValue > 0) {
            root.right = insertRecursive(root.right, contact);
        } 
        
        // otherwise, the ID already exists in the tree; throw an error
        else {
            // Contact with the same ID already exists, throw error
        	throw new IllegalArgumentException("Contact ID already exists in contact list");
        }

        return root;
    }

    // Delete a Contact from the BST based on the contactId
    public void delete(String contactId) {
        root = deleteRec(root, contactId);
    }

    // Helper method for deleting a Contact recursively
    private ContactNode deleteRec(ContactNode root, String contactId) {
        if (root == null) {
            return root;
        }

        // Compare the contactId for deletion
        int compareValue = contactId.compareTo(root.contact.getId());
        
        // if the value is lass than the root, recurse left
        if (compareValue < 0) {
            root.left = deleteRec(root.left, contactId);
        } 
        
        // if the value is greater than the root, recurse right
        else if (compareValue > 0) {
            root.right = deleteRec(root.right, contactId);
        } 
        
        else {
            // ContactNode with only one child or no child
            if (root.left == null) {
                return root.right;
            } 
            
            else if (root.right == null) {
                return root.left;
            }

            // ContactNode with two children, get the inorder successor (smallest in the right subtree)
            root.contact = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.contact.getId());
        }

        return root;
    }
    // Find the minimum value node in the BST
    private Contact minValue(ContactNode node) {
        Contact minValue = node.contact;
        while (node.left != null) {
            minValue = node.left.contact;
            node = node.left;
        }
        return minValue;
    }
    
    // Find and return a Contact by contactId
    public Contact search(String contactId) {
        return searchRecursive(root, contactId);
    }

    // Helper method for searching for a Contact recursively
    private Contact searchRecursive(ContactNode root, String contactId) {
        if (root == null) {
            return null; // Contact not found
        }

        int compareValue = contactId.compareTo(root.contact.getId());
        
        // if the root Id is equal, return
        if (compareValue == 0) {
            return root.contact; // Contact found
        } 
        
        // if the root ID is less, search left
        else if (compareValue < 0) {
            return searchRecursive(root.left, contactId); 
        } 
        
        // if the root id is greater than, search right
        else {
            return searchRecursive(root.right, contactId); 
        }
    }
    
    
}