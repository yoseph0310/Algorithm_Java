package Programmers.LV3;
import java.util.*;

public class 리틀_프렌즈_사천성 {

    int N, M;
    char[][] board;
    List<Character> list = new ArrayList<>();
    HashMap<Character, int[][]> map = new HashMap<>();

    public String solution(int m, int n, String[] b) {
        String answer = "";

        N = m;
        M = n;

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = b[i].charAt(j);
                board[i][j] = c;

                if (c != '.' && c != '*') {
                    if (!list.contains(c)) {
                        list.add(c);
                        map.put(c, new int[2][2]);
                        map.get(c)[0][0] = i;
                        map.get(c)[0][1] = j;
                    } else {
                        map.get(c)[1][0] = i;
                        map.get(c)[1][1] = j;
                    }
                }
            }
        }

        Collections.sort(list);

        int idx = 0;
        while (list.size() != 0) {
            if (canDelete(list.get(idx))) {
                char popped = list.remove(idx);
                answer += popped;
                deleteChar(popped);
                idx = 0;
            } else {
                idx++;
                if (idx == list.size()) {
                    return "IMPOSSIBLE";
                }
            }
        }

        return answer;
    }
    void deleteChar(char a) {
        int x1 = map.get(a)[0][0];
        int y1 = map.get(a)[0][1];
        int x2 = map.get(a)[1][0];
        int y2 = map.get(a)[1][1];

        board[x1][y1] = '.';
        board[x2][y2] = '.';
    }

    boolean canDelete(char a) {
        int x1 = map.get(a)[0][0];
        int y1 = map.get(a)[0][1];
        int x2 = map.get(a)[1][0];
        int y2 = map.get(a)[1][1];

        if (y1 < y2) {
            if (rowCheck(x1, x2, y1, a) && columnCheck(y1, y2, x2, a)) {
                return true;
            }
            if (columnCheck(y1, y2, x1, a) && rowCheck(x1, x2, y2, a)) {
                return true;
            }
        } else {
            if (rowCheck(x1, x2, y2, a) && columnCheck(y2, y1, x1, a)) {
                return true;
            }
            if (columnCheck(y2, y1, x2, a) && rowCheck(x1, x2, y1, a)) {
                return true;
            }
        }

        return false;
    }

    boolean rowCheck(int x1, int x2, int y, int a) {
        for (int i = x1; i <= x2; i++) {
            if (board[i][y] != '.' && board[i][y] != a) return false;
        }
        return true;
    }

    boolean columnCheck(int y1, int y2, int x, int a) {
        for (int i = y1; i <= y2; i++) {
            if (board[x][i] != '.' && board[x][i] != a) return false;
        }
        return true;
    }
}

/*
    1. String[] board -> char[][] board
    2. 타일 종류 저장할 리스트 -> List<Character> list
    3. 타일 위치 저장할 맵 -> HashMap<Character, int[][]> map : 같은 종류의 타일 2개의 좌표를 저장.
    4. 리스트를 오름차순으로 정렬
    5. 조건에 맞게 타일을 제거할 수 있는지 여부 확인
        - 제거할 수 있다면 리스트에서 제거.
        - 리스트에서 제거한 문자를 answer에 더한다.
        - 리스트에서 제거한 문자를 board에 '.'으로 공백 처리
        - 제거할 수 없다면 idx를 늘려서 리스트의 다음 요소를 확인. idx가 리스트 사이즈만큼 갔으면 IMPOSSIBLE.
*/