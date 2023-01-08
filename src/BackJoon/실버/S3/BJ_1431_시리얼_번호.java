package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BJ_1431_시리얼_번호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> guitarList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String guitar = br.readLine();

            guitarList.add(guitar);
        }

        Collections.sort(guitarList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                } else if (o1.length() == o2.length()) {
                    if (add(o1) == add(o2)) {
                        return o1.compareTo(o2);
                    } else {
                        return Integer.compare(add(o1), add(o2));
                    }
                } else {
                    return 1;
                }
            }
        });

        for (String guitar: guitarList) {
            System.out.println(guitar);
        }
    }

    static int add(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                sum += s.charAt(i) - '0';
            }
        }

        return sum;
    }
}
