public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    public MaxHeap(int s){
    // Heap constructor.
    	
    	this.maxSize = s; 
    	this.length  = 0; 
    	this.myArray = (E[]) (new Comparable[maxSize + 1]); 
    }
    
    
    public E[] getArray(){
    // Returns the array of the heap.
    	
        return myArray;
    }

    
    public void setArray(E[] newArray){
    // Sets the array of the heap to a new array.
    	
    	if (newArray.length > maxSize + 1){
    		return;
    	}
    	
        myArray = newArray;
        length = newArray.length-1;
    }

    
    public int getMaxSize(){
    // Gets the maximum size of the heap.
    	
        return maxSize;
    }

    
    public void setMaxSize(int ms){
    // Set the maximum size of the heap. 
    	
        maxSize = ms;
    }

    
    public int getLength(){
    // Get the length of the array. 
    	
        return length;
    }

    
    public void setLength(int l){
    // Set the length of the heap.
    	
        length = l;
    }
    
    
    public void insert(E data){
    //Insert a data item into the heap. 
    	
    	this.length ++;
    	if(this.length >= this.maxSize){
    		System.out.println("no room in the array");
    		this.length --;
    		return; 
    	}
    	
    	int position = this.length;
    	int parent  = this.length / 2;
    	this.myArray[position ] = data;
    	
    	while((position > 1) && (this.myArray[parent].compareTo(this.myArray[position]) < 0 )){     // data bubbles up to the right place in the heap.
    		this.myArray[position] = this.myArray[parent]; 
    		this.myArray[parent] =  data;  
    		position = parent; 
    		parent  = position / 2;
    	}
    }

    
    public Comparable<E> maximum(){
    	// Returns the maximum value in the heap
    	
        return this.myArray[1]; 
    }

    public Comparable<E> extractMax(){
        // Returns and removes the maximum value in the heap
    	
    	if(this.length < 1){
    		System.out.println("Heap is empty");
    		return null; 
    	}
    	
    	Comparable<E> max = this.myArray[1]; 
    	this.myArray[1] = this.myArray[this.length]; 
    	this.length --;
    	heapify(1);                                  // Restores heap order property
    	return max; 
    }
    
    public void heapify(int i){
    	// Creates a heap from index i
    	// Used recursively all the way up to the root to maintain heap order property.
    	// Bubbles down 
    	
    	int left = 2 * i;          // left child
    	int right = (2 * i) + 1;   // right child
    	int largest = i;           
    	
    	if((left <= this.length) && (this.myArray[left].compareTo(this.myArray[largest]) > 0)){    // See if left child is larger than parent  
    		largest = left; 
    	}
    	
    	if((right <= this.length) && (this.myArray[right].compareTo(this.myArray[largest]) > 0)){  // See if right child is larger than parent or left child
    		largest = right; 
    	}
    	
    	if( largest != i){                            // If the parent is not larger, swap values and recursively call index of larger child.                                             
    		E data = this.myArray[i];
    		this.myArray[i] = this.myArray[largest]; 
    		this.myArray[largest] = data; 
    		heapify(largest); 
    	}
    	
    }
    
    
    public void buildHeap(E[] newArr){
    	// Builds a max heap out of an unsorted array in O(n) time
    	
		setArray(newArr);
		for(int i = this.length/2; i > 0; i--){
			heapify(i); 
		}
    	 
	}
}
