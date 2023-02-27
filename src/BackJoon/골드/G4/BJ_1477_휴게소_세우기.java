package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1477_휴게소_세우기 {

    static int N, M, L;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        list.add(0);
        list.add(L);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        int left = 0;
        int right = L;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canMake(mid)) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(left);
    }

    static boolean canMake(int mid) {
        int cnt = 0;

        for (int i = 1; i < list.size(); i++) {
            cnt += (list.get(i) - list.get(i - 1) - 1) / mid;
        }

        System.out.println(cnt);
        if (cnt > M) return true;
        return false;
    }
}
