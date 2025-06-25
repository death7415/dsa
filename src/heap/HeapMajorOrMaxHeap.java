package heap;

public class HeapMajorOrMaxHeap {
    int size;
    int capacity;
    int []heap;

    public HeapMajorOrMaxHeap(int []heap){
        size = heap.length;
        this.capacity = heap.length;
        this.heap = heap;
    }

    public int pop(){
        if (size == 0){
            return -1;
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return max;
    }

    public void buildTree(){
        //leave nodes range N/2 to N-1
        for (int i = (size/2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int index) {
        int largest = index;
        int leftChildIndex = getLeftChild(index);
        int rightChildIndex = getRightChild(index);

        if (leftChildIndex < size && heap[leftChildIndex] > heap[largest]){
            largest = leftChildIndex;
        }
        if (rightChildIndex < size && heap[rightChildIndex] > heap[largest]){
            largest = rightChildIndex;
        }
        if (largest != index){
            swap(index, largest);
            heapify(largest);
        }
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int getLeftChild(int index){
        return 2 * index + 1;
    }

    private int getRightChild(int index){
        return 2 * index + 2;
    }

    public void print(){
        for (int i = 0; i < size; i++) {
            if (i == size -1){
                System.out.print(heap[i]);
            }else {
                System.out.print(heap[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int []arr = {10, 5, 20, 6, 11};
        HeapMajorOrMaxHeap majorOrMaxHeap = new HeapMajorOrMaxHeap(arr);
        majorOrMaxHeap.buildTree();
        majorOrMaxHeap.print();
        System.out.println("=======");
        System.out.println("popped = " +  majorOrMaxHeap.pop());
        majorOrMaxHeap.print();
    }
}
