package algo101.binarySearch;

public class NoOfOccurences {

    public static int ltOccurence(int a[], int ele) {
        int u = a.length - 1, l = 0, ind = -1;

        if(a.length == 1 && a[0] == ele) return 0;

        while(l <= u) {
            int m = (l + u) / 2;

            if(a[m] == ele) {
                u = m -1;
                ind = m;
            } else if (a[m] > ele) {
                u = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ind;
    }

    public static int RtOccurence(int a[], int ele) {
        int u = a.length - 1, l = 0, ind = -1;

        if(a.length == 1 && a[0] == ele) return 0;

        while(l <= u) {
            int m = (l + u) / 2;

            if(a[m] == ele) {
                l = m + 1;
                ind = m;
            } else if (a[m] > ele) {
                u = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ind;
    }

    public static int count(int a[], int ele) {
        int first = ltOccurence(a, ele);

        if(first == -1)
            return 0;

        int last = RtOccurence(a, ele);

        return last - first + 1;
    }

    public static void main(String[] a) {
        int[] arr = new int[]{1,2,3,3,4,5,6,6,6,7};
        System.out.println(count(arr, 6));
    }
}



