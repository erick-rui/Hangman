package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree {
	Node root;
	
	int height(Node N) { 
        if (N == null) 
            return 0; 
  
        return N.height; 
    }
	
	int max(int a, int b) { 
        return (a > b) ? a : b; 
    }
	// This rotation and insertion code has been contributed by Mayank Jaiswal
	// A utility function to right rotate subtree rooted with y 
    // See the diagram given above.
	Node rightRotate(Node y) { 
        Node x = y.left; 
        Node T2 = x.right; 
  
        // Perform rotation 
        x.right = y; 
        y.left = T2; 
  
        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        // Return new root 
        return x; 
    }
	
	// A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
	Node leftRotate(Node x) { 
        Node y = x.right; 
        Node T2 = y.left; 
  
        // Perform rotation 
        y.left = x; 
        x.right = T2; 
  
        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        // Return new root 
        return y; 
    } 
	
	// Get Balance factor of node N 
    int getBalance(Node N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    }
    
    Node insert(Node node, String key) { 
    	  
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new Node(key)); 
  
        if (key.compareTo(node.data) < 0)
            node.left = insert(node.left, key); 
        else if (key.compareTo(node.data) > 0) 
            node.right = insert(node.right, key); 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key.compareTo(node.left.data) < 0) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && key.compareTo(node.right.data) > 0) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && key.compareTo(node.left.data) > 0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && key.compareTo(node.right.data) < 0) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    } 

	
	public void addNode(String value) {
		root = insert(root, value);
	}
	
    /* A function that constructs Balanced Binary Search Tree  
    from a sorted array */
   Node sortedArrayToBST(ArrayList<String> arr, int start, int end) { 
 
       /* Base Case */
       if (start > end) { 
           return null; 
       } 
       
      
 
       /* Get the middle element and make it root */
       int mid = (start + end) / 2; 
       Node node = new Node(arr.get(mid)); 
 
       /* Recursively construct the left subtree and make it 
        left child of root */
       node.left = sortedArrayToBST(arr, start, mid - 1); 
 
       /* Recursively construct the right subtree and make it 
        right child of root */
       node.right = sortedArrayToBST(arr, mid + 1, end); 
         

       return node; 
       
       
   }
   
   public void buildTree(ArrayList<String> arr) {
	   root = sortedArrayToBST(arr, 0, arr.size()-1);
   }
	
	public boolean contains(Node current, String target) {
		if(current == null)
			return false;
		if((current.data).equals(target))
			return true;
		if((current.data).compareTo(target) > 0) {
			return contains(current.left, target);
		}else {
			return contains(current.right, target);
		}
	}
	
	private String findCloseValue(Node current, String target) {
		if((current.data).compareTo(target) == 0)
			return current.data;
		if((current.data).compareTo(target) > 0) {
			if(current.left == null)
				return current.data;
			else
				return findCloseValue(current.left, target);
		} else {
			if(current.right == null)
				return current.data;
			else
				return findCloseValue(current.right, target);
		}
	}
	
	public String findClosestValue(String target) {
		return findCloseValue(root, target);
	}
	
	public boolean containsNode(String target) {
		return contains(root, target);
	}
	
	private String findSmallestValue(Node root) {
		if(root == null)
			throw new ItemNotFoundException("The tree is empty");
		if(root.left == null)
			return root.data;
		else
			return findSmallestValue(root.left);
	}
	
	private String findLargestValue(Node root) {
		if(root == null)
			throw new ItemNotFoundException("The tree is empty");
		if(root.right == null)
			return root.data;
		else
			return findLargestValue(root.right);
	}
	
	public String min() {
		return findSmallestValue(root);
	}
	
	public String max() {
		return findLargestValue(root);

	}
	
	public int size() {
		if (root == null)
			return 0;
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(root);
		int size = 0;
		
		while(!nodes.isEmpty()) {
			Node node = nodes.remove();
			size++;
			if(node.left != null)
				nodes.add(node.left);
			if(node.right != null)
				nodes.add(node.right);
		}
		return size;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	
	//Pre-order traversal visits first the root node, then the left subtree, and finally the right subtree
	public void traversePreOrder(Node node) {
		if(node != null) {
			System.out.print(" " + node.data);
			traversePreOrder(node.left);
			traversePreOrder(node.right);
		}
	}
	
	
	// In-order traversal: left subtree --> root --> right subtree
	public void traverseInOrder(Node node) {
		if(node != null) {
			traverseInOrder(node.left);
			System.out.print(" " + node.data);
			traverseInOrder(node.right);
		}
	}
	
	// post-order traversal: left subtree --> right subtree --> root
	public void traversePostOrder(Node node) {
		if(node != null) {
			traverseInOrder(node.left);
			traverseInOrder(node.right);
			System.out.print(" " + node.data);
		}			
	}
	
	public void DFS() {
		System.out.println("InOrder Traversal: ");
		traverseInOrder(root);
		System.out.println("\nPreOrder Traversal: ");
		traversePreOrder(root);
		System.out.println("\nPostOrder Traversal: ");
		traversePostOrder(root);
	}
	
	// Breadth-first search: visit all the nodes of a level before going to the next level
	// for the implementation we use a Queue to hold the nodes from each level in order
	// we'll extract each node from the list, print its value, then add the children to the queue
	public void BFS() {
		if (root == null) {
			return;
		}
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(root);
		
		while(!nodes.isEmpty()) {
			Node node = nodes.remove();
			System.out.print(" " + node.data);
			if(node.left != null)
				nodes.add(node.left);
			if(node.right != null)
				nodes.add(node.right);
		}
	}

}
