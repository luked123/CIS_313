public class RBT<E extends Comparable<E>> {
    private Node<E> root;

    public RBT(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an RBT tree
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
    }

    public void delete(E data){
    	// Preform a regular delete
    	// Check to make sure the tree remains an RBT tree
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
    }


    public void rightRotate(Node<E> y){
    	
    	/*
    	If x is the root of the tree to rotate with left child subtree T1 and right child y, 
    	where T2 and T3 are the left and right children of y:
			x becomes left child of y and T3 as its right child of y
			T1 becomes left child of x and T2 becomes right child of x
		*/
    }

    public void leftRotate(Node<E> x){
    
    	/*
    	If y is the root of the tree to rotate with right child subtree T3 and left child x, 
    	where T1 and T2 are the left and right children of x:
			y becomes right child of x and T1 as its left child of x
			T2 becomes left child of y and T3 becomes right child of y
		*/
		
    }
    
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
}
