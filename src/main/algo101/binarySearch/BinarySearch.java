package algo101.binarySearch;

public class BinarySearch {

    public static int binarySearch(int[] a, int ele) {
        int u = a.length - 1, l = 0;

        if(a.length == 1 && a[0] == ele) return 0;

        while(l <= u) {
            int m = (l + u) / 2;

            if(a[m] == ele) {
              return m;
            } else if (a[m] > ele) {
                u = m - 1;
            } else {
                l = m + 1;
            }
        }
       return -1;
    }

    public static void main(String[] a) {
        int[] arr = new int[]{1,2,3,3,4,5,6,6,6,7};
        System.out.println(binarySearch(arr, 6));
    }
}
