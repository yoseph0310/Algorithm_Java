package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2110_공유기설치 {

    static int N, C;
    static int[] house;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        int low = 1;                            // 최소 거리가 가질 수 있는 최솟값
        int high = house[N-1] - house[0] + 1;   // 최소 거리가 가질 수 있는 최댓값

        while(low < high){
            int mid = (low + high) / 2;
            /*
             * mid 거리에 대해 설치 가능한 공유기 개수가 M 개수에 못미치면
             * 거리를 좁혀야 한다.
             */
            if (canInstall(mid) < C){
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low - 1);
    }

    static int canInstall(int dis) {
        int cnt = 1;
        int lastLocate = house[0];

        for (int i = 0; i < house.length; i++) {
            int locate = house[i];

            if (locate - lastLocate >= dis){
                cnt++;
                lastLocate = locate;
            }
        }
        return cnt;
    }
}
