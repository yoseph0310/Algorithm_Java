package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class No_4_암호문3 {

    static LinkedList<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = 10;
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());        // 암호문 개수
            st = new StringTokenizer(br.readLine());
            list = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());        // 명령어 개수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String cmd = st.nextToken();
                int x, y, s;

                switch (cmd){
                    case "I":
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());

                        for (int j = 0; j < y; j++) {
                            s = Integer.parseInt(st.nextToken());

                            list.add(x++, s);
                        }

                        break;
                    case "D":
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());

                        for (int j = 0; j < y; j++) {
                            list.remove(x);
                        }

                        break;
                    case "A":
                        y = Integer.parseInt(st.nextToken());

                        for (int j = 0; j < y; j++) {
                            s = Integer.parseInt(st.nextToken());

                            list.add(s);
                        }
                        break;
                }
            }

            System.out.print("#" + t + " ");
            for (int i = 0; i < 10; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }
}
