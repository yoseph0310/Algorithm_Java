package Z_DataStructure.Tree.SegmentTree;

public class SegmentTree {
    long[] tree;        // 각 원소가 담길 트리
    int treeSize;       // 트리의 크기

    public SegmentTree(int arrSize) {
        // 트리 높이 구하기 - 2의 제곱꼴이 아닐때 +1 효과를 내기위해 올림 처리
        int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

        // 높이를 이용한 배열 사이즈 구하기
        this.treeSize = (int) Math.pow(2, h+1);

        // 배열 생성
        tree = new long[treeSize];
    }

    /**
     * 1. 생성 및 구성
     *
     * @param arr : 원소 배열
     * @param node : 현재 노드
     * @param start : 현재구간 배열 시작
     * @param end : 현재구간 배열 끝
     *
     * @return : 원소 배열 값 or 자식노드의 합
     */
    public long init(long[] arr, int node, int start, int end) {
        // 배열의 시작과 끝이 같다면 단말 노드이므로 원소 배열 값 그대로 담는다
        if (start == end) {
            return tree[node] = arr[start];
        }

        // 단말 노드가 아니면 자식노드 합 담기
        return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
    }

    /**
     * 2. 데이터 변경
     *
     * @param node : 현재 노드 idx
     * @param start : 배열의 시작
     * @param end : 배열의 끝
     * @param idx : 변경된 데이터의 idx
     * @param diff : 원래 데이터 값과 변경 데이터값의 차이
     */
    public void update(int node, int start, int end, int idx, long diff) {
        // 만약 변경할 index 값이 범위 바깥이면 확인 불필요
        if (idx < start || end < idx) return;

        // 변경된 값과 원래 값의 차이를 저장
        tree[node] += diff;

        // 단말 노드가 아니면 아래 자식들도 확인
        if (start != end) {
            update(node * 2, start, (start + end) / 2, idx, diff);
            update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
        }
    }

    /**
     * 3. 구간 합 구하기
     *
     * @param node : 현재 노드
     * @param start : 배열의 시작
     * @param end : 배열의 끝
     * @param left : 원하는 누적합의 시작
     * @param right : 원하는 누적합의 끝
     * @return : 누적합
     */
    public long sum(int node, int start, int end, int left, int right) {
        // 범위를 벗어나는 경우 더할 필요 없다.
        if (left > end || right < start) {
            return 0;
        }

        // 범위 내 완전히 포함시에는 더 내려가지 않고 바로 리턴
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 그 외의 경우 좌, 우로 지속 탐색 진행
        return sum(node * 2, start, (start + end) / 2, left, right) +
                sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }
}
