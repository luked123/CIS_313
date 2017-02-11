import java.util.Scanner;

public class TreeCompare {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int lines = Integer.parseInt(scan.nextLine()); 
		BST<Integer> tree1 = new BST<Integer>();
		BST<Integer> tree2 = new BST<Integer>();
		int num = 0; 
		
		for(int i = 0; i < lines; i++){
			String string = scan.nextLine();
			String command[] = string.split(" "); 
			if(command.length > 1){
				num = Integer.parseInt(command[1]); 
			}
			switch(command[0]){
			case "insert":
				tree1.insert(num);
				break;
			case "delete":
				tree1.delete(num);
				break; 
			case "preorder":
				tree1.traverse("preorder", tree1.getRoot());
				System.out.println();
				break;
			case "inorder":
				tree1.traverse("inorder", tree1.getRoot());
				System.out.println();
				break;
			case "postorder":
				tree1.traverse("postorder", tree1.getRoot());
				System.out.println(); 
				break; 
			default:
				System.out.println("Incorrect input");
				break;		
			}
		}
		
		lines = Integer.parseInt(scan.nextLine());
		for(int i = 0; i < lines; i++){
			String string = scan.nextLine();
			String command[] = string.split(" "); 
			if(command.length > 1){
				num = Integer.parseInt(command[1]); 
			}
			switch(command[0]){
			case "insert":
				tree2.insert(num);
				break;
			case "delete":
				tree2.delete(num);
				break; 
			case "preorder":
				tree2.traverse("preorder", tree2.getRoot());
				System.out.println();
				break;
			case "inorder":
				tree2.traverse("inorder", tree2.getRoot());
				System.out.println();
				break;
			case "postorder":
				tree2.traverse("postorder", tree2.getRoot());
				System.out.println(); 
				break; 
			default:
				System.out.println("Incorrect input");
				break;		
			}
		}
		if(compareTree(tree1, tree2)){
			System.out.println("The trees have the same shape.");
		}
		else{
			System.out.println("The trees do not have the same shape.");
		}
		scan.close();
	}


	
	private static boolean compareTree(BST<Integer> tree1, BST<Integer> tree2){
		//compares the shape of two trees
	    Node<Integer> node1 = tree1.getRoot(); 
	    Node<Integer> node2 = tree2.getRoot(); 
	    
	    if(compareNode(node1, node2)){
	    	return true;
	    }
		return false;  	
	}
	
	
	private static boolean compareNode(Node<Integer> node1, Node<Integer> node2){
	//compares nodes to see if they have the same structure by comparing children
		
		//traverses trees preorder fashion recursively
		if(node1 != null && node2 != null){
			// if nodes do not have same structure returns false
			if((Right(node1) != Right(node2)) || (Left(node1) != Left(node2))){   
				return false;  
			}
			if(!(compareNode(node1.getLeftChild(), node2.getLeftChild()))){
				return false; 
			}
			if(!(compareNode(node1.getRightChild(), node2.getRightChild()))){
				return false; 
			}
		}
		else if((node1 == null) && (node2 != null)){
			return  false; 
		}
		else if((node1 != null) && (node2 == null )){
			return false; 
		}
		//passes all tests and returns true
		return true; 
	}
	
	private static boolean Right(Node<Integer> node){
	//checks to see if node has a right child
		if(node.getRightChild() != null){
			return true;
		}
		return false; 
	}
	
	private static boolean Left(Node<Integer> node){
	//checks to see if node has a left child
		if(node.getLeftChild() != null){
			return true;
		}
		return false; 
	}	
	
}

