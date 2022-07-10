package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            LinkedList<int[]> q = new LinkedList<>();   // queue 로 활용
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                q.offer(new int[] {i, Integer.parseInt(st.nextToken())});       // {초기 위치, 중요도}
            }

            int cnt = 0;        // 출력 횟수

            while (!q.isEmpty()){
                int[] front = q.poll(); // 가장 첫 원소
                boolean isMax = true;   // front 원소가 가장 큰 원소인지를 판단하는 변수

                // 큐에 남아있는 원소들과 중요도 비교
                for (int i = 0; i < q.size(); i++) {
                    if(front[1] < q.get(i)[1]){
                        q.offer(front);
                        for (int j = 0; j < i; j++) {
                            q.offer(q.poll());
                        }

                        isMax = false;
                        break;
                    }
                }

                if(!isMax){
                    continue;
                }
                cnt++;
                if(front[0] == M){
                    break;
                }
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
        bw.close();
    }
}
