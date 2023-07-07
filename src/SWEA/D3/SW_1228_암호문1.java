package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_1228_암호문1 {

    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());        // 암호문 길이
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();
            list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());        // 명령의 개수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String c = st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int commandCnt = Integer.parseInt(st.nextToken());

                for (int j = 0; j < commandCnt; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    list.add(x++, num);
                }
            }

            sb.append("#").append(t).append(" ");
            System.out.print(sb);
            print_arr(list);
        }
    }

    static void print_arr(List<Integer> list) {
        for (int i = 0; i < 10; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}
