package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SW_14361_숫자가_같은_배수 {

    static char[] arr;
    static char[] out;
    static boolean[] visited;
    static boolean check;
    static int len;
    static ArrayList<Integer> num_list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String input = br.readLine();
            len = input.length();

            arr = new char[len];
            out = new char[len];
            visited = new boolean[len];
            num_list = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                arr[i] = input.charAt(i);
            }

            if (len == 1) {
                System.out.println("#" + t + " impossible");
                continue;
            }

            perm(0);

            if (!check) System.out.println("#" + t + " impossible");
            else System.out.println("#" + t + " possible");

        }
    }

    static void perm(int depth) {
        if (depth == len) {
//            print(out, r);
            String s = "";
            for (int i = 0; i < len; i++) {
                s += out[i];
            }

            if (out[0] != '0'){
                if (!num_list.contains(Integer.parseInt(s))) num_list.add(Integer.parseInt(s));
            }

            int num = num_list.get(0);
            for (int i = 1; i < num_list.size(); i++) {
                if (num_list.get(i) % num == 0) {
                    check = true;
                }
            }

            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                perm(depth+1);
                visited[i] = false;
            }
        }
    }

}
