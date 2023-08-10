package SWEA.CodeBattle.실전실습.No_3_항공노선관리;

import java.util.PriorityQueue;

/**
 * Dynamic Shortest Path 문제
 * 변화하는 그래프에서 최단 경로를 찾아내는 문제
 * 시간 최단경로, 비용 최단 경로
 */
public class UserSolution {

    static final int INF = Integer.MAX_VALUE;
    static int N;

    // 비용 관련
    static int[][] graphPrice;
    static int[] distP;

    // 시간 관련
    static int[][][] graphTime;
    static int[] graphTimeSize;
    static int[][] distT;

    /**
     * 공항은 N 개 이며 0 ~ N-1 이하 번호로 주어진다. 인덱스로 활용한다.
     *
     * @param n             : 공항의 수 (3 <= n <= 60)
     */
    public void init(int n) {
        N = n;

        graphPrice = new int[61][61];
        distP = new int[61];

        graphTime = new int[61][61*61][3];          // 0 : endAirPort  1 : startTime  2 : mTravelTime
        graphTimeSize = new int[61];
        distT = new int[61][2];

        for (int i = 0; i <= N; i++) {
            graphTimeSize[i] = 0;
            for (int j = 0; j <= N; j++) {
                graphPrice[i][j] = INF;
            }
        }

    }

    /**
     * mStartAirport -> mEndAirport 공항으로 가는 항공 노선 추가
     *
     * 출발 시각 mStartTime. 소요시간 mTravelTime, 티켓 가격 mPrice
     *
     * 두 공항 사이에 여러 개의 항공 노선이 존재할 수 있음에 유의하자.
     * mStartAirport 와 mEndAirport 는 서로 다름이 보장됨.
     *
     * @param mStartAirport         : 출발 공항 번호 (0 <= mStartAirport <= N-1)
     * @param mEndAirport           : 도착 공항 번호 (0 <= mEndAirport <= N-1)
     * @param mStartTime            : 출발 시각 (0 <= mStartTime <= 23)
     * @param mTravelTime           : 비행 소요 시간 (1 <= mTravelTime <= 23)
     * @param mPrice                : 티켓 가격
     */
    public void add(int mStartAirport, int mEndAirport, int mStartTime, int mTravelTime, int mPrice) {
        // 공항(노드)과 공항 간 노선(간선) 정보를 인접리스트로 담아둔다. -> 최소 시간 전용, 최소 비용 전용 2개
        // 최소 시간 그래프에 노선 추가 - A, B 사이에 여러 노선이 있을 수 있다. 따라서 각 공항의 노선수를 Cnt 하는 배열을 사용한다.
        int size = graphTimeSize[mStartAirport];
        graphTime[mStartAirport][size][0] = mEndAirport;
        graphTime[mStartAirport][size][1] = mStartTime;
        graphTime[mStartAirport][size][2] = mTravelTime;
        graphTimeSize[mStartAirport]++;

        // 최소 비용 그래프에 노선 추가
        graphPrice[mStartAirport][mEndAirport] = Math.min(graphPrice[mStartAirport][mEndAirport], mPrice);

//        print_graph(graphPrice);
    }

