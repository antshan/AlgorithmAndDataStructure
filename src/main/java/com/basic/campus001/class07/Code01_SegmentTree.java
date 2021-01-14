package com.basic.campus001.class07;

public class Code01_SegmentTree {

    public static class SegmentTree {
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
            change = new int[MAXN << 2];
            update = new boolean[MAXN << 2];
        }

        public void build(int L, int R, int rt) {
            if (L == R) {
                sum[rt] = arr[L];
                return;
            }
            int mid = (L + R) >> 1;
            build(L, mid, rt << 1);
            build(mid + 1, R, (rt << 1) | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[(rt << 1) | 1];
        }

        public void add(int L, int R, int C, int l, int r, int rt){
            if (L <= l && r <= R){
                sum[rt] += (r - l + 1) * C;
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r -mid);
            if (L <= mid){
                add(L, R, C, l, mid, rt << 1);
            }
            if (mid < R){
                add(L, R, C, mid + 1, r, (rt << 1) | 1 );
            }
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt){
            if (L <= l && r <= R){
                change[rt] = C;
                update[rt] = true;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid){
                update(L, R, C, l, mid, rt << 1);
            }
            if (mid < R){
                update(L, R, C, mid+ 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        public long query(int L, int R, int l, int r, int rt){
            if (L <= l && r <= R){
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid){
                ans += query(L, R, l, mid, rt << 1);
            }
            if (mid < R){
                ans += query(L, R, mid + 1, r, (rt << 1) | 1);
            }
            return ans;
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]){
                update[rt << 1] = true;
                update[(rt << 1) | 1]  = true;
                change[rt << 1] = change[rt];
                change[(rt << 1) | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[(rt << 1) | 1] = 0;
                sum[rt << 1] = ln * change[rt];
                sum[(rt << 1) | 1] = rn * change[rt];
                update[rt] = false;
            }
            if (lazy[rt] != 0){
                lazy[rt << 1] += lazy[rt];
                lazy[(rt << 1) | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                sum[(rt << 1) | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

    }
}
