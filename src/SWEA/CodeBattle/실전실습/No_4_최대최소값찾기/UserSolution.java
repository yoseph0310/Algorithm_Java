package SWEA.CodeBattle.실전실습.No_4_최대최소값찾기;

public class UserSolution {

    static final int BUCKET_MAX_LEN = 448;
    static final int INF = 100_000_001;

    static int poolLastIdx;
    static Bucket[] pool;
    static Bucket bHead, bTail;

    /**
     * N 은 초기 상태에 저장해야 할 수의 개수
     * mValue 에는 저장해야 할 수가 0번 인덱스부터 순서대로 저장되어 있다.
     *
     * @param N                 : 1 <= N <= 30,000
     * @param mValue            : 0 <= mValue[i] <= 100,000,000 (단, 0 <= i <= N-1)
     */
    void init(int N, int[] mValue) {
        poolLastIdx = 0;

        pool = new Bucket[447];
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new Bucket();
        }
        bHead = new Bucket();
        bTail = new Bucket();

        pool[0].init(bHead, bTail);

        bHead.next = pool[0];
        bTail.prev = pool[0];

        add(N, mValue);

    }

    /**
     * 새로운 수 M 개를 기존에 저장되어 있는 수의 오른쪽 끝에 저장.
     * mValue 에는 저장해야할 수가 0번 인덱스부터 순서대로 저장되어 있다.
     *
     * @param M                 : 1 <= M <= 100
     * @param mValue            : 0 <= mValue[i] <= 100,000,000 (단 0 <= i <= M-1)
     */
    void add(int M, int[] mValue) {
        for (int i = 0; i < M; i++) {
            // 마지막 버킷의 크기가 최대 크기를 넘으면
            if (bTail.prev.size >= BUCKET_MAX_LEN) {
                // 새로 추가되는 버킷의 링크 작업
                pool[++poolLastIdx].init(bTail.prev, bTail);

                // tail 의 기존 이전노드의 다음을 새 노드로 링크, tail 의 새 이전노드로 새 노드로 링크
                bTail.prev.next = pool[poolLastIdx];
                bTail.prev = pool[poolLastIdx];
            }

            // 마지막 버킷에 원소들을 추가한다.
            bTail.prev.add(mValue[i]);
        }
    }

    /**
     * 제일 앞에 있는 수를 1번째 수라고 정의했을 때, mFrom 부터 mTo 까지 삭제한다.
     * 삭제 후 남아 있는 수들은 그 순서를 유지한다.
     *
     * @param mFrom             : 삭제해야할 수의 시작 위치 (인덱스)
     * @param mTo               : 삭제해야할 수의 마지막 위치 (인덱스)
     */
    void erase(int mFrom, int mTo) {
        int skip = mFrom - 1;
        int rest = mTo - mFrom + 1;
        Bucket bucket = bHead.next;

        // 찾으려는 시작 범위가 버킷 사이즈보다 크면
        while (skip >= bucket.size) {
            skip -= bucket.size;
            bucket = bucket.next;
        }

        // 삭제해야할 수의 개수가 0보다 크고 버킷 크기에서 스킵한 개수를 뺀거보다도 아직도 남아있다면
        while (rest > 0 && bucket.size - skip <= rest) {
            rest -= bucket.size - skip;
            bucket.size = skip;
            bucket.recalculate();

            if (bucket.size == 0) {
                bucket.prev.next = bucket.next;
                bucket.next.prev = bucket.prev;
            }

            bucket = bucket.next;
            skip = 0;
        }

        // 아직도 더 남아있다면
        if (rest > 0) {
            // 처리하려는 버킷의 이전 버킷이 헤드가 아니고
            // 삭제되지 않은 것들의 개수가 최대 용적보다 작거나 같으면
            if (bucket.prev != bHead && bucket.prev.size + bucket.size - rest <= BUCKET_MAX_LEN) {
                for (int i = 0; i < skip; i++) {
                    bucket.prev.add(bucket.numArr[i]);
                }
                for (int i = skip + rest; i < bucket.size; i++) {
                    bucket.prev.add(bucket.numArr[i]);
                }

                bucket.prev.next = bucket.next;
                bucket.next.prev = bucket.prev;
            }
            // 삭제되지 않은 것들의 개수가 최대 용적보다 크면
            else {
                for (int i = skip + rest; i < bucket.size; i++) {
                    bucket.numArr[i - rest] = bucket.numArr[i];
                }
                bucket.size -= rest;
                bucket.recalculate();
            }
        }
    }

    /**
     * 오른쪽 첫번째 수부터 K 번째까지의 수중 가장 큰 수와 가장 작은 수의 차이를 반환한다.
     * K는 현재 저장되어 있는 수의 개수 이하임이 보장된다.
     *
     * @param K                 : 1 <= K <= 현재 저장되어 있는 수의 개수
     * @return                  : K 개의 수 중 가장 큰 수와 작은 수의 차이
     */
    int find(int K) {
        Bucket bucket = bTail.prev;
        int minNum = INF;
        int maxNum = -1;

        while (K > 0) {
            if (bucket.size >= K) {
                for (int i = bucket.size - K; i < bucket.size; i++) {
                    minNum = Math.min(minNum, bucket.numArr[i]);
                    maxNum = Math.max(maxNum, bucket.numArr[i]);
                }
                K = 0;
            } else {
                minNum = Math.min(minNum, bucket.minNum);
                maxNum = Math.max(maxNum, bucket.maxNum);
                K -= bucket.size;
            }
            bucket = bucket.prev;
        }

        return maxNum - minNum;
    }

    static class Bucket {
        int size;
        int minNum, maxNum;
        int[] numArr;
        Bucket prev, next;

        public Bucket() {
            this.numArr = new int[BUCKET_MAX_LEN];
        }

        public void init(Bucket prev, Bucket next) {
            this.size = 0;
            this.prev = prev;
            this.next = next;
            this.minNum = INF;
            this.maxNum = -1;
        }

        public void add(int num) {
            numArr[size++] = num;
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
        }

        public void recalculate() {
            minNum = INF;
            maxNum = -1;

            for (int i = 0; i < size; i++) {
                minNum = Math.min(minNum, numArr[i]);
                maxNum = Math.max(maxNum, numArr[i]);
            }
        }
    }
}
