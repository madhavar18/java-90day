package infosys_prep;

public class LowerBound {

    public static int lowerBound(int[] arr, int n, int target) {
        // smallest index such that arr[index] >= target

        int ans = n;
        int low = 0, high = n - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            if(arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }

    public static int upperBound(int[] arr, int n, int target) {
        // smallest index such that arr[index] > target

        int ans = n;
        int low = 0, high = n - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            if(arr[mid] > target) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,3,5,8,8,10,10,11};
        System.out.println(lowerBound(arr, arr.length, 9));
    }
}
