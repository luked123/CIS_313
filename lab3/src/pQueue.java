public class pQueue<E extends Comparable> {
    private MaxHeap myHeap;

    public pQueue (int s) {
        this.myHeap = new MaxHeap(s); 
    }

    public void insert(E data){
        myHeap.insert(data);
    }

    public Comparable<E> maximum(){
        return myHeap.maximum();
    }

    public Comparable<E> extractMax(){
        return myHeap.extractMax();
    }

    public boolean isEmpty(){
    	if(myHeap.getLength() == 0){
    		return true; 
    	}
    	return false; 
    }

	public void build(E[] arr){
    	this.myHeap.buildHeap(arr);
    }
    
    public void print(){
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
