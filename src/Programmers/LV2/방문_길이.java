package Programmers.LV2;

public class 방문_길이 {
    static int dr[] = {-1,0,1,0};
    static int dc[] = {0,1,0,-1};   // U, R, D, L

    public int solution(String dirs) {
        int answer = 0;
        char arr[] = dirs.toCharArray();
        boolean visited[][][][] = new boolean[11][11][11][11];

        int idx = 0;
        int sr = 0; int sc = 0;
        int nr = 5; int nc = 5;

        for(int i = 0; i < arr.length; i++){

            sr = nr; sc = nc;

            if ( arr[i] == 'U' ){
                idx = 0;
            } else if ( arr[i] == 'R'){
                idx = 1;
            } else if ( arr[i] == 'D' ){
                idx = 2;
            } else if ( arr[i] == 'L' ){
                idx = 3;
            }

            nr = sr + dr[idx];
            nc = sc + dc[idx];

            if( nr < 0 || nc < 0 || nr > 10 || nc > 10) {
                nr -= dr[idx];
                nc -= dc[idx];
                continue;
            }

            if ( !visited[sr][sc][nr][nc] && !visited[nr][nc][sr][sc]){
                visited[sr][sc][nr][nc] = true;
                visited[nr][nc][sr][sc] = true;
                answer++;
            }
        }

        System.out.println(answer);
        return answer;
    }
}
