import java.util.Scanner;

public class TestContents {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Queue<Integer> queue = new Queue<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        String input;
        String task;
        while (scan.hasNext()){
            input = scan.nextLine();
            String[] phrases = input.split(" ");
            task = phrases[0];
            switch(task) {
            	case "insert":
            		queue.enqueue(Integer.parseInt(phrases[1]));
            		stack.push(Integer.parseInt(phrases[1]));
            		break;
            	case "remove":
            		queue.dequeue();
            		stack.pop();
            		break;
            	case "print":
            		queue.printQueue();
            		stack.printStack();
            		break;
            }
        }
        scan.close();
    }
}