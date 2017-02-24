public class pQueue<E extends Comparable> {
    private MaxHeap myHeap;

    public pQueue (int s) {
    // Priority queue constructor.
    // All functions are wrappers for maxHeap.
    	
        this.myHeap = new MaxHeap(s); 
    }

    public void insert(E data){
    // Insert  a value into the priority queue. 
    	
        myHeap.insert(data);
    }

    
    public Comparable<E> maximum(){
    // Return the maximum value from the priority queue. 
    	
        return myHeap.maximum();
    }

    public Comparable<E> extractMax(){
    // Return and remove the maximum value from the priority queue. 
    	
        return myHeap.extractMax();
    }

    public boolean isEmpty(){
    // Returns boolean whether the priority queue is empty or not.
    	
    	if(myHeap.getLength() == 0){
    		return true; 
    	}
    	return false; 
    }

    
	public void build(E[] arr){
	// Builds a priority queue from unordered array. 
		
    	this.myHeap.buildHeap(arr);
    }
    
    public void print(){
    // Prints the priority queue. 	
    	
        Comparable queue[] = this.myHeap.getArray();
        int length = this.myHeap.getLength(); 
        for(int i = 1; i <= length; i++){
        	if(i == length){
        		System.out.printf("%s", queue[i]);
        	}
        	else{
        		System.out.printf("%s,",queue[i]);
        	} 
        }
        System.out.println();
    }
    
}
