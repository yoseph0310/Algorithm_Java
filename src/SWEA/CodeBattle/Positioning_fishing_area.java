package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Positioning_fishing_area {

    static int[][] gateList = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
    static int[][] infos;
    static int N, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            infos = new int[3][2];

            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                infos[i][0] = Integer.parseInt(st.nextToken());
                infos[i][1] = Integer.parseInt(st.nextToken());
            }
            ans = Integer.MAX_VALUE;

            for (int method = 0; method < 6; method++) { // 게이트 순서
                dfs(0, method, new int[N+1]);
            }

            System.out.println("#"+t+" "+ans);
        }
    }

    static void dfs(int depth, int method, int[] posInfo){
        if (depth == 3){
            ans = Math.min(ans, calcDistSum(posInfo));
            return;
        }

        int[] newPosInfo = new int[N + 1];
        newPosInfo = posInfo.clone();

        int gate = infos[gateList[method][depth]][0];
        int fisherNum = infos[gateList[method][depth]][1];

        int distance = 0;
        while (fisherNum > 1){
            if (gate + distance <= N && newPosInfo[gate + distance] == 0){
                newPosInfo[gate + distance] = gate;
                fisherNum--;
            }
            if (gate - distance > 0 && newPosInfo[gate - distance] == 0){
                newPosInfo[gate - distance] = gate;
                fisherNum--;
            }
            distance++;
        }
        if (fisherNum == 0) {
            dfs(depth + 1, method, newPosInfo);
        } else {
            distance = findMinDist(gate, newPosInfo);

            if (gate - distance > 0 && newPosInfo[gate - distance] == 0){
                int[] copy = new int[N+1];
                copy = newPosInfo.clone();
                copy[gate - distance] = gate;
                dfs(depth + 1, method, copy);
            }
            if (gate + distance <= N && newPosInfo[gate + distance] == 0){
                int[] copy = new int[N+1];
                copy = newPosInfo.clone();
                copy[gate + distance] = gate;
                dfs(depth + 1, method, copy);
            }
        }
    }

    // 낚시군들을 자리에 할당
    static int findMinDist(int gate, int[] posInfo){
        int leftDist = 0;
        while(gate - leftDist > 0 && posInfo[gate - leftDist] != 0){
            leftDist++;
        }
        int rightDist = 0;
        while(gate + rightDist <= N && posInfo[gate + rightDist] != 0){
            rightDist++;
        }
        int minDist = Math.min(leftDist, rightDist);
        int maxDist = Math.max(leftDist, rightDist);

        return (gate + minDist <= N && posInfo[gate + minDist] == 0) || (gate - minDist > 0 && posInfo[gate - minDist] == 0)
                ? minDist : maxDist;
    }

    // 낚시꾼들의 이동거리 계산
    static int calcDistSum(int[] posInfo){
        int sumDist = 0;
        for (int i = 1; i <= N; i++) {
            if (posInfo[i] != 0)
                sumDist += Math.abs(posInfo[i] - i) + 1;
        }
        return sumDist;
    }

}