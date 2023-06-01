package Programmers.LV2;
import java.util.*;

public class 연속된_부분_수열의_합 {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        int partitionSum = sequence[0];

        int n = sequence.length;

        List<SubSequence> subs = new ArrayList<>();
        while (true) {
            if (partitionSum == k) {
                subs.add(new SubSequence(left, right));
            }
            if (left == n && right == n) break;

            if (partitionSum <= k && right < n) {
                right++;
                if (right < n) partitionSum += sequence[right];
            } else {
                if (left < n) partitionSum -= sequence[left];
                left++;
            }
        }

        Collections.sort(subs);

        return new int[]{subs.get(0).left, subs.get(0).right};

    }

    class SubSequence implements Comparable<SubSequence> {
        int left, right, size;

        public SubSequence (int left, int right) {
            this.left = left;
            this.right = right;
            this.size = right - left;
        }

        @Override
        public int compareTo(SubSequence o) {
            if (this.size == o.size) return this.left - o.left;
            return this.size - o.size;
        }
    }
}
