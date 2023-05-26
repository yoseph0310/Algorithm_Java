package Programmers.LV2;
import java.util.*;

public class 할인_행사 {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 원하는 물품과 수량을 담는 맵
        // 10일 동안 할인하는 물품과 수량을 체크하는 맵
        Map<String, Integer> wantMap = new HashMap<>();
        Map<String, Integer> disMap = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // want의 물품을 key로 삼아 수량이 일치하지 않으면 다음 반복을 하는 구조로 함수를 짠다.
        for (int i = 0; i < 10; i++) {
            disMap.put(discount[i], disMap.getOrDefault(discount[i], 0) + 1);
        }
        if (isPossible(wantMap, disMap)) answer++;


        int maxIdx = discount.length - 10;
        for (int i = 1; i <= maxIdx; i++) {
            String deleteItem = discount[i - 1];
            disMap.put(deleteItem, disMap.get(deleteItem) - 1);

            String addItem = discount[i - 1 + 10];
            disMap.put(addItem, disMap.getOrDefault(addItem, 0) + 1);

            // 모두 할인을 받을 수 있는지 확인하는 함수
            if (isPossible(wantMap, disMap)) {
                answer++;
            }

        }

        return answer;
    }

    boolean isPossible(Map<String, Integer> wantMap, Map<String, Integer> disMap) {

        // wantMap의 key가 disMap에 있으면 수량을 비교하고 다르면 false 반환
        for (String key : wantMap.keySet()) {

            if (!disMap.containsKey(key) || wantMap.get(key) > disMap.get(key)) {
                return false;
            }
        }

        return true;
    }
}
