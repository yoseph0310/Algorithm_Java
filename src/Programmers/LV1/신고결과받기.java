package Programmers.LV1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class 신고결과받기 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        for (int num: solution(id_list, report, k)) {
            System.out.print(num + " ");
        }
    }

    public static int[] solution(String[] id_list, String[] report, int k){
        int[] answer = new int[id_list.length];
        HashMap<String, HashSet<String>> reporterInfoMap = new HashMap<>();
        HashMap<String, Integer> reportedCountInfoMap = new HashMap<>();
        HashSet<String> reportSet = new HashSet<>(Arrays.asList(report));       // 같은 사람이 한 사람을 계속 신고하면 1회로 간주

        for (String reportInfo: reportSet) {
            String reporter = reportInfo.split(" ")[0];
            String reported = reportInfo.split(" ")[1];

            reporterInfoMap.putIfAbsent(reporter, new HashSet<>(){{
                add(reported);
            }});
            reporterInfoMap.get(reporter).add(reported);
            reportedCountInfoMap.put(reported, reportedCountInfoMap.getOrDefault(reported, 0) + 1);
        }

        for (String reported: reportedCountInfoMap.keySet()) {
            int reportedCnt = reportedCountInfoMap.get(reported);

            if (reportedCnt >= k) {
                for (int i = 0; i < id_list.length; i++) {
                    if (reporterInfoMap.containsKey(id_list[i]) && reporterInfoMap.get(id_list[i]).contains(reported)) {
                        answer[i]++;
                    }
                }
            }
        }
        return answer;
    }
}
