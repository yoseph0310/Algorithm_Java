package SWEA.CodeBattle.실전실습.No_3_항공노선관리;

import java.util.PriorityQueue;

public class UserSolution_Practice {

    static final int INF = Integer.MAX_VALUE;
    static int N;

    // 시간 관련
    static int[][][] graphTime;
    static int[] startAirportEdgeSize;
    static int[][] distTime;

    // 비용 관련
    static int[][] graphPrice;
    static int[] distPrice;

    void init(int n) {
        N = n;

        // add 호출 최대 횟수 30,000. 간선이 최대 30,000개 생길수 있음. 넉넉히 61*61.
        graphTime = new int[61][61*61][3];
        startAirportEdgeSize = new int[61];
        distTime = new int[61][2];

        graphPrice = new int[61][61];
        distPrice = new int[61];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                graphPrice[i][j] = INF;
            }
        }
    }

    void add(int mStartAirport, int mEndAirport, int mStartTime, int mTravelTime, int mPrice) {
        int size = startAirportEdgeSize[mStartAirport];
        graphTime[mStartAirport][size][0] = mEndAirport;
        graphTime[mStartAirport][size][1] = mStartTime;
        graphTime[mStartAirport][size][2] = mTravelTime;
        startAirportEdgeSize[mStartAirport]++;

        graphPrice[mStartAirport][mEndAirport] = Math.min(graphPrice[mStartAirport][mEndAirport], mPrice);
    }

    int minTravelTime(int mStartAirport, int mEndAirport, int mStartTime) {
        PriorityQueue<TimeNode> pq = new PriorityQueue<>();

        pq.add(new TimeNode(0, mStartTime, mStartAirport));
        for (int i = 0; i <= N; i++) {
            distTime[i][0] = INF;
            distTime[i][1] = INF;
        }
        distTime[mStartAirport][0] = 0;
        distTime[mStartAirport][1] = mStartTime;

        while (!pq.isEmpty()) {
            TimeNode cur = pq.poll();

            int curDays = cur.days;
            int curHours = cur.hours;
            int curStartAirport = cur.startAirport;

            if (curDays > distTime[curStartAirport][0] || curHours > distTime[curStartAirport][1]) continue;

            if (curStartAirport == mEndAirport) {
                return curDays * 24 + curHours - mStartTime;
            }

            for (int i = 0; i < startAirportEdgeSize[curStartAirport]; i++) {
                int[] next = graphTime[curStartAirport][i];

                int nextDays = curDays;
                if (curHours > next[1]) {
                    nextDays++;
                }

                int nextHours = next[1] + next[2];
                if (nextHours >= 24) {
                    nextHours -= 24;
                    nextDays++;
                }

                if (nextDays < distTime[next[0]][0] || (nextDays == distTime[next[0]][0]) && nextHours < distTime[next[0]][1]) {
                    distTime[next[0]][0] = nextDays;
                    distTime[next[0]][1] = nextHours;
                    pq.add(new TimeNode(nextDays, nextHours, next[0]));
                }
            }

        }


        return -1;
    }

    int minPrice(int mStartAirport, int mEndAirport) {
        PriorityQueue<PriceNode> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.price, n2.price));

        pq.add(new PriceNode(mStartAirport, 0));
        for (int i = 0; i <= N; i++) {
            distPrice[i] = INF;
        }
        distPrice[mStartAirport] = 0;

        while (!pq.isEmpty()) {
            PriceNode cur = pq.poll();

            int idx = cur.idx;
            int price = cur.price;

            if (distPrice[idx] < price) continue;

            if (idx == mEndAirport) {
                return price;
            }

            for (int i = 0; i < graphPrice[idx].length; i++) {
                if (graphPrice[idx][i] == INF) continue;

                int nextPrice = price + graphPrice[idx][i];
                if (nextPrice < distPrice[i]) {
                    distPrice[i] = nextPrice;
                    pq.add(new PriceNode(i, nextPrice));
                }

            }
        }

        return -1;
    }

    static class TimeNode implements Comparable<TimeNode>{
        int days, hours, startAirport;

        public TimeNode(int days, int hours, int startAirport) {
            this.days = days;
            this.hours = hours;
            this.startAirport = startAirport;
        }

        @Override
        public int compareTo(TimeNode t) {
            if (t.days == this.days) return this.hours - t.hours;
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