    /**
     * mStartTime 에 일정을 시작하여 mStartAirPort 공항에서 mEndAirport 공항 까지 도착하는 최소 소요시간 반환
     *
     * mStartAirport 에서 mStartTime 시에 출발하여 mEndAirport 공항에 갈 수 없으면 -1 반환
     *
     * 출발, 도착 공항이 서로 다름이 보장 됨
     *
     * @param mStartAirport         : 출발 공항 번호 (0 <= mStartAirport <= N-1)
     * @param mEndAirport           : 도착 공항 번호 (0 <= mEndAirport <= N-1)
     * @param mStartTime            : 출발 시각 (0 <= mStartTime <= 23)
     *
     * @return                      : 최소 이동 시간
     */
    public int minTravelTime(int mStartAirport, int mEndAirport, int mStartTime) {

        PriorityQueue<TimeNode> pq  = new PriorityQueue<>();

        pq.add(new TimeNode(0, mStartTime, mStartAirport));
        for (int i = 0; i < N; i++) {
            distT[i][0] = INF;
            distT[i][1] = INF;
        }

        distT[mStartAirport][0] = 0;                // 소요 일 수
        distT[mStartAirport][1] = mStartTime;       // 소요 시간. mStart 부터 계산

        while (!pq.isEmpty()) {
            TimeNode curNode = pq.poll();

            int curDays = curNode.days;
            int curHours = curNode.hour;
            int curStartAirport = curNode.startAirPort;

            // 현재 노드의 시간값들이 모두 dist 에 기록된 최소값들보다 더 크다면 볼 필요가 없다.
            if (curDays > distT[curStartAirport][0] || curHours > distT[curStartAirport][1]) continue;

            if (curStartAirport == mEndAirport) {
                return curDays * 24 + curHours - mStartTime;
            }

            for (int i = 0; i < graphTimeSize[curStartAirport]; i++) {
                // 0 : 목적지 공항  1 : 출발 시간  2: 소요 시간
                int[] next = graphTime[curStartAirport][i];

                // 현재 시간보다 다음 확인해볼 노선의 시간보다 작으면 다음날로 표기.
                int nextDays = curDays;
                if (curHours > next[1]) nextDays++;

                // 다음 확인해볼 노선의 출발시간, 소요시간이 24보다 크면 다음날로 표기
                int nextHour = next[1] + next[2];
                if (nextHour >= 24) {
                    nextHour -= 24;
                    nextDays++;
                }

                if (nextDays < distT[next[0]][0] || (nextDays == distT[next[0]][0]) && nextHour < distT[next[0]][1]) {
                    distT[next[0]][0] = nextDays;
                    distT[next[0]][1] = nextHour;
                    pq.add(new TimeNode(nextDays, nextHour, next[0]));
                }
            }
        }

        return -1;
    }

    /**
     * mStartAirport 공항에서 mEndAirport 공항으로 가는 최소 비용을 달러 단위로 반환한다.
     * 비용은 항공 노선들의 티켓 가격의 총합
     *
     * 출발 공항에서 도착 공항으로 갈 수 없으면 -1 반환
     *
     * 출발, 도착 공항이 서로 다름이 보장됨
     *
     * @param mStartAirport         : 출발 공항 번호 (0 <= mStartAirport <= N-1)
     * @param mEndAirport           : 도착 공항 번호 (0 <= mEndAirport <= N-1)
     *
     * @return                      : 최소 비용
     */
    public int minPrice(int mStartAirport, int mEndAirport) {

        PriorityQueue<PriceNode> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.price, n2.price));

        pq.add(new PriceNode(mStartAirport, 0));
        for (int i = 0; i <= N; i++) {
            distP[i] = INF;
        }
        distP[mStartAirport] = 0;

        while (!pq.isEmpty()) {
            PriceNode curNode = pq.poll();

            int idx = curNode.idx;
            int price = curNode.price;

            if (distP[idx] < price) continue;

            if (idx == mEndAirport) {
                return price;
            }

            for (int i = 0; i < graphPrice[mStartAirport].length; i++) {
                if (graphPrice[idx][i] == INF) continue;

                int nextPrice = price + graphPrice[idx][i];
                if (nextPrice < distP[i]) {
                    distP[i] = nextPrice;
                    pq.add(new PriceNode(i, nextPrice));
                }
            }
        }

        return -1;
    }

    static class TimeNode implements Comparable<TimeNode> {
        int days, hour, startAirPort;

        public TimeNode(int days, int hour, int startAirPort) {
            this.days = days;
            this.hour = hour;
            this.startAirPort = startAirPort;
        }

        @Override
        public int compareTo(TimeNode t) {
            if (t.days == this.days) return this.hour - t.hour;
            else return this.days - t.days;
        }
    }

    static class PriceNode {
        int idx, price;

        public PriceNode(int idx, int price) {
            this.idx = idx;
            this.price = price;
        }
    }
}
