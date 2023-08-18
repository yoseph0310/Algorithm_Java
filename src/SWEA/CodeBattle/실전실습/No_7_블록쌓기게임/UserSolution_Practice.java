package SWEA.CodeBattle.실전실습.No_7_블록쌓기게임;

public class UserSolution_Practice {

    static final int MAX_COL = 1_000_000;
    static final int TREE_SIZE = 4 * MAX_COL;
    static int[] minSeg, maxSeg, lazySeg;
    static int C, cnt;

    void init(int c) {
        C = c;
        cnt = 0;

        minSeg = new int[TREE_SIZE];
        maxSeg = new int[TREE_SIZE];
        lazySeg = new int[TREE_SIZE];

        for (int i = 0; i < TREE_SIZE; i++) {
            minSeg[i] = 0;
            maxSeg[i] = 0;
            lazySeg[i] = 0;
        }
    }

    static void add(int node, int start, int end, int left, int right, int diff) {
        updateLazy(node, start, end);

        if (right < start || end < left) return;
        if (left <= start && end <= right) {
            lazySeg[node] = diff;
            updateLazy(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        add(node * 2, start, mid, left, right, diff);
        add(node * 2 + 1, mid + 1, end, left, right, diff);
        minSeg[node] = Math.min(minSeg[node * 2], minSeg[node * 2 + 1]);
        maxSeg[node] = Math.max(maxSeg[node * 2], maxSeg[node * 2 + 1]);
    }

    static void updateLazy(int node, int start, int end) {
        if (lazySeg[node] == 0) return;

        minSeg[node] += lazySeg[node];
        maxSeg[node] += lazySeg[node];

        // 자식 노드가 있으면
        if (start != end) {
            lazySeg[node * 2] += lazySeg[node];
            lazySeg[node * 2 + 1] += lazySeg[node];
        }

        lazySeg[node] = 0;
    }

    Solution.Result dropBlocks(int mCol, int mHeight, int mLength) {
        add(1, 0, C-1, mCol, mCol + mLength - 1, mHeight);
        updateLazy(1, 0, C-1);
        int min = minSeg[1];
        cnt = ((cnt + mHeight * mLength - min * C) % MAX_COL + MAX_COL) % MAX_COL;
        add(1, 0, C-1, 0, C-1, -min);

        Solution.Result res = new Solution.Result();
        res.top = maxSeg[1];
        res.count = cnt;

        return res;
    }
}
