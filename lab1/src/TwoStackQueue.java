
public class TwoStackQueue<E>{
	//TwoStackQueue Class
	Stack<E> stack1;  
	Stack<E> stack2;  
	
	public TwoStackQueue(){
		//constructs two stacks which will implement a queue
		this.stack1 = new Stack<E>(); 
		this.stack2 = new Stack<E>(); 
	}
	
	public void enqueue(E newData){
		//inserts an element into the queue
		stack2.push(newData); 
	}
	
	public Node<E> dequeue(){
		//removes an element from the queue
		if(!stack1.isEmpty()){
			return stack1.pop(); 
		} 
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop().getData());
		}
		return stack1.pop(); 
	}
	
	public boolean isEmpty(){
		//returns true if queue is empty
		if(stack1.isEmpty() && stack2.isEmpty()){
			return true;
		}
		return false; 
	}
	
	public void printQueue(){
		Stack<E> temp = new Stack<E>(); 
		//puts stack2 into a queue form, aligns with stack1
		while(!stack2.isEmpty()){
			temp.push(stack2.pop().getData());
		}
		
		//prints queue
		stack1.printStack();
		temp.printStack();
		
		//resets the stack; 
		while(!temp.isEmpty()){
			stack2.push(temp.pop().getData());
		}
		
	}
}
