package BackJoon.실버.S5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_10867 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> hs = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hs.add(Integer.parseInt(st.nextToken()));
        }
        ArrayList<Integer> al = new ArrayList<>(hs);
        Collections.sort(al);

        for (int num: al) {
            bw.write(num+" ");
        }
        bw.flush();
        bw.close();
    }
}
