package BackJoon.실버.S5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10815 {

    static int [] card;
    static int [] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        card = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(card);

        int start = 0;
        int end = card.length - 1;
        for (int i = 0; i < M; i++) {
            sb.append(search(arr[i], card, start, end)).append(' ');
        }

        System.out.println(sb);
    }

    static int search(int n, int [] card, int start, int end){
        int mid = (start + end) / 2;
        if (start > end){
            return 0;
        }
        if (n == card[mid]){
            return 1;
        }
        else if (n > card[mid]){
            return search(n, card, mid + 1, end);
        }
        else {
            return search(n, card, start, mid - 1);
        }
    }
}
