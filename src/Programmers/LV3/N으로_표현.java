package Programmers.LV3;

import java.util.*;

public class N으로_표현 {
    /*
        - N 을 가지고 (), 사칙연산을 사용하여 number를 만드는데 필요한 N의 개수중 최소개수를 반환
        - 나누기 연산에서 나머지는 무시한다.
    */
    class Solution {
        public int solution(int N, int number) {
            List<Set<Integer>> cntList = new ArrayList<>();

            for (int i = 0; i < 9; i++) {
                cntList.add(new HashSet<>());
            }

            cntList.get(1).add(N);

            for (int i = 2; i < 9; i++) {
                Set<Integer> cntSet = cntList.get(i);

                for (int j = 1; j <= i; j++) {
                    Set<Integer> preSet = cntList.get(j);
                    Set<Integer> postSet = cntList.get(i - j);

                    for (int preNum : preSet) {
                        for (int postNum : postSet) {
                            cntSet.add(preNum + postNum);
                            cntSet.add(preNum - postNum);
                            cntSet.add(preNum * postNum);

                            if (preNum != 0 && postNum != 0) cntSet.add(preNum / postNum);
                        }
                    }
                }

                cntSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }

            for (Set<Integer> sub : cntList) {
                if (sub.contains(number)) return cntList.indexOf(sub);
            }


            return -1;
        }
    }
}
