package SWEA.CodeBattle.실전실습.No_2_삼국지게임;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserSolution {

    static int[] parent;                // 노드의 부모 정보    -> 25 * 25 = 625, attack 으로 인한 새 국가 생성 가능성 최대 8,000 : 8,625
    static List<Integer>[] unionList;            // 각 군주들의 동맹 리스트    -> 625
    static List<Integer>[] enemyList;            // 각 군주들의 적군 리스트    -> 625

    // 각 국가의 정보
    static class NationInfo {
        int x, y, soldierNum;
    }

    static NationInfo[] nationInfoList;        // 노드별 국가 정보    -> 8625
    static int[][] nationPosition;             // 각 국가의 좌표     -> 25 * 25

    static HashMap<String, Integer> monarchHM;  // 군주의 이름을 좌표로 표현하기 위한 해시맵  key : string(군주 이름을 string 으로 변환), value : integer (idx)
    static int nationCnt;                      // id 설정을 위한 국가 개수

    static int N;

    // 8방 확인 - 상, 상우, 우, 우하, 하, 좌하, 좌, 좌상
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    /**
     * 각 테케의 처음에 호출
     * 전체 영토는 N * N 격자 모양
     *
     *
     * @param n                 : 전체 영토의 크기 (4 <= N <= 25, 16 <= N * N <= 625)
     * @param mSoldier          : 각 영토의 병사 수 (4 <= mSoldier[][] <= 200)
     * @param mMonarch          : 각 영토의 군주 이름 (4 <= mMonarch[][] 의 길이 <= 10). 알파벳 소문자로 이뤄져있다.
     */
    void init(int n, int[][] mSoldier, char[][][] mMonarch) {
        N = n;
        int nationID = 0;

        parent = new int[8625];
        unionList = new ArrayList[625];
        enemyList = new ArrayList[625];
        for (int i = 0; i < 625; i++) {
            unionList[i] = new ArrayList<>();
            enemyList[i] = new ArrayList<>();
        }

        nationInfoList = new NationInfo[8625];
        nationPosition = new int[25][25];
        for (int i = 0; i < 8625; i++) {
            nationInfoList[i] = new NationInfo();
        }

        monarchHM = new HashMap<String, Integer>();
        nationCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String mMonarchName = charToString(mMonarch[i][j]);

                // string -> id 정보를 해시맵에 저장, 각 국가의 id 를 2차원 배열에 마킹
                monarchHM.put(mMonarchName, monarchHM.getOrDefault(mMonarchName, 0) + nationID);
                nationPosition[i][j] = nationID;

                // 국가 정보 입력
                nationInfoList[nationID].soldierNum = mSoldier[i][j];
                nationInfoList[nationID].x = i;
                nationInfoList[nationID].y = j;

                // 최초 부모 노드는 자기 자신
                parent[nationID] = nationID;

                // 동맹 리스트에 자기 자신 추가
                unionList[nationID].add(nationID);

                // 다음 국가 ID를 위해 nationID 증가
                nationID++;
            }
        }

        nationCnt = N * N;
    }

    // 각 테케에 마지막에 호출. 빈 함수로 두어도 영향을 미치지 않음
    void destroy() {}

    /**
     * <<동맹>>
     *
     * mMonarchA 의 동맹들이 mMonarchB 의 동맹들과 동맹을 맺는다.
     *
     * mMonarchA 와 mMonarchB 가 동일하거나 이미 동맹관계이면 -1 반환
     * mMonarchA 의 동맹과 mMonarchB 의 동맹 간에 적대관계가 있으면 -2 반환
     *
     * 위 두 경우가 아니면 동맹이 맺어지고 1 반환
     *
     * 각 군주 이름은 4 이상 10 이하 길이의 알파벳 소문자로 이뤄진 문자열이다.
     * mMonarchA, mMonarchB 는 현재 군주임이 보장된다.
     *
     * @param mMonarchA         : 군주의 이름 (4 <= mMonarchA <= 10)
     * @param mMonarchB         : 군주의 이름 (4 <= mMonarchB <= 10)
     *
     * @return                  : 동맹 결과 (이미 동맹이면 -1, 적대관계가 존재하면 -2, 성공하면 1)
     */
    int ally(char[] mMonarchA, char[] mMonarchB) {
        String monarchA = charToString(mMonarchA);
        String monarchB = charToString(mMonarchB);

        int idxA = monarchHM.get(monarchA);
        int idxB = monarchHM.get(monarchB);

        // A, B 가 이미 동맹이면 -1
        if (isUnion(idxA, idxB)) return -1;
        // A, B 동맹들 사이에 적대관계가 있으면 -2
        if (isEnemy(idxA, idxB)) return -2;

        // 위 경우 아니라면 union
        mkUnion(idxA, idxB);

        return 1;
    }

    /**
     * <<공격>>
     *
     * mMonarchA 와 동맹들이 mMonarchB 영토를 공격한다. 공격을 지휘하는 장수는 mGeneral.
     *
     * mMonarchA, mMonarchB 가 동맹 관계면 -1 반환, 전투는 일어나지 않는다.
     * mMonarchA 의 영토 또는 동맹 영토가 mMonarchB 의 영토와 인접하지 않으면 -2 반환, 전투는 일어나지 않는다.
     *
     * <전투 발생>
     * 1. mMonarchA, mMonarchB 의 동맹은 서로 적대가 된다.
     * 2. mMonarchB 영토에 *인접한* mMonarchA 를 포함한 모든 동맹들은 보유한 병사의 절반을 보내 함께 공격한다.
     * 3. mMonarchB 영토에 *인접한* mMonarchB 의 모든 동맹들도 보유한 병사의 절반을 보내 방어한다.
     * 4. 보내는 병사 계산시 소수점은 버린다.
     * 5. 공격하는 병사 수가 0명이라도 전투가 발생한것으로 간주. -> (강조됨)
     * 6. 전투 시 병사들은 상대 병사와 1:1로 싸워 함께 전사.
     *
     * <전투 결과>
     * 1. 남은 병사로 결정.
     * 2. 공격쪽의 병사가 남았다면 공격 성공으로 1 반환
     * 3. 방어쪽의 병사가 남았거나 모든 병사가 사망했다면 0 반환
     * 4. 공격 지휘 장수는 병사 수에 포함 X
     *
     * <공격 성공>
     * 1. mMonarchB 는 처형. mMonarchB 가 다스렸던 영토는 멸망하여 동맹관계, 적대관계도 없는 새로운 영토가 된다. -> (강조됨)
     * 2. 새로운 영토의 군주는 mGeneral 이 되고 mMonarchA 의 동맹에 편입, 적대관계는 mMonarchA 의 적대관계와 동일하다.
     *
     * 각 군주 이름은 4 이상 10 이하 길이의 알파벳 소문자로 이뤄진 문자열이다.
     * mMonarchA, mMonarchB 는 현재 군주임이 보장된다. mGeneral 은 군주가 아님이 보장된다.
     *
     * @param mMonarchA         : 군주의 이름 (4 <= mMonarchA <= 10)
     * @param mMonarchB         : 군주의 이름 (4 <= mMonarchB <= 10)
     * @param mGeneral          : 장수의 이름 (4 <= mGeneral <= 10)
     *
     * @return                  : 공격의 결과 (승리 1, 방어 성공 0, 이미 동맹이면 -1), (공격 영토 주변에 동맹이 없으면 -2)
     */
    int attack(char[] mMonarchA, char[] mMonarchB, char[] mGeneral) {
        String monarchA = charToString(mMonarchA);
        String monarchB = charToString(mMonarchB);
        String general = charToString(mGeneral);

        int idxA = monarchHM.get(monarchA);
        int idxB = monarchHM.get(monarchB);

        // 같은 동맹인지 확인
        if (isUnion(idxA, idxB)) return -1;

        // A 동맹, B 동맹 들이 B 영토에 인접하지 않으면 전투가 일어나지 않으므로 인접하는지 확인
        int Bx = nationInfoList[idxB].x;
        int By = nationInfoList[idxB].y;

        boolean isEnemy = false;
        for (int d = 0; d < 8; d++) {
            int nx = Bx + dx[d];
            int ny = By + dy[d];

            // 경계선 안이고 다음 좌표에 A의 동맹군이 하나라도 있다면 전투는 시작 된다.
            if (isBoundary(nx, ny)) {
                if (find(idxA) == find(nationPosition[nx][ny])) {
                    isEnemy = true;
                    break;
                }
            }
        }

        if (!isEnemy) return -2;

        // 전투 시작
        mkEnemy(find(idxA), find(idxB));

        int totalSoldierA = 0;
        int totalSoldierB = nationInfoList[nationPosition[Bx][By]].soldierNum;
        int dispatchCnt;

        for (int d = 0; d < 8; d++) {
            int nx = Bx + dx[d];
            int ny = By + dy[d];

            if (isBoundary(nx, ny)) {
                // 적군이면
                if (find(idxA) == find(nationPosition[nx][ny])) {
                    dispatchCnt = nationInfoList[nationPosition[nx][ny]].soldierNum / 2;
                    nationInfoList[nationPosition[nx][ny]].soldierNum -= dispatchCnt;
                    totalSoldierA += dispatchCnt;
                }
                // 아군이면
                else if (find(idxB) == find(nationPosition[nx][ny])) {
                    dispatchCnt = nationInfoList[nationPosition[nx][ny]].soldierNum / 2;
                    nationInfoList[nationPosition[nx][ny]].soldierNum -= dispatchCnt;
                    totalSoldierB += dispatchCnt;
                }
            }
        }

        // 방어 성공
        if (totalSoldierB >= totalSoldierA) {
            nationInfoList[idxB].soldierNum = totalSoldierB - totalSoldierA;
            return 0;
        }

        // 방어 실패 - 공격 대상지는 삭제된다.
        unionList[find(idxB)].remove((Integer) idxB); // idxB 를 인덱스로 인식하고 있다.

        // 해당 영토의 병사 수는 A - B. 동맹관계, 적대관계도 없는 새로운 영토가 된다.
        nationInfoList[idxB].soldierNum = totalSoldierA - totalSoldierB;

        nationInfoList[nationCnt] = nationInfoList[idxB];       // 새로운 id 에 idxB 의 위치를 대입
        nationPosition[nationInfoList[nationCnt].x][nationInfoList[nationCnt].y] = nationCnt;   // 해당 좌표에 새로운 id 부여

        // mGeneral 이 새 영토의 주인이 된다.
        monarchHM.put(general, monarchHM.getOrDefault(general, 0) + nationCnt);

        // 동맹 관계 편입
        parent[nationCnt] = find(idxA);
        unionList[find(idxA)].add(nationCnt);

        nationCnt++;

        return 1;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    /**
     * <<병사 모집>>
     *
     * 1. mOption : 0
     *  - 군주 mMonarch 의 영토에 mNum 명의 병사를 모집
     *  - 병사 모집 이후에 군주 mMonarch 영토의 병사 수 반환
     *
     * 2. mOption : 1
     *  - 군주 mMonarch 를 포함한 모든 동맹의 영토에 각각 mNum 명의 병사를 모집.
     *  - 병사 모집 이후에 군주 mMonarch 동맹의 모든 병사의 수 합산하여 반환
     *
     * 각 군주 이름은 4 이상 10 이하 길이의 알파벳 소문자로 이뤄진 문자열이다.
     * mMonarch 는 현재 군주임이 보장된다.
     *
     * @param mMonarch          : 군주의 이름 (4 <= mMonarch <= 10)
     * @param mNum              : 병사의 수 (1 <= mNum <= 200)
     * @param mOption           : 병사 모집 조건 (0 || 1)
     *
     * @return                  : 병사의 수
     */
    int recruit(char[] mMonarch, int mNum, int mOption) {
        String monarch = charToString(mMonarch);

        int recruitCnt = 0;

        // monarch 만 mNum 병사 모집
        if (mOption == 0) {
            int idxA = monarchHM.get(monarch);
            nationInfoList[idxA].soldierNum += mNum;
            recruitCnt = nationInfoList[idxA].soldierNum;
        }
        // monarch 포함 모든 동맹 mNum 병사 모집
        else {
            int rootA = find(monarchHM.get(monarch));
            for (int allies: unionList[rootA]) {
                nationInfoList[allies].soldierNum += mNum;
                recruitCnt += nationInfoList[allies].soldierNum;
            }
        }

        return recruitCnt;
    }

    // x 의 부모를 찾는다
    static int find(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    // x, y 의 부모가 다르면 조합한다.
    // 부모 쪽 기준으로 두고 부모를 x라 하면
    // x 동맹 리스트에 y 의 동맹리스트 추가
    // x 적군 리스트에 y 의 적군리스트 추가
    static void mkUnion(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;

            // y 의 동맹리스트, 적군 리스트 x 에도 추가
            for (int n: unionList[y]) {
                unionList[x].add(n);
            }
            for (int n: enemyList[y]) {
                enemyList[x].add(n);
            }
        }
    }

    // x, y 가 서로 같은 동맹인지 판단한다.
    static boolean isUnion(int x, int y) {
        x = find(x);
        y = find(y);

        return x == y;
    }

    // x, y 가 적군인지 판단한다.
    static boolean isEnemy(int x, int y) {
        x = find(x);
        y = find(y);

        // x 의 적군리스트에서 y 가 있다면 true 반환
        for (int enemy : enemyList[x]) {
            // 전부 다 돌지 않고 enemy 를 find() 넣고 돌려 y 가 나오면 적군임
            if (find(enemy) == y) return true;
        }
        return false;
    }

    // x, y 를 서로의 적군으로 추가한다.
    static void mkEnemy(int x, int y) {
        x = find(x);
        y = find(y);

        // 동맹이거나 이미 적군이면 리턴
        if (x == y || isEnemy(x, y)) return;

        enemyList[x].add(y);
        enemyList[y].add(x);
    }

    static String charToString(char[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '\0') sb.append(arr[i]);
        }

        return sb.toString();
    }
}