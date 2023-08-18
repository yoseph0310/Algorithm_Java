package SWEA.CodeBattle.실전실습.No_4_최대최소값찾기;

public class UserSolution_Practice {

    static final int MAX_BUCKET_SIZE = 448;
    static final int INF = 1_000_000_001;

    static Bucket[] pool;
    static Bucket pHead, pTail;
    static int poolLastIdx;

    static class Bucket {
        int[] numArr;
        int size, min, max;
        Bucket next, prev;

        public Bucket() {
            this.numArr = new int[MAX_BUCKET_SIZE];
        }

        public void init(Bucket prev, Bucket next) {
            this.prev = prev;
            this.next = next;
            this.size = 0;
            this.min = INF;
            this.max = -1;
        }

        public void add(int num) {
            numArr[size++] = num;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        public void updateMinMax() {
            min = INF;
            max = -1;

            for (int i = 0; i < size; i++) {
                min = Math.min(numArr[i], min);
                max = Math.max(numArr[i], max);
            }
        }
    }

    void init(int N, int[] mValue) {
        poolLastIdx = 0;
        pool = new Bucket[MAX_BUCKET_SIZE];
        for (int i = 0; i < MAX_BUCKET_SIZE; i++) {
            pool[i] = new Bucket();
        }

        pHead = new Bucket();
        pTail = new Bucket();

        pool[0].init(pHead, pTail);

        pHead.next = pool[0];
        pTail.prev = pool[0];

        add(N, mValue);
    }

    void add(int M, int[] mValue) {
        for (int i = 0; i < M; i++) {
            if (pTail.prev.size >= MAX_BUCKET_SIZE) {
                pool[++poolLastIdx].init(pTail.prev, pTail);

                pTail.prev.next = pool[poolLastIdx];
                pTail.prev = pool[poolLastIdx];
            }

            pTail.prev.add(mValue[i]);
        }
    }

    void erase(int mFrom, int mTo) {
        int skip = mFrom - 1;
        int rest = mTo - mFrom + 1;
        Bucket bucket = pHead.next;

        while (skip >= bucket.size) {
            skip -= bucket.size;
            bucket = bucket.next;
        }

        while (rest > 0 && bucket.size - skip <= rest) {
            rest -= bucket.size - skip;
            bucket.size = skip;
            bucket.updateMinMax();

            if (bucket.size == 0) {
                bucket.prev.next = bucket.next;
                bucket.next.prev = bucket.prev;
            }

            bucket = bucket.next;
            skip = 0;
        }

        if (rest > 0) {
            if (bucket.prev != pHead && bucket.prev.size + bucket.size - rest <= MAX_BUCKET_SIZE) {
                for (int i = 0; i < skip; i++) {
                    bucket.prev.add(bucket.numArr[i]);
                }

                for (int i = skip + rest; i < bucket.size; i++) {
                    bucket.prev.add(bucket.numArr[i]);
                }

                bucket.prev.next = bucket.next;
                bucket.next.prev = bucket.prev;
            }
            else {
                for (int i = skip + rest; i < bucket.size; i++) {
                    bucket.numArr[i - rest] = bucket.numArr[i];
                }
                bucket.size -= rest;
                bucket.updateMinMax();
            }
        }
    }

    int find(int K) {
        Bucket bucket = pTail.prev;
        int min = INF;
        int max = -1;

        while (K > 0) {
            if (bucket.size >= K) {
                for (int i = bucket.size - K; i < bucket.size; i++) {
                    min = Math.min(min, bucket.numArr[i]);
                    max = Math.max(max, bucket.numArr[i]);
                }
                K = 0;
            } else {
                min = Math.min(min, bucket.min);
                max = Math.max(max, bucket.max);

                K -= bucket.size;
            }
            bucket = bucket.prev;
        }
        return max - min;
    }
}
