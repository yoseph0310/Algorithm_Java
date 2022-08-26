package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Shuiffle_O_Matic {

    static int t;
    static int n;
    static ArrayList<Integer> list;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         t = Integer.parseInt(br.readLine());
         for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {

                Integer.parseInt(st.nextToken());


            }




        }

        System.out.println(ans);
    }

}
