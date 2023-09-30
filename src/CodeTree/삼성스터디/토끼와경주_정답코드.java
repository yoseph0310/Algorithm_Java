package CodeTree.삼성스터디;

import java.io.*;
import java.util.*;

public class 토끼와경주_정답코드 {

    public static class Rabbit{
        int p_id, r, c, d, jump_cnt;
        long score;
        public Rabbit(int p_id, int r, int c, int d, int jump_cnt, long score){
            this.p_id = p_id;
            this.r = r;
            this.c = c;
            this.d = d;
            this.jump_cnt = jump_cnt;
            this.score = score;
        }
    }

    static int Q, N, M, P;
    static PriorityQueue<Rabbit> pq;
    static HashMap<Integer, Rabbit> rabbits = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pq = new PriorityQueue<>(new Comparator<Rabbit>(){
            @Override
            public int compare(Rabbit r1, Rabbit r2) {
                if (r1.jump_cnt != r2.jump_cnt)
                    return r1.jump_cnt - r2.jump_cnt;
                else if (r1.r + r1.c != r2.r + r2.c)
                    return (r1.r + r1.c) - (r2.r + r2.c);
                else if (r1.r != r2.r)
                    return r1.r - r2.r;
                else if (r1.c != r2.c)
                    return r1.c - r2.c;
                return r1.p_id - r2.p_id;
            }
        });
        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());
            switch(cmd){
                case 100:
                    N = Integer.parseInt(st.nextToken());
                    M = Integer.parseInt(st.nextToken());
                    P = Integer.parseInt(st.nextToken());
                    for(int j = 0; j < P; j ++){
                        int p_id = Integer.parseInt(st.nextToken());
                        int d = Integer.parseInt(st.nextToken());

                        Rabbit r = new Rabbit(p_id, 0, 0, d, 0, 0);
                        pq.offer(r);
                        rabbits.put(p_id, r);
                    }
                    break;
                case 200:
                    int k = Integer.parseInt(st.nextToken());
                    int s = Integer.parseInt(st.nextToken());
                    race(k, s);
                    break;
                case 300:
                    int p_id = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    addDist(p_id, l);
                    break;
                case 400:
                    System.out.println(getHighScore());
                    return;
            }
        }
    }

    public static void race(int k, int s){
        long total = 0;
        PriorityQueue<Rabbit> round = new PriorityQueue<>(new Comparator<Rabbit>(){
            @Override
            public int compare(Rabbit o1, Rabbit o2){
                if((o1.r + o1.c) != (o2.r + o2.c)) return (o2.r + o2.c) - (o1.r + o1.c);
                else if(o1.r != o2.r) return o2.r - o1.r;
                else if(o1.c != o2.c) return o2.c - o1.c;
                else return o2.p_id - o1.p_id;
            }
        });

        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < k; i ++){
            Rabbit r = selectRabbit();
            set.add(r.p_id);
            total += selectMoveRabbit();
        }

        for(int s1 : set){
            round.offer(rabbits.get(s1));
        }

        for(int key : rabbits.keySet()){
            Rabbit r = rabbits.get(key);
            r.score += total;
        }

        int p_id = round.poll().p_id;
        Rabbit r = rabbits.get(p_id);
        r.score += s;
        rabbits.put(p_id, r);
    }

    public static long selectMoveRabbit(){
        Rabbit r = pq.poll();
        PriorityQueue<int[]> selectedMove = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] != o2[0]) return o2[0] - o1[0];
                else if(o1[1] != o2[1]) return o2[1] - o1[1];
                else return o2[2] - o1[2];
            }
        });

        int row_move_cnt = r.d;
        int col_move_cnt = r.d;

        // 아래로 이동
        int nr = (r.r + row_move_cnt) % (2 * (N - 1));
        if(nr > N - 1)
            nr = N - 1 - (nr % (N - 1));
        selectedMove.offer(new int[] { nr + r.c, nr, r.c });

        // 위로 이동
        nr = Math.abs(r.r - row_move_cnt) % (2 * (N - 1));
        if(nr > N - 1)
            nr = N - 1 - (nr % (N - 1));
        selectedMove.offer(new int[] { nr + r.c, nr, r.c });

        // 오른쪽 이동
        int nc = (r.c + col_move_cnt) % (2 * (M - 1));
        if(nc > M - 1)
            nc = M - 1 - (nc % (M - 1));
        selectedMove.offer(new int[] { r.r + nc, r.r, nc });

        // 아래 이동
        nc = Math.abs(r.c - col_move_cnt) % (2 * (M - 1));
        if(nc > M - 1)
            nc = M - 1 - (nc % (M - 1));
        selectedMove.offer(new int[] { r.r + nc, r.r, nc });

        int[] next_pos = selectedMove.poll();

        r.r = next_pos[1];
        r.c = next_pos[2];
        long score = next_pos[1] + 1 + next_pos[2] + 1;
        r.score -= score;
        r.jump_cnt ++;
        pq.offer(r);

        return score;
    }

    public static Rabbit selectRabbit(){
        Rabbit r = pq.peek();
        return r;
    }

    public static void addDist(int pid, int L) {
        Rabbit cur = rabbits.get(pid);
        cur.d *= L;
        rabbits.put(pid, cur);
    }

    public static long getHighScore(){
        long ret = 0;
        for(int key : rabbits.keySet()){
            Rabbit cur = rabbits.get(key);
            ret = Math.max(ret, cur.score);
        }
        return ret;
    }
}
