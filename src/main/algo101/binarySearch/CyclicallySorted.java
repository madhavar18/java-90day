package algo101.binarySearch;

public class CyclicallySorted {

    public static int minInCyclicallySorted(int a[]) {

        int low = 0;
        int high = a.length - 1;

        while(low < high) {
            int mid = (low + high) / 2;

            if(a[mid] > a[high])
                low = mid + 1;

            else  high = mid;
        }

        return low;
    }

    public static void main(String[] a) {
        int[] arr = new int[]{1,2,3,3,4,5,6,6,6,7};
        System.out.println(minInCyclicallySorted(arr));
    }
}
