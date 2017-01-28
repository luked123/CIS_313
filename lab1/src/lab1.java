import java.util.Scanner;

public class lab1 {
	public static void main(String[] args){
	
		// Create a Scanner that reads system input
		Scanner scan = new Scanner(System.in);
		int lines = scan.nextInt(); 
		for(int i = 0 ; i < lines; i++){
			if(isPalindrome(scan.next())){
				System.out.println("This is a Palindrome.");
			}
			else{
				System.out.println("Not a Palindrome.");
			}
		}
		scan.close();		
	}
	
	public static boolean isPalindrome(String s){
		Queue<Character> queue = new Queue<Character>();
		Stack<Character> stack = new Stack<Character>(); 
		
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			queue.enqueue(c);
			stack.push(c);
		}
		
		while(!queue.isEmpty()){
			if(queue.dequeue().getData() != stack.pop().getData()){
				return false;
			} 
		}
		return true; 
	}
}
