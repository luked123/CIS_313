
public class Stack<E> {
	//The stack class
	private Node<E> top;
	
	public Stack(){
		//constructs a stack
		this.top = null; 
	}
	
	public void push(E newData){
		//puts an element on the stack
		Node<E> node = new Node<E>(newData, this.top); 
		this.top = node; 

	}
	
	public Node<E> pop(){
		//removes an element from the stack
		if(this.top == null){
			return null; 
		}
		Node<E> temp = this.top; 
		this.top = this.top.getNext(); 
		
		return temp; 
	}
	
	public boolean isEmpty(){
		//returns true if stack is empty
		if(this.top == null){
			return true; 
		}
		return false; 
		
	}
	
	public void printStack(){
		//prints the stack with each element on a new line
		Node<E> node = this.top; 		
		while(node != null){
			System.out.printf("%s\n", node.getData().toString());
			node = node.getNext(); 
		}
		
	}
}