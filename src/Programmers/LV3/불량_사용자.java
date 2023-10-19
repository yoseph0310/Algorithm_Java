package Programmers.LV3;
import java.util.*;

public class 불량_사용자 {
    class Solution {

        static String[] userIds;
        static String[] bannedIds;
        static HashSet<HashSet<String>> result = new HashSet<>();

        public int solution(String[] user_id, String[] banned_id) {
            userIds = user_id;
            bannedIds = banned_id;

            dfs(new HashSet<>(), 0);

            return result.size();
        }

        static void dfs(HashSet<String> set, int depth) {
            if (depth == bannedIds.length) {
                result.add(set);
                return;
            }

            for (int i = 0; i < userIds.length; i++) {
                if (set.contains(userIds[i])) {
                    continue;
                }

                if (check(userIds[i], bannedIds[depth])) {
                    set.add(userIds[i]);
                    dfs(new HashSet<>(set), depth + 1);
                    set.remove(userIds[i]);
                }
            }
        }

        static boolean check(String userId, String bannedId) {
            if (userId.length() != bannedId.length()) return false;

            boolean match = true;
            for (int i = 0; i < userId.length(); i++) {
                if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                    match = false;
                    break;
                }
            }

            return match;
        }
    }
}
