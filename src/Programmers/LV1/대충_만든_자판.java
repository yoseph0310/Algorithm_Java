package Programmers.LV1;

import java.util.Arrays;
import java.util.HashMap;

public class 대충_만든_자판 {

    static int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        HashMap<Character, Integer> map = new HashMap<>();

        for (String key: keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);

                if (!map.containsKey(c) || i < map.get(c)) {
                    map.put(c, i + 1);
                }
            }
        }

        for (int t = 0; t < targets.length; t++)  {
            String target = targets[t];
            int cnt = 0;
            for (int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);

                if (!map.containsKey(c)) {
                    cnt = 0;
                    break;
                } else {
                    cnt += map.get(c);
                }
            }

            answer[t] = cnt == 0 ? -1 : cnt;
        }

        return answer;
    }

    public static int[] solution2(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        HashMap<Character, Integer> map = new HashMap<>();

        for (int k = 0; k < keymap.length; k++) {
            for (int kci = 0; kci < keymap[k].length(); kci++) {
                char c = keymap[k].charAt(kci);

                if (map.getOrDefault(c, 0) == 0) {
                    map.put(c, kci + 1);
                } else {
                    if ((kci + 1) < map.get(c)) {
                        map.put(c, kci + 1);
                    }
                }
            }
        }

        for (char key : map.keySet()) {
            System.out.print(key + " " + map.get(key));
            System.out.println();
        }

        for (int t = 0; t < targets.length; t++) {
            int cnt = 0;
            for (int tci = 0; tci < targets[t].length(); tci++) {
                char c = targets[t].charAt(tci);

                if (map.getOrDefault(c, 0) == 0) {
                    cnt = 0;
                    break;
                } else {
                    cnt += map.get(c);
                }
            }

            if (cnt == 0) {
                answer[t] = -1;
            } else {
                answer[t] = cnt;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] keymap1 = {"ABACD", "BCEFD"};
        String[] keymap2 = {"AA"};
        String[] keymap3 = {"AGZ", "BSSS"};

        String[] targets1 = {"ABCD","AABB"};
        String[] targets2 = {"B"};
        String[] targets3 = {"ASA","BGZ"};

        System.out.println(Arrays.toString(solution(keymap1, targets1)));
        System.out.println(Arrays.toString(solution(keymap2, targets2)));
        System.out.println(Arrays.toString(solution(keymap3, targets3)));
    }
}
