package infosys_prep;

public class KadanesAlgorithm {
    // the trick is to drop negative sum, never carry negative sum forward
    public long[] KadanesAlgorithm(int[] arr) {
        long sum = 0;
        long max = Long.MIN_VALUE;
        long start = -1;
        long end = -1;
        for(int i = 0; i < arr.length; i++) {
            if(sum == 0) start = i;
            sum = sum + arr[i];
            if(sum > max) {
                max = sum;
                end = i;
            }
            if(sum < 0) sum = 0;
        }
        return new long[]{max, start, end};
    }

    public static void main(String[] args) {
        KadanesAlgorithm k = new KadanesAlgorithm();
        int[] arr = new int[]{-2,  1, -3,  4, -1,  2,  1, -5,  4};
        long[] ans = k.KadanesAlgorithm(arr);
        System.out.println(ans[0]);
        for(long i = ans[1]; i < ans[2] + 1; i++) {
            System.out.println(arr[Math.toIntExact(i)]);
        }
    }
}
