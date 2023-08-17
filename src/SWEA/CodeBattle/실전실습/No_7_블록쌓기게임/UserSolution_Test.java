package SWEA.CodeBattle.실전실습.No_7_블록쌓기게임;

/**
 *  행의 개수 : 10,000 개
 *  열의 개수 : C 개
 *
 *  << 제약 사항 >>
 *      1. dropBlocks() 최대 호출 횟수 : 3,000 회
 *      2. 격자판 행 개수는 10,000
 *      3. 격자판 열 개수는 1,000,000 이하이다.
 *      4. 격자판에 남아있는 블록들의 개수는 Integer Type 의 최대값 (약 21억)보다 클 수 있다는 점에 유의.
 *
 *  << Key Point >>
 *      1. col 의 범위에 블록들이 쌓여 나가진다.
 *      2. 이를 달리 해석하면 col 인덱스에 원소가 더해져 나간다.
 *      3. col 을 단순히 열이 아닌 범위라고 생각을 해보면
 *      4. 가장 높은 블록의 높이는 범위 내의 최댓값이며
 *      5. 남은 블록의 개수는 쌓여진 블록 - 터진 블록의 개수이다.
 *      6. 여기서 터진 블록은 0 ~ col 전체 범위의 최솟값이 된다.
 *      7. 범위에 쌓인 블록을 계속해서 기록해 나갈텐데 맨 밑줄서부터 가득차면 삭제되기 때문에 모든 범위의 최솟값이 즉 터진 블록의 개수이다.
 *      8. 즉, 블록을 쌓아가면서 최댓값, 최솟값을 빼면 된다.
 *      9. 여기서 일정한 범위내의 업데이트가 일어나기 때문에 Lazy Segment Tree 개념을 사용한다.
 */
class UserSolution_Test {

    static final int MAX_COL_CNT = 1_000_000;
    static int[] minSeg, maxSeg, lazySeg;
    static int C, cnt;

    /**
     * 처음 격자판은 비어있다.
     *
     * @param c        : 격자판 열의 개수 (1 <= c <= 1,000,000)
     */
    void init(int c) {
        C = c;
        cnt = 0;

        minSeg = new int[4 * MAX_COL_CNT];
        maxSeg = new int[4 * MAX_COL_CNT];
        lazySeg = new int[4 * MAX_COL_CNT];

        for (int i = 0; i < 4 * C; i++) {
            minSeg[i] = 0;
            maxSeg[i] = 0;
            lazySeg[i] = 0;
        }
    }

    /**
     * 세그먼트 트리에 원소 추가
     *
     * @param node         : 노드 인덱스
     * @param left         : 탐색 left 포인터
     * @param right        : 탐색 right 포인터
     * @param start        : 범위 시작 값
     * @param end          : 범위 끝 값
     * @param d            : update 될 값. 이 문제에서는 주어지는 사각형의 mHeight.
     */
    void add(int node, int left, int right, int start, int end, int d) {
        // lazy 가 남아있는 것들을 갱신해준다.
        updateLazy(node, left, right);

        if (end < left || right < start) return;

        // 범위안에 속한다면 새로 변경될 값을 더하고 lazy 를 새로 업데이트 해준다.
        if (start <= left && right <= end) {
            lazySeg[node] = d;
            updateLazy(node, left, right);
            return;
        }

        int mid = (left + right) / 2;
        add(node * 2, left, mid, start, end, d);
        add(node * 2 + 1, mid + 1, right, start, end, d);
        minSeg[node] = Math.min(minSeg[node * 2], minSeg[node * 2 + 1]);
        maxSeg[node] = Math.max(maxSeg[node * 2], maxSeg[node * 2 + 1]);
    }

    /**
     * lazy 값 업데이트.
     *
     * @param node      : 노드 인덱스
     * @param left      : 탐색 left 포인터
     * @param right     : 탐색 right 포인터
     */
    void updateLazy(int node, int left, int right) {
        if (lazySeg[node] == 0) return;

        minSeg[node] += lazySeg[node];
        maxSeg[node] += lazySeg[node];

        if (left != right) {
            lazySeg[node * 2] += lazySeg[node];
            lazySeg[node * 2 + 1] += lazySeg[node];
        }

        lazySeg[node] = 0;
    }

    /**
     * 격자판의 가로방향의 위치 mCol 에 높이가 mHeight 이고 길이가 mLength 인 직사각형 모양으로 배치된 블록들을 위치시킨다.
     * 격자판을 벗어나게 하는 mCol, mLength 값이 주어지는 경우는 없다.
     *
     * 배치된 블록들이 아래로 내려가다 멈추게 되면 각 행마다 블록들로 가득 찼는지 여부를 확인하여 해당 행의 블록들을 삭제하고 위쪽의 블록들을 밑으로 내린다.
     * 이후 남아있던 블록들의 개수를 1,000,000 으로 나눈 나머지와 같은 높은 블록의 높이를 반환한다.
     *
     * @param mCol      : 떨어뜨릴 블록들의 위치 (0 <= mCol <= C - mLength)
     * @param mHeight   : 떨어뜨릴 블록들로 형성되는 사각형의 높이 (1 <= mHeight <= 3)
     * @param mLength   : 떨어뜨릴 블록들로 형성되는 사각형의 길이 (2 <= mLength <= C)
     *
     * @return          : result 내에서 top - 가장 높은 블록의 높이
     *                  : result 내에서 count - 남은 블록 개수 % 1,000,000
     */
    Solution.Result dropBlocks(int mCol, int mHeight, int mLength) {
        add(1, 0, C-1, mCol, mCol + mLength - 1, mHeight);
        updateLazy(1, 0, C-1);
        int min = minSeg[1];
        cnt = ((cnt + mHeight * mLength - min * C) % MAX_COL_CNT + MAX_COL_CNT) % MAX_COL_CNT;
        add(1, 0, C-1, 0, C-1, -min);

        Solution.Result ret = new Solution.Result();
        ret.top = maxSeg[1];
        ret.count = cnt;

        return ret;
    }

}