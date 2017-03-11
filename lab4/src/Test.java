import java.util.Scanner;

public class Test {
	    public static void main(String[] args) {
	    	Scanner scan = new Scanner(System.in); 
	    	int lines = Integer.parseInt(scan.nextLine());
	    	RBT<Integer> tree = new RBT<Integer>();
	    	int num = 0; 
	    	for(int i = 0; i < lines; i++){
	    		String string = scan.nextLine();
	    		String command[] = string.split(" "); 
	    		if(command.length > 1){
	    			num = Integer.parseInt(command[1]); 
	    		}
	    		switch(command[0]){
	    			case "insert":
	    				tree.insert(num);
	    				break;
	    			case "delete":
	    				tree.delete(num);
	    				break; 
	    			case "rightRotate": 
	    				Node<Integer> y = tree.search(num);
	    				tree.rightRotate(y);
	    				break; 
	    			case "leftRotate": 
	    				Node<Integer> x = tree.search(num); 
	    				tree.leftRotate(x);
	    				break; 
	    			case "preorder":
	    				tree.traverse("preorder", tree.getRoot());
	    				System.out.println();
	    				break;
	    			case "inorder":
	    				tree.traverse("inorder", tree.getRoot());
	    				System.out.println();
	    				break;
	    			case "postorder":
	    				tree.traverse("postorder", tree.getRoot());
	    				System.out.println(); 
	    				break; 
	    			default:
	    				System.out.println("Incorrect input");
	    				break;		
	    		}
	    	}
	    	
	    	scan.close();
	    }
}
