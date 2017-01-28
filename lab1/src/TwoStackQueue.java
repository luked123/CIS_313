
public class TwoStackQueue<E>{
	Stack<E> stack1;  
	Stack<E> stack2;  
	
	public TwoStackQueue(){
		this.stack1 = new Stack<E>(); 
		this.stack2 = new Stack<E>(); 
	}
	
	public void enqueue(E newData){
		stack2.push(newData); 
	}
	
	public Node<E> dequeue(){
		if(!stack1.isEmpty()){
			return stack1.pop(); 
		} 
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop().getData());
		}
		return stack1.pop(); 
	}
	
	public boolean isEmpty(){
		if(stack1.isEmpty() && stack2.isEmpty()){
			return true;
		}
		return false; 
	}
	
	public void printQueue(){
		Stack<E> temp = new Stack<E>(); 
		
		//puts stack2 into a queue form aligned with stack1
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
