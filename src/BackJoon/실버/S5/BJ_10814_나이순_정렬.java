package BackJoon.실버.S5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10814_나이순_정렬 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        StringBuilder[] memberList = new StringBuilder[201];

        for (int i = 0; i < memberList.length; i++) {
            memberList[i] = new StringBuilder();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            memberList[age].append(age).append(' ').append(name).append('\n');
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder member: memberList) {
            sb.append(member);
        }

        System.out.println(sb);
    }
}
