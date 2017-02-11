import java.util.Scanner;

public class TreeCompare {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		BST<Integer> tree1 = new BST<Integer>();
		BST<Integer> tree2 = new BST<Integer>();
		BST<Integer> tree = tree1;
		
		String pre_tree1 = "";
		String in_tree1  = "";
		String pre_tree2 = "";
		String in_tree2  = "";
		
		String pre_tree = pre_tree1;
		String in_tree  = in_tree1; 
		
		int num = 0; 
		for(int j = 0; j < 2; j ++){
			int lines = Integer.parseInt(scan.nextLine());
			if(j==1){
				tree = tree2; 
			}
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
				case "preorder":
					tree.traverse("preorder", tree.getRoot());
					System.out.println();
					break;
				case "inorder":
					tree1.traverse("inorder", tree.getRoot());
					System.out.println();
					break;
				case "postorder":
					tree1.traverse("postorder", tree.getRoot());
					System.out.println(); 
					break; 
				default:
					System.out.println("Incorrect input");
					break;		
				}
			}
		}
		scan.close();
	}

}

