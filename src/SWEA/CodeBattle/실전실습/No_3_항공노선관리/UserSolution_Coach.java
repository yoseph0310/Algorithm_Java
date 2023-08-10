package SWEA.CodeBattle.실전실습.No_3_항공노선관리;

class Heap {
    int[][] data;
    int size;

    public Heap() {
        data = new int[60000][3];
        size = 0;
    }

    int compare(int i, int j) {
        if (data[i][0] < data[j][0]) {
            return -1;
        } else if (data[i][0] > data[j][0]) {
            return 1;
        } else if (data[i][1] < data[j][1]) {
            return -1;
        } else if (data[i][1] > data[j][1]) {
            return 1;
        } else {
            return Integer.compare(data[i][2], data[j][2]);
        }
    }

    void swap(int i, int j) {
        int[] temp = {data[i][0], data[i][1], data[i][2]};
        data[i][0] = data[j][0];
        data[i][1] = data[j][1];
        data[i][2] = data[j][2];
        data[j][0] = temp[0];
        data[j][1] = temp[1];
        data[j][2] = temp[2];
    }

    void push(int a, int b, int c) {
        data[size][0] = a;
        data[size][1] = b;
        data[size][2] = c;
        size++;
        int cur = size - 1;
        while (cur > 0) {
            int parent = (cur - 1) / 2;
            if (compare(cur, parent) < 0) {
                swap(cur, parent);
                cur = parent;
            } else {
                break;
            }
        }
    }

    void pop() {
        size--;
        data[0][0] = data[size][0];
        data[0][1] = data[size][1];
        data[0][2] = data[size][2];
        int cur = 0;
        while (cur * 2 + 1 < size) {
            int child = cur * 2 + 1;
            if (child + 1 < size && compare(child + 1, child) < 0) {
                child++;
            }
            if (compare(child, cur) < 0) {
                swap(child, cur);
                cur = child;
            } else {
                break;
            }
        }
    }

    int[] top() {
        return data[0];
    }

    boolean empty() {
        return size == 0;
    }

    void clear() {
        size = 0;
    }
}

class UserSolution_Coach {
    int[][][] graph_time;
    int[] graph_time_size;
    int[][] graph_price;
    int[][] dist1;
    int[] dist2;
    int n;
    Heap pq;

    UserSolution_Coach() {
        graph_time = new int[66][30300][3];
        graph_time_size = new int[66];
        graph_price = new int[66][66];
        dist1 = new int[66][2];
        dist2 = new int[66];
        pq = new Heap();
    }

    public void init(int N) {
        n = N;
        for (int i = 0; i < n; i++) {
            graph_time_size[i] = 0;
            for (int j = 0; j < n; j++) {
                graph_price[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public void add(int mStartAirport, int mEndAirport, int mStartTime, int mTravelTime, int mPrice) {
        int size = graph_time_size[mStartAirport];
        graph_time[mStartAirport][size][0] = mEndAirport;
        graph_time[mStartAirport][size][1] = mStartTime;
        graph_time[mStartAirport][size][2] = mTravelTime;
        graph_time_size[mStartAirport]++;

        graph_price[mStartAirport][mEndAirport] = Math.min(graph_price[mStartAirport][mEndAirport], mPrice);
    }

    public int minTravelTime(int mStartAirport, int mEndAirport, int mStartTime) {
        pq.push(0, mStartTime, mStartAirport);
        for (int i = 0; i < n; i++) {
            dist1[i][0] = Integer.MAX_VALUE;
            dist1[i][1] = Integer.MAX_VALUE;
        }
        dist1[mStartAirport][0] = 0;
        dist1[mStartAirport][1] = mStartTime;
        while (!pq.empty()) {
            int[] cur = {pq.top()[0], pq.top()[1], pq.top()[2]};
            pq.pop();
            if (cur[0] != dist1[cur[2]][0] || cur[1] != dist1[cur[2]][1]) {
                continue;
            }
            if (cur[2] == mEndAirport) {
                pq.clear();
                return cur[0] * 24 + cur[1] - mStartTime;
            }
            for (int i = 0; i < graph_time_size[cur[2]]; i++) {
                int[] next = graph_time[cur[2]][i];
                int nextDate = cur[0];
                if (cur[1] > next[1]) {
                    nextDate++;
                }

                int nextTime = next[1] + next[2];
                if (nextTime >= 24) {
                    nextTime -= 24;
                    nextDate++;
                }

                if (nextDate < dist1[next[0]][0] || (nextDate == dist1[next[0]][0]) && nextTime < dist1[next[0]][1]) {
                    dist1[next[0]][0] = nextDate;
                    dist1[next[0]][1] = nextTime;
                    pq.push(nextDate, nextTime, next[0]);
                }
            }
        }
        return -1;
    }

    public int minPrice(int mStartAirport, int mEndAirport) {
        pq.push(0, mStartAirport, 0);
        for (int i = 0; i < n; i++) {
            dist2[i] = Integer.MAX_VALUE;
        }
        dist2[mStartAirport] = 0;
        while (!pq.empty()) {
            int[] cur = {pq.top()[0], pq.top()[1]};
            pq.pop();
            if (cur[0] != dist2[cur[1]]) {
                continue;
            }
            if (cur[1] == mEndAirport) {
                pq.clear();
                return cur[0];
            }
            for (int i = 0; i < n; i++) {
                if (graph_price[cur[1]][i] == Integer.MAX_VALUE) {
                    continue;
                }
                int next = cur[0] + graph_price[cur[1]][i];
                if (next < dist2[i]) {
                    dist2[i] = next;
                    pq.push(next, i, 0);
                }
            }
        }
        return -1;
    }
}