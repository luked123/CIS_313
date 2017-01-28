
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
		//
		if(this.top == null){
			return null; 
		}
		Node<E> temp = this.top; 
		this.top = this.top.getNext(); 
		
		return temp; 
	}
	
	public boolean isEmpty(){
		if(this.top == null){
			return true; 
		}
		return false; 
		
	}
	
	public void printStack(){
		Node<E> node = this.top; 		
		while(node != null){
			System.out.printf("%s ", node.getData().toString());
			node = node.getNext(); 
		}
		
	}
}