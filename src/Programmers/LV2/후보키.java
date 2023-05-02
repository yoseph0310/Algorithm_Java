package Programmers.LV2;
import java.util.*;

public class 후보키 {
    String[][] g_relation;
    HashSet<String> set;

    public int solution(String[][] relation) {
        g_relation = relation;
        set = new HashSet<>();

        for (int i = 1; i <= relation[0].length; i++) {
            boolean[] selected = new boolean[relation[0].length];
            dfs(0, 0, i, selected);
        }

        return set.size();
    }

    void dfs(int idx, int cnt, int max, boolean[] selected) {
        if (cnt == max) {
            String cols = "";

            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    cols += i;
                }
            }

            if (isPossible(cols, selected)) {
                set.add(cols);
            }

            return;
        }

        if (idx >= selected.length) return;

        selected[idx] = true;
        dfs(idx + 1, cnt + 1, max, selected);

        selected[idx] = false;
        dfs(idx + 1, cnt, max, selected);
    }

    boolean isPossible(String cols, boolean[] selected) {
        // 최소성 확인
        for (String s : set) {
            boolean flag = true;
            for (int i = 0; i < s.length(); i++) {
                if (!cols.contains(s.charAt(i)+"")) {
                    flag = false;
                }
            }

            if (flag) return false;
        }

        // 중복값을 확인할 컬럼 정하기
        HashSet<String> values = new HashSet<>();
        int[] col_name = new int[cols.length()];
        int idx = 0;

        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                col_name[idx++] = i;
            }
        }

        // 중복값 찾기
        String value = "";
        for (int i = 0; i < g_relation.length; i++) {
            value = "";
            for (int j = 0; j < col_name.length; j++) {
                value += g_relation[i][col_name[j]];
            }

            if (values.contains(value)) {
                return false;
            } else {
                values.add(value);
            }
        }

        return true;
    }
}
