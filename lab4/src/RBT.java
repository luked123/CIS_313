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
		Node<E> nil  = new Node<E>(null);
		
		nil.setColor('b');
		nil.setParent(node);
		
		node.setLeftChild(nil);
		node.setRightChild(nil);
		node.setColor('r');
		
		// if tree is empty make node into root
		if(this.root == null){
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
        // finds node matching data in BST	
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
    		return null; 
    	}
    	
    	return cur; 
        // Note: No need to worry about duplicate values added to the tree
    }

    public void delete(E data){
    	// Preform a regular delete
    	Node<E> node = search(data); 
    	Node<E> nil = new Node<E>(null); 
    	nil.setColor('b');
    	nil.setParent(node);
    	
    	if(node == null){
    		return; 
    	}
    	
    	// node has no children
    	if(node.getLeftChild().getData() == null && node.getRightChild().getData() == null){
    		if(node.getParent() == null){
    			this.root = null; 
    		}
    		else if(isLeft(node)){
    			node.getParent().setLeftChild(nil);
    		}
    		else{
    			node.getParent().setRightChild(nil);
    		}
    	}
    	
    	//node has left child
    	else if(node.getLeftChild().getData() != null && node.getRightChild().getData() == null){
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
    	}
    	
    	// node has right child
    	else if(node.getLeftChild().getData() == null && node.getRightChild().getData() != null){
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
    	}
    	
    	else{
    		Node<E> suc = findSuc(node); 
    		delete(suc.getData()); 
    		
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
    // Check to make sure the tree remains an RBT tree
    }
    
    
    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
    	if(top.getData() != null){
    		if(order == "preorder"){
    			if(top.getColor() == 'r'){
    				System.out.printf("%d:R ", top.getData());
    			}
    			else{
    				System.out.printf("%d:b ", top.getData()); 
    			}
    			traverse("preorder", top.getLeftChild());
    			traverse("preorder", top.getRightChild()); 
    		}
    	}
    }


    public void leftRotate(Node<E> y){
    	
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
    	
    	/*
    	If x is the root of the tree to rotate with left child subtree T1 and right child y, 
    	where T2 and T3 are the left and right children of y:
			x becomes left child of y and T3 as its right child of y
			T1 becomes left child of x and T2 becomes right child of x
		*/
    }

    public void rightRotate(Node<E> x){
    	
    	Node<E> y = x.getParent();
    	Node<E> t1 = x.getLeftChild(); 
    	Node<E> t2 = x.getRightChild(); 
    	Node<E> t3 = y.getRightChild(); 
    	
    	Node<E> rotx = new Node<E>(x.getData()); 
    	Node<E> roty = new Node<E>(y.getData()); 
    	
    	roty.setParent(rotx);
    	roty.setLeftChild(t2);
    	roty.setRightChild(t3);
    	t2.setParent(roty);
    	t3.setParent(roty);
    	
    	rotx.setParent(y.getParent());
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
    
    private Node<E> findSuc(Node<E> node){
    	Node<E> suc = node.getRightChild(); 
    	while(suc.getLeftChild().getData() != null){
    		suc = suc.getLeftChild(); 
    	}
    	return suc; 
    }
    
    
    private Node<E> getUncle(Node<E> n){
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
    	return node.getParent().getParent(); 
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
    
    private void fixInsert(Node<E> node){
    	//root case
    	if(node == this.root){
    		node.setColor('b');
    	}
    	else{
    		if(node.getParent().getColor() == 'b'){
    			return; 
    		}
    		else{
    			Node<E> uncle = getUncle(node);
    			Node<E> grandParent = getGrandParent(node); 
    			if(uncle.getColor() == 'r'){
    				node.getParent().setColor('b');
    				uncle.setColor('b');
    				grandParent.setColor('r');
    				fixInsert(grandParent); 
    			}
    			else{
    				// parent is left child 
    				if(isLeft(node.getParent())){
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
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
}


