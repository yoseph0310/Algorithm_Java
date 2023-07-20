package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class No_5_수열_편집 {

    static LinkedList<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());           // 수열의 길이
            int M = Integer.parseInt(st.nextToken());           // 추가 횟수
            int L = Integer.parseInt(st.nextToken());           // 출력 인덱스 번호

            list = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();

                int idx, data;
                switch (cmd){
                    case "I":
                        idx = Integer.parseInt(st.nextToken());
                        data = Integer.parseInt(st.nextToken());

                        list.add(idx, data);

                        break;
                    case "D":
                        idx = Integer.parseInt(st.nextToken());

                        list.remove(idx);

                        break;
                    case "C":
                        idx = Integer.parseInt(st.nextToken());
                        data = Integer.parseInt(st.nextToken());

                        list.set(idx, data);

                        break;
                }
            }

            int ans = list.size() > L ? list.get(L) : -1;
            System.out.println("#" + t + " " + ans);
        }
    }


}
