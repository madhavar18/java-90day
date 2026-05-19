package infosys_prep;
import java.util.*;
public class SegmentTree1 {
    int[] a = new int[100005];
    int[] seg = new int[4*100005];
    public void build(int ind, int low, int high) {
        if(low == high) {
            seg[ind] = a[low];
            return;
        }
        int mid = (low + high) / 2;
        build(2*ind+1, low, mid);
        build(2*ind+2, mid+1, high);
        seg[ind] = seg[2*ind+1] + seg[2*ind+2];
    }
    public void query1(int low, int high, int l, int r) {
        for(int i = l; i < r+1; i++) {
            a[i] = (i - l + 1) * a[l];
        }
        build(0, low, high);
    }
    public int query2(int ind, int low, int high, int l, int r) {
        // 1. complete inclusion
        if(low >= l && high <= r) {
            return seg[ind];
        }
        // 2. no relation to range
        if(high < l || low > r) return 0;
        // 3. overlapping condition
        int mid = (low + high) / 2;
        int left = query2(2*ind+1, low, mid, l, r);
        int right = query2(2*ind+2, mid+1, high, l ,r);
        return left + right;
    }
    public static void main(String[] args) {
        SegmentTree1 st = new SegmentTree1();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            st.a[i] = sc.nextInt();
        }
        st.build(0, 0, n-1);
        int nq = sc.nextInt();
        int[] query = new int[3];
        while(nq-- > 0) {
            for (int i = 0; i < 3; i++) {
                query[i] = sc.nextInt();
            }
            switch (query[0]) {
                case 1:
                    st.query1(0, n - 1, query[1], query[2]);
                    break;
                case 2:
                    System.out.println(st.query2(0, 0, n - 1, query[1], query[2]));
                    break;
                default:
                    System.out.println("invalid choice");
            }
        }
    }
}
