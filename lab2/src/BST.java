
public class BST<E extends Comparable<E>> {
	private Node<E> root;

	
	public BST(){
	// constructor for BST
		root = null;
	}
	
	
	public Node<E> getRoot(){
	// returns root for BST
		return root;
	}


	public void insert(E data){
    // inserts node with data into the BST

		Node<E> node = new Node<E>(data); 
		
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
				else
				{
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
	
	
    public Node<E> find(E data){
    // finds node matching data in BST	
    	Node<E> node = new Node<E>(data); 
    	Node<E> cur = this.root;
    	
    	while(cur != null && node.getData().compareTo(cur.getData())!= 0){
    		if(node.getData().compareTo(cur.getData()) < 0){
    			cur = cur.getLeftChild(); 
    		}
    		else
    		{
    			cur = cur.getRightChild(); 
    		}
    	}
    	
    	// no finds
    	if(cur == null){
    		return null;
    	}
    	return cur; 
    }

    public void delete(E data){
    // finds node that matches data and deletes it
    // returns null if node is not in BST
    	
    	Node<E> node = find(data);
    	
    	// node isn't in tree
    	if(node == null){
    		return; 
    	}
    	
        // node has no children
    	if(node.getLeftChild() == null && node.getRightChild() == null){
    		if(node.getParent() == null){                                     // root case
    			this.root = null; 
    		}
    		else if(isLeft(node)){                                            // node is left child 
    			node.getParent().setLeftChild(null);
    		}
    		else{                                                             // node is right child
    			node.getParent().setRightChild(null);                        
    		}
    	}
    	
        // node has left child
    	else if(node.getLeftChild() != null && node.getRightChild() == null){
    		if(node.getParent() == null){                                      // root case
    			this.root = node.getLeftChild(); 
    		}
    		else if(isLeft(node)){
    			node.getParent().setLeftChild(node.getLeftChild());            // node is left child  
    			node.getLeftChild().setParent(node.getParent());
    		}
    		else{
    			node.getParent().setRightChild(node.getLeftChild());           // node is right child
    			node.getLeftChild().setParent(node.getParent());
    		}
    	}
    	
    	// node has right child 
    	else if(node.getLeftChild() == null && node.getRightChild() != null){
    		
    		if(node.getParent() == null){                                      // root case
    			this.root = node.getRightChild();
    		}
    		else if(isLeft(node)){                                             // node is left child
    			node.getParent().setLeftChild(node.getRightChild());
    			node.getRightChild().setParent(node.getParent());
    		}
    		else{                                                              // node is right child  
    			node.getParent().setRightChild(node.getRightChild());            
    			node.getRightChild().setParent(node.getParent());
    		}
    		
    	}
    	
    	//Node has two children
    	else{
    		Node<E> suc = findSuc(node);
    		delete(suc.getData()); // suc node will have either no children or a right child
    		
    		if(node.getParent() == null){                                       // root case
    			suc.setLeftChild(node.getLeftChild());
    			suc.setRightChild(node.getRightChild());
    			this.root = suc; 
    			
    		}
    		else if(isLeft(node)){                                              // node is left child
    			suc.setLeftChild(node.getLeftChild());
    			suc.setRightChild(node.getRightChild());
    			node.getParent().setLeftChild(suc);
    		}   
    		else{                                                               // node is right child
    			suc.setLeftChild(node.getLeftChild());
    			suc.setRightChild(node.getRightChild());
    			node.getParent().setRightChild(suc);
    		}
    	}
    }

    
    public void traverse(String order, Node<E> top) {
    //traverses the BST depending on what order is inputed
        if (top != null){
            switch (order) {
                case "preorder":
                    System.out.printf("%s ", top.getData().toString());
                    traverse("preorder", top.getLeftChild());
                    traverse("preorder", top.getRightChild()); 
                    break;
                case "inorder":
                	traverse("inorder", top.getLeftChild());
                	System.out.printf("%s ", top.getData().toString());
                	traverse("inorder", top.getRightChild()); 
                    break;
                case "postorder":
                	traverse("postorder", top.getLeftChild());
                	traverse("postorder", top.getRightChild());
                	System.out.printf("%s ", top.getData().toString()); 
                    break;
                default:
                	System.out.println("Incorrect Format");
                	break; 
            }
        }
    }
    
    
    private boolean isLeft(Node<E> node){
    // returns true if node is the left child, false if node is the right child
    	if(node.getParent().getLeftChild() == node){
    		return true; 
    	}
    	return false; 
    }
    
    
    // kept this function in in case I had to implement in the future
    /*private boolean isRight(Node<E> node){
    	if(node.getParent().getRightChild() == node){
    		return true; 
    	}
    	return false; 
    }
    */
    
    
    private Node<E> findSuc(Node<E> node){
    // finds the nodes successor
    	Node<E> suc = node.getRightChild();
    	while(suc.getLeftChild() != null){
    		suc = suc.getLeftChild();
    	}
    	return suc; 
    }
}
