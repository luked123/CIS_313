
public class Queue<E> {
	//The queue class
	private Node<E> head;
	private Node<E> tail;
	
	public Queue(){
		//constructs a queue 
		this.head = null; 
		this.tail = null; 
	}
	
	public void enqueue(E newData){
		//places a new element into the queue
		Node<E> node = new Node<E>(newData, null);
		if(this.head == null){
			this.head = this.tail = node; 
		}
		else{
			this.tail.setNext(node);
			this.tail = node; 
		}
	}
	
	public Node<E> dequeue(){
		//removes an element from the queue
		if(this.head == null){
			return null; 
		}
		Node<E> node = this.head; 
		this.head = this.head.getNext();
		return node;

	}
	
	public boolean isEmpty(){
		//check if the queue is empty
		if(this.head == null){
			return true;
		}
		return false; 
	}
	
	public void printQueue(){
		//prints the queue
		Node<E> node = this.head; 
		while(node != null){
			System.out.printf("%s ", node.getData().toString());
			node = node.getNext(); 
		}
	}
}
