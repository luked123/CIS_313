
public class Node<E> {
	//The node class
	private E data;
	private Node<E> next;
	
	public Node(E c, Node<E> n){
		//Constructs a node
		this.data = c; 
		this.next = n; 
	}
	
	public void setData(E d){
		//Sets the data field
		this.data = d; 
	}
	
	public void setNext(Node<E> n){
		//Sets the next field
		this.next = n; 
	}
	public E getData(){
		//Returns the data field
		return this.data;
	}
	
	public Node<E> getNext(){
		//Returns the next field
		return this.next; 
	}
	
}
