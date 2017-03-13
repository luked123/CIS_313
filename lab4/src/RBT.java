public class RBT<E extends Comparable<E>> {
    private Node<E> root; 
    
    public RBT(){
    // Constructor
   
        root = null;
    }

    public Node<E> getRoot(){
    // Returns root of tree
    	
        return root;
    }

    public void insert(E data){
    // Inserts node in correct place of RBT tree
    // Calls fixInsert when finished 
    	
		Node<E> node = new Node<E>(data);
		Node<E> nilL  = new Node<E>(null);
		Node<E> nilR  = new Node<E>(null);
		
		nilL.setColor('b');
		nilR.setColor('b');
		nilL.setParent(node);
		nilR.setParent(node);
		
		node.setLeftChild(nilL);
		node.setRightChild(nilR);
		node.setColor('r');
		
		if(this.root == null){
		// If tree is empty make node root

			this.root = node; 
		}
		else{
			Node<E> cur = this.root;
			while(cur.getData() != null){
				if(node.getData().compareTo(cur.getData()) < 0 ){
					cur = cur.getLeftChild(); 
				}
				else{
					cur = cur.getRightChild(); 
				}
			}
			
			node.setParent(cur.getParent());
			if(isLeft(cur)){
				cur.getParent().setLeftChild(node);
			}
			else{
				cur.getParent().setRightChild(node);
			}
		}
		fixInsert(node); 
    }

    public Node<E> search(E data){
    // Return the node that corresponds with the given data
    // finds node matching data in RBT
    	
    	Node<E> node = new Node<E>(data); 
    	Node<E> cur = this.root;
    	
    	while(cur.getData() != null && node.getData().compareTo(cur.getData())!= 0){
    		if(node.getData().compareTo(cur.getData()) < 0){
    			cur = cur.getLeftChild(); 
    		}
    		else{
    			cur = cur.getRightChild(); 
    		}
    	}
    	
    	if(cur.getData() == null){
    	// No node was found
    		return null; 
    	}
    	
    	return cur; 
    }

    
    public void delete(E data){
    // Performs a regular delete and depending on case calls fixDelete
    	Node<E> node = search(data);
    	
    	Node<E> nil; 
    	Node<E> nilL = new Node<E>(null); 
    	Node<E> nilR = new Node<E>(null); 
    	nilL.setColor('b');
    	nilR.setColor('b');
    	
    	if(node == null){
    	// No node was found
    		return; 
    	}
    	
    
    	if(node.getLeftChild().getData() == null && node.getRightChild().getData() == null){
    	// Node has no children 	
    		if(node.getParent() == null){
    			this.root = null;
    			return; 
    		}
    		else if(isLeft(node)){
    			nilL.setParent(node.getParent());
    			node.getParent().setLeftChild(nilL);
    			nil = nilL; 
    		}
    		else{
    			nilR.setParent(node.getParent());
    			node.getParent().setRightChild(nilR);
    			nil = nilR; 
    		}
    		
    		if(node.getColor() == 'b'){           // only perform if node was black
    			fixDelete(nil);
    		}
   
    	}
    	
    	else if(node.getLeftChild().getData() != null && node.getRightChild().getData() == null){
    	// Node has left child
    		if(node.getParent() == null){
    			this.root = node.getLeftChild(); 
    		}
    		else if(isLeft(node)){
    			node.getParent().setLeftChild(node.getLeftChild());
    			node.getLeftChild().setParent(node.getParent());
    		}
    		else{
    			node.getParent().setRightChild(node.getLeftChild());
    			node.getLeftChild().setParent(node.getParent()); 
    		}
    		if(node.getColor() == 'b' ){                // only perform if node was black
    			fixDelete(node.getLeftChild());
    		}
    	}
    	
    	else if(node.getLeftChild().getData() == null && node.getRightChild().getData() != null){
    	// Node has right child
    		if(node.getParent() == null){
    			this.root = node.getRightChild();  
    		}
    		else if(isLeft(node)){
    			node.getParent().setLeftChild(node.getRightChild());
    			node.getRightChild().setParent(node.getParent());
    		}
    		else{
    			node.getParent().setRightChild(node.getRightChild());
    			node.getRightChild().setParent(node.getParent());
    		}
    		if(node.getColor() == 'b'){               // only perform if node was black
    			fixDelete(node.getRightChild()); 
    		}
    	}
    	
    	else{
    	// Node has two children 	
    		Node<E> suc = findSuc(node); 
    		delete(suc.getData()); 
    		suc.setColor(node.getColor());
    		
    		if(node.getParent() == null){
    			suc.setLeftChild(node.getLeftChild());
    			suc.setRightChild(node.getRightChild());
    			this.root = suc; 
    		}
    		else if (isLeft(node)){
    			suc.setLeftChild(node.getLeftChild());
    			suc.setRightChild(node.getRightChild());
    			node.getParent().setLeftChild(suc);
    		}
    		else{
    			suc.setLeftChild(node.getLeftChild());
    			suc.setRightChild(node.getRightChild());
    			node.getParent().setRightChild(suc);
    		}
    	}
    }
    	
    
    public void traverse(String order, Node<E> top) {
    // Traverses tree in indicated order 
    	
    	if(top.getData() != null){
    		if(order == "preorder"){
    			System.out.printf("%d ", top.getData());
    			traverse("preorder", top.getLeftChild());
    			traverse("preorder", top.getRightChild()); 
    		}
    		else if(order == "inorder"){
    			traverse("inorder", top.getLeftChild());
    			System.out.printf("%d ", top.getData());
    			traverse("inorder", top.getRightChild());
    		}
    		else if(order =="postorder"){
    			traverse("postorder", top.getLeftChild());
				traverse("postorder", top.getRightChild());
				System.out.printf("%d ", top.getData());
    		}
    	}
    }


    public void leftRotate(Node<E> y){
    // Left rotate on node y
    	
    	Node<E> x = y.getParent(); 
    	Node<E> t1 = x.getLeftChild();
    	Node<E> t2 = y.getLeftChild();
    	Node<E> t3 = y.getRightChild();
    	
    	Node<E> rotx = new Node<E>(x.getData()); 
    	Node<E> roty = new Node<E>(y.getData()); 
    	
    	rotx.setParent(roty);
    	rotx.setLeftChild(t1);
    	rotx.setRightChild(t2);
    	t1.setParent(rotx);
    	t2.setParent(rotx);
    	
    	roty.setParent(x.getParent());
    	roty.setLeftChild(rotx);
    	roty.setRightChild(t3);
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
    	
    }

    public void rightRotate(Node<E> x){
    // Right rotates on node x
    	
    	Node<E> y = x.getParent();
    	Node<E> t1 = x.getLeftChild(); 
    	Node<E> t2 = x.getRightChild(); 
    	Node<E> t3 = y.getRightChild(); 
    	
    	Node<E> rotx = new Node<E>(x.getData()); 
    	Node<E> roty = new Node<E>(y.getData()); 
    	
    	roty.setParent(rotx);
    	roty.setColor(y.getColor());
    	roty.setLeftChild(t2);
    	roty.setRightChild(t3);
    	t2.setParent(roty);
    	t3.setParent(roty);
    	
    	rotx.setParent(y.getParent());
    	rotx.setColor(x.getColor());
    	rotx.setLeftChild(t1);
    	rotx.setRightChild(roty);
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
    }
    
    
    private boolean isRight(Node<E> n){
    // Tests if node is right child
    	
    	if((n.getParent() != null) && (n == n.getParent().getRightChild())){
    		return true; 
    	}
    	return false; 
    }
    	
    private boolean isLeft(Node<E> n){
    // Tests if node is left child
    	
    	if((n.getParent() != null) && (n == n.getParent().getLeftChild())){
    		return true; 
    	}
    	return false; 
    }
    
    
    private Node<E> findSuc(Node<E> node){
    // Return the successor of the node
    	
    	node = node.getRightChild(); 
    	while(node.getLeftChild().getData() != null){
    		node = node.getLeftChild(); 
    	}
    	return node; 
    }
    
    
    private Node<E> getUncle(Node<E> n){
    // Returns the Uncle of the node
    	
    	if(isRight(n.getParent())){
    		return n.getParent().getParent().getLeftChild();
    	}
    	else if (isLeft(n.getParent())){
    		return n.getParent().getParent().getRightChild(); 
    	}
    	else{
    		return null; 
    	}
    }
    
    
    private Node<E> getGrandParent(Node<E> node){
    // Returns the grandparent of the node
    	
    	return node.getParent().getParent(); 
    }
    
    
    private Node<E> getSibling(Node<E> n){
    // Returns the sibling of the node
    	
    	if(isRight(n)){
    		return n.getParent().getLeftChild();
    	}
    	else if(isLeft(n)){
    		return n.getParent().getRightChild(); 
    	}
    	else{
    		return null; 
    	}
    }
    
    
    private void fixInsert(Node<E> node){
    // Fixes the red black tree at node during insertion
    // Refers to cases found in text book
    	
    	if(node == this.root){
    		node.setColor('b');
    	}
    	else{
    		if(node.getParent().getColor() == 'b'){
    		// Do nothing if parent is black	
    			return; 
    		}
    		else{
    		// Uncle is red case 
    			Node<E> uncle = getUncle(node);
    			Node<E> grandParent = getGrandParent(node); 
    			if(uncle.getColor() == 'r'){
    				node.getParent().setColor('b');
    				uncle.setColor('b');
    				grandParent.setColor('r');
    				fixInsert(grandParent); 
    			}
    			else{
    			// Uncle is black case
    				if(isLeft(node.getParent())){
    				// Parent is left child 
    					if(isRight(node)){
    						leftRotate(node);
    						rightRotate(node);
    						node.setColor('b');
    						node.getRightChild().setColor('r');
    					}
    					else{
    						rightRotate(node.getParent());
    						node.getParent().setColor('b');
    						node.getParent().getRightChild().setColor('r');
    					}
    				}
    				else{
    				// Parent is right child
    					if(isLeft(node)){
    						rightRotate(node);
    						leftRotate(node); 
    						node.setColor('b');
    						node.getLeftChild().setColor('r');
    					}
    					else{
    						leftRotate(node.getParent());
    						node.getParent().setColor('b');
    						node.getParent().getLeftChild().setColor('r');
    					}
    				}
    			}
    		}
    	}
    }
    
    
	private void fixDelete(Node<E> node){
		// Fixes the Red black tree at node during deletion
		// All cases differ from the book and refer to this video "https://www.youtube.com/watch?v=CTvfzU_uNKE"
		// Implemented cases by myself

		if(node.getColor() == 'r'){
			if((node.getLeftChild().getData() == null) && (node.getRightChild().getData() == null)){
				node.setColor('b');
			}
		}
		else{
			node.setColor('B');    // refers to double black node
			Node<E> sibling = getSibling(node); 
			if(node == this.root){   
			// Case 1    
				node.setColor('b');
			}
			if(isLeft(node)){
			// Node is the left child
				if((node.getParent().getColor() == 'b') && (sibling.getColor() == 'r') && (sibling.getLeftChild().getColor() == 'b') && (sibling.getRightChild().getColor() == 'b')){
				// Case 2	
					node.getParent().setColor('r');
					sibling.setColor('b');
					leftRotate(sibling); 
					fixDelete(node); 
				}
				else if((node.getParent().getColor() == 'b') && (sibling.getColor() == 'b') && (sibling.getLeftChild().getColor() == 'b') && (sibling.getRightChild().getColor() == 'b')){
				// Case 3	
					sibling.setColor('r');
					node.setColor('b');
					fixDelete(node.getParent()); 
				}
				else if((node.getParent().getColor() == 'r') && (sibling.getColor() == 'b') && (sibling.getLeftChild().getColor() == 'b') && (sibling.getRightChild().getColor() == 'b')){
				// Case 4	
					node.getParent().setColor('b');
					sibling.setColor('r');
					node.setColor('b');
				}
				else if((sibling.getColor() == 'b') && (sibling.getRightChild().getColor() == 'b') && (sibling.getLeftChild().getColor() == 'r')){
				// Case 5	
					sibling.setColor('r');
					sibling.getLeftChild().setColor('b');
					rightRotate(sibling.getLeftChild());
					fixDelete(node); 
				}
				else if((sibling.getColor() =='b') && (sibling.getRightChild().getColor() == 'r')){
				// Case 6	
					sibling.setColor(node.getParent().getColor());
					node.getParent().setColor('b');
					sibling.getRightChild().setColor('b');
					node.setColor('b');
					leftRotate(sibling); 
				}
				else{
				// Just in case for some reason it does not go into any cases, was used for testing	
				}
			}
			else{
			// Node is right child	
				if((node.getParent().getColor() == 'b') && (sibling.getColor() == 'r') && (sibling.getLeftChild().getColor() == 'b') && (sibling.getRightChild().getColor() == 'b')){
				// Case 2
					node.getParent().setColor('r');
					sibling.setColor('b');
					rightRotate(sibling); 
					fixDelete(node); 
				}
				else if((node.getParent().getColor() == 'b') && (sibling.getColor() == 'b') && (sibling.getLeftChild().getColor() == 'b') && (sibling.getRightChild().getColor() == 'b')){
				// Case 3	
					sibling.setColor('r');
					node.setColor('b');
					fixDelete(node.getParent()); 
				}
				else if((node.getParent().getColor() == 'r') && (sibling.getColor() == 'b') && (sibling.getLeftChild().getColor() == 'b') && (sibling.getRightChild().getColor() == 'b')){
				// Case 4
					node.getParent().setColor('b');
					sibling.setColor('r');
					node.setColor('b');
				}
				else if((sibling.getColor() == 'b') && (sibling.getRightChild().getColor() == 'r') && (sibling.getLeftChild().getColor() == 'b')){
				// Case 5
					sibling.setColor('r');
					sibling.getRightChild().setColor('b');
					leftRotate(sibling.getRightChild());
					fixDelete(node); 
				}
				else if((sibling.getColor() == 'b') && (sibling.getLeftChild().getColor() == 'r')){
				// Case 6
					sibling.setColor(node.getParent().getColor());
					node.getParent().setColor('b');
					sibling.getLeftChild().setColor('b');
					node.setColor('b');
					rightRotate(sibling); 
				}
				else{	
				// Just in case for some reason it does not go into any cases was used for testing
				}
			}
		}	
	}
}


