package Programmers.LV2;

import java.util.LinkedList;
import java.util.Queue;

public class 프렌즈4블록_1차 {
    boolean[][] visited;

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char map[][] = new char[m][n];
        for(int i=0; i<m ; i++){
            map[i] = board[i].toCharArray();
        }

        boolean flag = true;
        while(flag){
            visited = new boolean[m][n];
            flag = false;
            for(int i=0; i<m-1; i++){
                for(int j=0; j<n-1; j++){
                    if(map[i][j] == '0') continue;
                    if(check(i, j, map)){
                        visited[i][j] = true;
                        visited[i][j+1] = true;
                        visited[i+1][j] = true;
                        visited[i+1][j+1] = true;
                        flag = true;
                    }
                }
            }
            answer += erase(m,n,map);

        }
        return answer;
    }

    boolean check(int x, int y, char[][] map) {
        char ch = map[x][y];
        if (ch==map[x][y+1] && ch==map[x+1][y] && ch==map[x+1][y+1]) {
            return true;
        }
        return false;
    }

    int erase (int m, int n, char[][] map) {
        int cnt = 0;

        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                if (visited[i][j]) map[i][j] = '.';
            }
        }

        for (int i=0; i<n; i++) {
            Queue<Character> q = new LinkedList<>();
            for (int j = m-1; j >= 0; j--) {
                if (map[j][i] == '.') {
                    cnt++;
                } else {
                    q.add(map[j][i]);
                }
            }

            int idx = m-1;
            while(!q.isEmpty()) {
                map[idx--][i] = q.poll();
            }

            for (int j = idx; j >= 0; j--) {
                map[j][i] = '0';
            }
        }

        return cnt;
    }
}
