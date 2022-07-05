//Author: Lale Koşucu Özgen
import java.util.ArrayList;
import java.util.Arrays;

class MergeSort {

    /**
     * This method merges the left and right subarrays bydivideing the elements of them in ascending order.
     * @param left is the left half side of the array.
     * @param right is the right half side of the array.
     * @return a sorted and merged array. 
     */
    public static int[] merge(int[] left, int[] right) {

        int[] arr = new int[left.length + right.length];

        int k=0,l=0,m=0;

        while (k<left.length && l<right.length){
            if(left[k] <= right[l]){
                arr[m] = left[k];
                k++;
            }else{
                arr[m] = right[l];
                l++;
            }
            m++;
        }
        while(k<left.length){
            arr[m] = left[k];
            k++;
            m++;
        }
        while(l<right.length){
            arr[m] = right[l];
            l++;
            m++;
        }
        return arr;
    }

    /**
     * The method recursively divides an array in half until there is only one element in the array. 
     * @param arr is the array will be divided by the method.
     * @return call the merge method if the length of the array bigger than one; otherwise return the array. 
     */
    public static int[] divide(int arr[]) {

        if (arr.length > 1) {
            int m = arr.length / 2;
            int[] l1 = Arrays.copyOfRange(arr, 0, m);
            int[] l2 = Arrays.copyOfRange(arr, m, arr.length);

            l1 = divide(l1);
            l2 = divide(l2);
            
            return merge(l1, l2);
        } else {
            return arr;
        }
    }
    public static void main(String[] args) {
        int arr[] = new int[9];

        for(int i=0; i<9; i++){
            arr[i] = (int) (Math.random()*10);
            System.out.println(arr[i]);
        }
        System.out.println(Arrays.toString(sort(arr)));
    }
}
