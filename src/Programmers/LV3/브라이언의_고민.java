package Programmers.LV3;
import java.util.*;

public class 브라이언의_고민 {
    char[] sentence;

    public String solution(String s) {
        sentence = s.toCharArray();

        String invalid = "invalid";

        // 기호 위치 파악
        LinkedHashMap<Character, List<Integer>> charsMap = new LinkedHashMap<>();
        int len = s.length();
        int i;
        char c;

        for (i = 0; i < len; i++) {
            c = sentence[i];

            if (c >= 'a' && c <= 'z') {
                if (!charsMap.containsKey(c)) {
                    charsMap.put(c, new ArrayList<>());
                }
                charsMap.get(c).add(i);
            }
        }

        // 기호의 규칙, 단어의 시작과 끝 인덱스 파악
        List<Word> wordsList = new ArrayList<>();
        int cur_string_startIdx = 0;   // 현재 문자열의 시작 인덱스
        int cur_word_startIdx = 0;         // 현재 단어의 시작 인덱스
        int cur_word_endIdx = 0;           // 현재 단어의 끝 인덱스

        int pre_word_startIdx;          // 이전 단어의 시작 인덱스
        int pre_word_endIdx = -1;            // 이전 단어의 끝 인덱스

        int cur_char_startIdx = -1;          // 현재 기호의 시작 인덱스
        int cur_char_endIdx = -1;            // 현재 기호의 끝 인덱스

        int pre_char_startIdx = -1;          // 이전 기호의 시작 인덱스
        int pre_char_endIdx = -1;            // 이전 기호의 끝 인덱스

        int char_cnt = -1;              // 각 기호의 개수
        int distance;                   // 기호 간 간격
        int rule = -1;                  // 규칙
        boolean isDistance2;

        for (List<Integer> positions : charsMap.values()) {
            char_cnt = positions.size();

            cur_word_startIdx = cur_char_startIdx = positions.get(0);
            cur_word_endIdx = cur_char_endIdx = positions.get(char_cnt - 1);
            isDistance2 = true;

            for (i = 1; i < char_cnt; i++) {
                distance = positions.get(i) - positions.get(i - 1);

                if (distance < 2) return invalid;       // 기호가 연속으로 붙어있다. (예외 1)
                if (distance > 2) {
                    isDistance2 = false;
                    break;
                }
            }

            if (char_cnt >= 3 && !isDistance2) return invalid; // 규칙 1 - 간격 2가 아님 (예외 2)
            if (char_cnt == 1 || char_cnt >= 3) {
                rule = 1;
                cur_word_startIdx--;
                cur_word_endIdx++;

                if (cur_word_startIdx < 0 || len <= cur_word_endIdx) return invalid;    // 규칙 1 - 맨 앞/뒤 글자 없음(예외 3)
            }

            if (char_cnt == 2) rule = isDistance2 ? 0 : 2;  // 규칙 0 => 1 또는 2
            if (pre_char_startIdx < cur_char_startIdx && cur_char_endIdx < pre_char_endIdx) {
                if (rule == 2) return invalid;      // 규칙 2 - 한 단어에 같은 규칙 2번 적용 (예외 4)
                continue;   // 규칙 2 안에 규칙 1
            }

            if (pre_word_endIdx >= cur_word_startIdx) return invalid;      // 단어 범위 안 맞음 (예외 5)

            wordsList.add(new Word(rule, cur_word_startIdx, cur_word_endIdx));
            pre_char_startIdx = cur_char_startIdx;
            pre_char_endIdx = cur_char_endIdx;
            pre_word_endIdx = cur_word_endIdx;
        }

        // 기호를 제외한 단어 이어붙이기
        StringBuilder answer = new StringBuilder();

        int size = wordsList.size();
        Word word;

        for (i = 0; i < size; ++i) {
            word = wordsList.get(i);
            rule = word.rule;
            cur_word_startIdx = word.start;
            cur_word_endIdx = word.end;

            if (rule == 0) {
                if ((cur_string_startIdx <= cur_word_startIdx - 1) && (cur_word_endIdx + 1 < (i < size - 1 ? wordsList.get(i+1).start : len))) {
                    cur_word_startIdx--;
                    cur_word_endIdx++;
                }
            }

            if (cur_string_startIdx < cur_word_startIdx) {
                answer.append(getStr(cur_string_startIdx, cur_word_startIdx - 1));
            }
            answer.append(getStr(cur_word_startIdx, cur_word_endIdx));
            cur_string_startIdx = cur_word_endIdx + 1;
        }

        if (cur_string_startIdx < len) answer.append(getStr(cur_string_startIdx, len - 1));

        return answer.toString().trim();
    }

    String getStr(int start, int end) {
        StringBuilder str = new StringBuilder();
        char c;

        for (int i = start; i <= end; i++) {
            c = sentence[i];
            if (c >= 'A' && c <= 'Z') str.append(c);
        }

        return str.toString()+" ";
    }

    class Word {
        int rule, start, end;

        public Word(int rule, int start, int end) {
            this.rule = rule;
            this.start = start;
            this.end = end;
        }
    }
}
