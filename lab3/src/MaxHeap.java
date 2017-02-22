public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    public MaxHeap(int s){
    	//heap constructor
    	this.maxSize = s; 
    	this.length  = 0; 
    	this.myArray = (E[]) (new Comparable[maxSize]); 
    }

	// helper functions
    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
    	if (newArray.length > maxSize){
    		return;
    	}
    	
        myArray = newArray;
        length = newArray.length-1;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    // Other Methods
    public void insert(E data){
    	
    	this.length ++;
    	if(this.length >= this.maxSize){
    		System.out.println("no room in the array");
    		this.length --;
    		return; 
    	}
    	
    	int position = this.length;
    	int parent  = this.length / 2;
    	this.myArray[position ] = data;
    	
    	while((position > 1) && (this.myArray[parent].compareTo(this.myArray[position]) < 0 )){
    		this.myArray[position] = this.myArray[parent]; 
    		this.myArray[parent] =  data;  
    		position = parent; 
    		parent  = position / 2;
    	}
    	
    	// Insert an element into your heap.
    	
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)
        
    }

    public Comparable<E> maximum(){
        return this.myArray[1]; 
    }

    public Comparable<E> extractMax(){
        
    	if(this.length < 1){
    		System.out.println("Heap is empty");
    		return null; 
    	}
    	
    	Comparable<E> max = this.myArray[1]; 
    	this.myArray[1] = this.myArray[this.length]; 
    	this.length --;
    	heapify(1); 
    	return max; 
    }
    
    public void heapify(int i){
    	
    	int left = 2 * i; 
    	int right = (2 * i) + 1; 
    	int largest = i; 
    	
    	if((left <= this.length) && (this.myArray[left].compareTo(this.myArray[largest]) > 0)){
    		largest = left; 
    	}
    	
    	if((right <= this.length) && (this.myArray[right].compareTo(this.myArray[largest]) > 0)){
    		largest = right; 
    	}
    	
    	if( largest != i){
    		E data = this.myArray[i];
    		this.myArray[i] = this.myArray[largest]; 
    		this.myArray[largest] = data; 
    		heapify(largest); 
    	}
    	
    }
    
    public void buildHeap(E[] newArr){
		setArray(newArr);
		for(int i = this.length/2; i > 0; i--){
			heapify(i); 
		}
    	 
	}
}
