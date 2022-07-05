// Author: Lale Koşucu Özgen 
import java.util.*;

class Heap {
    private static final int d = 2;
    private int[] heap;
    private int heapSize;  //number of element inside of the heap

    public Heap(int capacity) {
        heapSize = 0; 
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }
    /**
     * the method checks whether the heap is empty or not.
     * @return heapSize == 0.
     */ 
    public boolean isEmpty() {
        return heapSize == 0;
    }
    /**
     * the method checks whether the heap is full or not.
     * @return heapSoze == heap.length.
     */
    public boolean isFull() {
        return heapSize == heap.length;
    }
    /**
     * @param index of the element whose parent's index is searched. 
     * @return the index of parent of a node.
     */ 
    private int parent(int i) {
        return (i - 1) / d; 
    }
    /**
     * @param i is the index of parent.
     * @param k is the order of the children.
     * @return the index of first or second child node of the parent node. 
     */
    private int kthChild(int i, int k) {
        return d * i + k; //index of child
    }
    /**
     * an element is inserted to the heap with this method.
     * @param the element that the method will insert.
     */
    
    public void insert(int x) {
        if (isFull())
            throw new UnsupportedOperationException("Heap is full");
        heap[heapSize++] = x;
        upHeap(heapSize - 1); 
    }
    /**
     * an element is deleted from the heap with this method.
     * @param the index of the element which will be deleted.
     * @return the element of the node which is deleted.
     */ 
    public int delete(int x) {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty");
        int key = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        downHeap(x);
        return key;
    }

    /**
     * the method maintains heap property during insertion
     * @param the index of the element which is the previous element of the last inserted element.
     */ 
    private void upHeap(int i) {
        int changeValue=heap[i];
        while((heap[parent(i)]<changeValue) && i>0 ){
            heap[i]=heap[parent(i)];
            i=parent(i);
        }
        heap[i]=changeValue;
    }

    /**
     * the method maintains heap property during deletion
     * @param the index of the element which was deleted.
     */ 
    private void downHeap(int i) {
        int child;
        int switchValue=heap[i];

        if(maxChild(i)!=-1){
            while((kthChild(i, 1)<heapSize)){
                child = maxChild(i);
                if(switchValue<heap[child]){
                    heap[i]=heap[child];
                }else{
                    break;
                }
                i=child;
           }
           heap[i]=switchValue;
        }
    }
    /**
     * @param i is the index of the node whose biggest child is searched.
     * @return the biggest child of the parent.
     *  If the node has no children or the elements of the childen are the equal, returns -1.
     */
    private int maxChild(int i) {   
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);

        if (leftChild < heap.length && rightChild < heap.length) {
            return heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
        } else if (rightChild < heap.length) {
            return heap[rightChild];
        } else if (leftChild < heap.length) {
            return heap[leftChild];
        }
        return -1;
    }
    
    /**
     * prints the elements of the heap from the root to the end node. 
     */
    public void printHeap() {
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    /**
     * @return the biggest node in the heap which is the root.
     */ 
    public int findMax() {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }
    /**
     * This method is used for autotesting.
     */
    public int[] getHeap() {
        return heap;
    }
    public static void main(String[] args) {
        Heap heap = new Heap(8);
        int x = 0;
        for(int i = 0; i<8; i++){
            x = (int) (Math.random()*100);
            heap.insert(x);
        }
        
        heap.printHeap();
        System.out.println(heap.delete(4)); 
        heap.printHeap();
        heap.delete(6);
        heap.printHeap();
 
    }
}