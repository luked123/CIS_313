import java.util.Scanner;

public class lab3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		int lines = Integer.parseInt(scan.nextLine()); 
		int num = 0; 
		String array = "";
		pQueue<Integer> queue = new pQueue<Integer>(lines); 
		for(int i = 0; i < lines; i++){
			if(scan.hasNextLine()){
				String string = scan.nextLine();
				String command[] = string.split(" ");
				if(command[0].equals("build")){
					array = command[1];  
				}
				else if(command.length > 1){
					num = Integer.parseInt(command[1]); 
				}
				switch(command[0]){
				case "insert":
					queue.insert(num);
					break;
				case "maximum":    				
					System.out.println(queue.maximum());
					break; 
				case "extractMax":
					System.out.println(queue.extractMax());
					break;
				case "isEmpty":
					if(queue.isEmpty()){
						System.out.println("Empty");
					}
					else{
						System.out.println("Not Empty");
					}
					break;
				case "print":
					System.out.print("Current Queue: ");
					queue.print();
					break;
				case "build":
					String [] strArr = array.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
					Integer[] intArr = new Integer[strArr.length + 1]; 

					for(int j = 1; j < intArr.length; j++){
						intArr[j] = Integer.parseInt(strArr[j-1]); 	
					}
					queue.build(intArr);
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

