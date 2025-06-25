package heap;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private ArrayList<Integer> heap = null;
    public Heap(){
        heap = new ArrayList<>();
    }

    public ArrayList<Integer> getHeapList(){
        return new ArrayList<>(heap);
    }
    private int getLeftChild(int parentIndex){
        int index = 2 * parentIndex + 1;
        return index;
    }

    private int getRightChild(int parentIndex){
        int index = 2 * parentIndex + 2;
        return index;
    }

    private int getParent(int childIndex){
        int index = (childIndex-1)/2;
        return index;
    }

    private void swapNodes(int index1, int index2){
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value){
        heap.add(value);
        int current = heap.size() - 1;

        while (current > 0 && heap.get(current) > heap.get(getParent(current))){
            swapNodes(current, getParent(current));
            current = getParent(current);
        }
    }

    public Integer remove(){
        if (heap.isEmpty()){
            return null;
        }else if (heap.size() == 1){
            return heap.removeFirst();
        }else {
            int maxValue = heap.getFirst();
            heap.set(0, heap.removeLast());
            sinkDown(0);
            return maxValue;
        }
    }

    private void sinkDown(int index) {
        int maxIndex = index;
        while (true){
            int leftIndex = getLeftChild(index);
            int rightIndex = getRightChild(index);

            if(leftIndex < heap.size() && heap.get(maxIndex) < heap.get(leftIndex)){
                maxIndex = leftIndex;
            }else if(rightIndex < heap.size() && heap.get(maxIndex) < heap.get(rightIndex)){
                maxIndex = rightIndex;
            }

            if (maxIndex != index){
                swapNodes(index, maxIndex);
                index = maxIndex;
            }else {
                return;
            }
        }
    }

    public static int findKthSmallest(int[] nums, int k){
        if (nums != null && k != 0){
//            Arrays.sort(nums);
//            return nums[k-1];
            Heap heap1 = new Heap();
            for (int num : nums){
                heap1.insert(num);

                if (heap1.getHeapList().size() > k){
                    heap1.remove();
                }
            }
            return heap1.remove();
        }
        return 0;
    }

    public static List<Integer> streamMax(int[] nums){
        List<Integer> result = new ArrayList<>();
        Heap numbers = new Heap();
        numbers.insert(Integer.MIN_VALUE);
        if(nums != null){
            for (int num : nums){
                numbers.insert(num);
                result.add(numbers.getHeapList().getFirst());
            }
            return result;
        }
        return result;
    }
}
