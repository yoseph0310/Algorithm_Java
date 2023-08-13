package SWEA.CodeBattle.실전실습.No_4_최대최소값찾기;

public class UserSolution_2 {

    static final int MAX_BUCKET_SIZE = 448;
    static final int INF = 100_000_001;

    int poolLastIdx;
    static Bucket[] pool;
    static Bucket pHead, pTail;


    static class Bucket {
        int size, minNum, maxNum;
        int[] numArr;
        Bucket prev, next;

        public Bucket() {
            this.numArr = new int[MAX_BUCKET_SIZE];
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

        public void updateMinMax() {
            minNum = INF;
            maxNum = -1;

            for (int i = 0; i < size; i++) {
                minNum = Math.min(minNum, numArr[i]);
                maxNum = Math.max(maxNum, numArr[i]);
            }
        }
    }

    public void init(int N, int[] mValue) {
        poolLastIdx = 0;
        pool = new Bucket[447];
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new Bucket();
        }

        // 연결리스트 헤드, 테일 정보 초기화
        pHead = new Bucket();
        pTail = new Bucket();

        pool[0].init(pHead, pTail);

        pHead.next = pool[0];
        pTail.prev = pool[0];

        add(N, mValue);
    }

    public void add(int M, int[] mValue) {
        for (int i = 0; i < M; i++) {
            // 마지막 버킷의 크기가 버킷 최대 용적을 넘으면
            if (pTail.prev.size >= MAX_BUCKET_SIZE) {
                // 다음 버킷 연결 정보 생성
                pool[++poolLastIdx].init(pTail.prev, pTail);

                // tail 의 기존 이전 노드의 다음 노드로 새노드로, tail 의 새 이전 노드로 새 노드로 링크
                pTail.prev.next = pool[poolLastIdx];
                pTail.prev = pool[poolLastIdx];
            }

            pTail.prev.add(mValue[i]);
        }

    }

    public void erase(int mFrom, int mTo) {
        int skip = mFrom - 1;
        int rest = mTo - mFrom + 1;
        Bucket bucket = pHead.next;

        // 삭제하려는 범위의 시작점이 버킷 사이즈보다 크거나 같다면
        while (skip >= bucket.size) {
            skip -= bucket.size;
            bucket = bucket.next;
        }

        // 삭제해야할 수의 개수가 0보다 크고 버킷 크기에서 스킵한 개수를 뺀거보다도 아직도 남아있다면
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

    public int find(int K) {
        Bucket bucket = pTail.prev;
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

}
