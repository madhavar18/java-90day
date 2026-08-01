package algo101.sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort {

    // The Engine
    private static void heapify(int[] a, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < n && a[left] > a[largest]) {
            largest = left;
        }

        if(right < n && a[right] > a[largest]) largest = right;

        if(largest != i) {
            swap(a, largest, i);
            heapify(a, n, largest);
        }
    }

    // Internal helper
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void buildHeap(int[] a, int n) {
        for(int i = (n / 2) - 1; i >= 0; i--) {
            heapify(a, n, i);
        }
    }

    public static void heapSort(int[] a, int n) {
        buildHeap(a, n);

        for(int i = n - 1; i >= 0; i--) {
            swap(a, i, 0);
            heapify(a, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 10, 3, 5, 1};
        heapSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
