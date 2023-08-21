package SWEA.CodeBattle.No_22_기초_Partial_Sort;

public class UserSolution {

    static final int MAX_SIZE = 100_000;
    static int size;

    static User[] HEAP;

    public void init() {
        size = 0;
        HEAP = new User[MAX_SIZE];
    }

    /**
     * user 추가
     *
     * @param uID       : uID (0 <= uID <= 100,000)
     * @param income    : income - user 의 수입. 클수록 우선순위가 높음.
     *                    동일하면 uID 가 작은 user 순위가 높음.
     */
    void addUser(int uID, int income) {
        HEAP[size++] = new User(uID, income);

        if (size <= 1) return;

        int idx = size - 1;

        while (compareUser(idx)) {
            siftUp(idx);
            idx = (idx - 1) / 2;
        }
    }

    boolean compareUser(int idx) {
        int parentIdx = (idx - 1) / 2;

        if (HEAP[parentIdx].income < HEAP[idx].income) return true;
        if (HEAP[parentIdx].income == HEAP[idx].income) return HEAP[parentIdx].uID > HEAP[idx].uID;
        return false;
    }

    void siftUp(int idx) {
        int parentIdx = (idx - 1) / 2;

        User tmp = HEAP[idx];
        HEAP[idx] = HEAP[parentIdx];
        HEAP[parentIdx] = tmp;
    }

    /**
     * 수입이 가장 큰 10명의 uID 를 수입 내림차순으로 구한다.
     * 10명이 되지 않으면 존재하는 대로 내림차순으로 구한다.
     * result 개수를 반환한다.
     *
     * @param result    : result[] 수입 큰순서대로 10개 저장.
     *
     * @return          : result[] 개수 반환
     */
    int getTop10(int[] result) {
        User[] popUser = new User[10];

        int res = 0;
        for (int i = 0; i < 10 && size > 0; i++) {
            User root = HEAP[0];
            result[i] = root.uID;
            popUser[res] = root;

            int idx = 0;
            HEAP[0] = HEAP[size-- - 1];
            res++;

            while (true) {
                int childIdx = getChildIdx(idx);

                if (childIdx == -1) break;

                siftDown(idx, childIdx);
                idx = childIdx;
            }
        }

        for (int i = 0; i < res; i++) {
            addUser(popUser[i].uID, popUser[i].income);
        }

        return res;
    }

    void siftDown(int idx, int childIdx) {
        User tmp = HEAP[idx];
        HEAP[idx] = HEAP[childIdx];
        HEAP[childIdx] = tmp;
    }

    int getChildIdx(int idx) {
        int leftChildIdx = idx * 2 + 1;
        int rightChildIdx = idx * 2 + 2;

        if (leftChildIdx >= size) return -1;

        int childIdx = -1;
        if (rightChildIdx >= size) {
            childIdx = leftChildIdx;
        } else {
            if (HEAP[leftChildIdx].income == HEAP[rightChildIdx].income) {
                if (HEAP[leftChildIdx].uID < HEAP[rightChildIdx].uID) childIdx = leftChildIdx;
                else childIdx = rightChildIdx;
            }
            else if (HEAP[leftChildIdx].income < HEAP[rightChildIdx].income) childIdx = rightChildIdx;
            else childIdx = leftChildIdx;
        }

        User child = HEAP[childIdx];

        if ((HEAP[idx].income < child.income) ||
                (HEAP[idx].income == child.income && HEAP[idx].uID > child.uID)) return childIdx;

        return -1;
    }

    static class User {
        int uID, income;

        public User(int uID, int income) {
            this.uID = uID;
            this.income = income;
        }
    }
}