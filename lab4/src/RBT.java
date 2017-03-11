public class RBT<E extends Comparable<E>> {
    private Node<E> root;

    public RBT(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
		Node<E> node = new Node<E>(data); 
		node.setColor('r');
		
		// if tree is empty make node into root
		if(this.root == null){
			this.root = node; 
		}
		else{
			Node<E> cur = this.root; // at current node
			// does comparisons to see where node fits in BST
			while(node.getData().compareTo(cur.getData())!= 0){
				if(node.getData().compareTo(cur.getData()) < 0){
					if(cur.getLeftChild() != null){
						cur = cur.getLeftChild(); 
					} 
					else{
						node.setParent(cur);
						cur.setLeftChild(node);
					}
				}
				else{
					if(cur.getRightChild() != null){
						cur = cur.getRightChild();
					}
					else{
						node.setParent(cur);
						cur.setRightChild(node);
					}
				}
			}
			
			// If duplicate return out of function
			if(node.getData().compareTo(cur.getData()) == 0){
				return; 
			}
		}
		
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // finds node matching data in BST	
    	Node<E> node = new Node<E>(data); 
    	Node<E> cur = this.root;
    	
    	while(cur != null && node.getData().compareTo(cur.getData())!= 0){
    		if(node.getData().compareTo(cur.getData()) < 0){
    			cur = cur.getLeftChild(); 
    		}
    		else{
    			cur = cur.getRightChild(); 
    		}
    	}
    	return cur; 
        // Note: No need to worry about duplicate values added to the tree
    }

    public void delete(E data){
    	// Preform a regular delete
    	// Check to make sure the tree remains an RBT tree
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
    	if(top != null){
    		if(order == "preorder"){
    			System.out.printf("%d ", top.getData()); 
    			traverse("preorder", top.getLeftChild());
    			traverse("preorder", top.getRightChild()); 
    		}
    	}
    }


    public void rightRotate(Node<E> y){
    	
    	Node<E> x = y.getParent(); 
    	Node<E> t1 = x.getLeftChild();
    	Node<E> t2 = y.getLeftChild();
    	Node<E> t3 = y.getRightChild();
    	
    	Node<E> rotx = new Node<E>(x.getData()); 
    	Node<E> roty = new Node<E>(y.getData()); 
    	
    	rotx.setParent(roty);
    	rotx.setLeftChild(t1);
    	rotx.setRightChild(t2);
    	rotx.setColor(x.getColor());
    	t1.setParent(rotx);
    	t2.setParent(rotx);
    	
    	roty.setParent(x.getParent());
    	roty.setLeftChild(rotx);
    	roty.setRightChild(t3);
    	roty.setColor(y.getColor());
    	t3.setParent(roty);
    	
    	if(isRight(x)){
    		x.getParent().setRightChild(roty);
    	}
    	else if(isLeft(x)){
    		x.getParent().setLeftChild(roty);
    	}
    	else{ 
    		this.root = roty; 
    	}
    	
    	/*
    	If x is the root of the tree to rotate with left child subtree T1 and right child y, 
    	where T2 and T3 are the left and right children of y:
			x becomes left child of y and T3 as its right child of y
			T1 becomes left child of x and T2 becomes right child of x
		*/
    }

    public void leftRotate(Node<E> x){
    	
    	Node<E> y = x.getParent();
    	Node<E> t1 = x.getLeftChild(); 
    	Node<E> t2 = x.getRightChild(); 
    	Node<E> t3 = y.getRightChild(); 
    	
    	Node<E> rotx = new Node<E>(x.getData()); 
    	Node<E> roty = new Node<E>(y.getData()); 
    	
    	roty.setParent(rotx);
    	roty.setLeftChild(t2);
    	roty.setRightChild(t3);
    	roty.setColor(y.getColor());
    	t2.setParent(roty);
    	t3.setParent(roty);
    	
    	rotx.setParent(y.getParent());
    	rotx.setLeftChild(t1);
    	rotx.setRightChild(roty);
    	rotx.setColor(x.getColor());
    	t1.setParent(rotx);
    	
    	if(isRight(y)){
    		y.getParent().setRightChild(rotx);
    	}
    	else if(isLeft(y)){
    		y.getParent().setLeftChild(rotx);
    	}
    	else{
    		this.root = rotx; 
    	}
    	/*
    	If y is the root of the tree to rotate with right child subtree T3 and left child x, 
    	where T1 and T2 are the left and right children of x:
			y becomes right child of x and T1 as its left child of x
			T2 becomes left child of y and T3 becomes right child of y
		*/
		
    }
    

    private boolean isRight(Node<E> n){
    // Tests if node is right child. 	
    	if((n.getParent() != null) && (n == n.getParent().getRightChild())){
    		return true; 
    	}
    	return false; 
    }
    	
    private boolean isLeft(Node<E> n){
    // Tests if node is left child.
    	if((n.getParent() != null) && (n == n.getParent().getLeftChild())){
    		return true; 
    	}
    	return false; 
    }
    
    private Node<E> getUncle(Node<E> n){
    	return null; 
    }
    
    private Node getSibling(Node<E> n){
    	if(isRight(n)){
    		return n.getParent().getLeftChild();
    	}
    	else if(isLeft(n)){
    		return n.getRightChild(); 
    	}
    	else{
    		return null; 
    	}
    }
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
}


