package infosys_prep;
import java.util.*;
public class SegmentTree {
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
        seg[ind] = Math.max(seg[2*ind+1],seg[2*ind+2]);
    }
    public int query(int ind, int low, int high, int l, int r) {
        // 1. complete inclusion
        if(low >= l && high <= r) {
            return seg[ind];
        }
        // 2. no relation to range
        if(high < l || low > r) return Integer.MIN_VALUE;
        //3. overlapping condition
        int mid = (low + high) / 2;
        int left = query(2*ind+1, low, mid, l, r);
        int right = query(2*ind+2, mid+1, high, l, r);
        return Math.max(left, right);
    }
    public static void main(String[] args) {
        SegmentTree st = new SegmentTree();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            st.a[i] = sc.nextInt();
        }
        st.build(0, 0, n-1);
        int q = sc.nextInt();
        while(q-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(st.query(0, 0, n-1, l, r));
        }
    }
}
