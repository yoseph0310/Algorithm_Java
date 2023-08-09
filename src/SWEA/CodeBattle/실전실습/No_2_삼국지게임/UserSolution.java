package SWEA.CodeBattle.실전실습.No_2_삼국지게임;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserSolution {

    static class NationInfo {
        int x, y, soldierNum;
    }

    static int[] parents;
    static List<Integer>[] unionList;
    static List<Integer>[] enemyList;

    static int[][] nationPosition;
    static NationInfo[] nationInfoList;

    static HashMap<String, Integer> monarchHM;
    static int nationCnt;

    static int N;

    // 상 우상 우 우하 하 좌하 좌 좌상
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

        parents = new int[8625];
        unionList = new ArrayList[625];
        enemyList = new ArrayList[625];
        for (int i = 0; i < 625; i++) {
            unionList[i] = new ArrayList<>();
            enemyList[i] = new ArrayList<>();
        }

        nationPosition = new int[25][25];
        nationInfoList = new NationInfo[8625];
        for (int i = 0; i < 8625; i++) {
            nationInfoList[i] = new NationInfo();
        }

        monarchHM = new HashMap<>();
        nationCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String monarchName = charToString(mMonarch[i][j]);

                // String -> int idx 로 변환 : HM 사용
                monarchHM.put(monarchName, monarchHM.getOrDefault(monarchName, 0) + nationCnt);

                // 최초 부모는 자기 자신
                parents[nationCnt] = nationCnt;

                // 위치 기록
                nationPosition[i][j] = nationCnt;

                // 국가 정보 기록
                nationInfoList[nationCnt].soldierNum = mSoldier[i][j];
                nationInfoList[nationCnt].x = i;
                nationInfoList[nationCnt].y = j;

                // 자기 자신도 자신의 동맹에 추가
                unionList[nationCnt].add(nationCnt);

                nationCnt++;
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

        if (isUnion(idxA, idxB)) {
            return -1;
        }
        if (isEnemy(idxA, idxB)) {
            return -2;
        }

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

        // 동맹 관계면 -1 반환
        if (isUnion(idxA, idxB)) {
            return -1;
        }

        // 인접 동맹 확인
        int Bx = nationInfoList[idxB].x;
        int By = nationInfoList[idxB].y;

        boolean isEnemy = false;
        for (int d = 0; d < 8; d++) {
            int nx = Bx + dx[d];
            int ny = By + dy[d];

            if (isBoundary(nx, ny)) {
                // 적군의 루트와 다음 좌표에 위치한 군주의 루트가 같으면 적군이 존재하는 것
                if (find(idxA) == find(nationPosition[nx][ny])) {
                    isEnemy = true;
                    break;
                }
            }
        }

        // 하나라도 적군이 없다면 -2 반환
        if (!isEnemy) {
            return -2;
        }

        mkEnemy(idxA, idxB);

        int totalSoldierA = 0;
        int totalSoldierB = nationInfoList[idxB].soldierNum;
        int dispatchCnt = 0;

        for (int d = 0; d < 8; d++) {
            int nx = Bx + dx[d];
            int ny = By + dy[d];

            if (isBoundary(nx, ny)) {
                // B 기준 - A는 적군. 적군이면 병사 수 / 2 파견
                if (find(idxA) == find(nationPosition[nx][ny])) {
                    dispatchCnt = nationInfoList[nationPosition[nx][ny]].soldierNum / 2;
                    nationInfoList[nationPosition[nx][ny]].soldierNum -= dispatchCnt;
                    totalSoldierA += dispatchCnt;
                }
                // B 기준 - B 아군
                else if (find(idxB) == find(nationPosition[nx][ny])) {
                    dispatchCnt = nationInfoList[nationPosition[nx][ny]].soldierNum / 2;
                    nationInfoList[nationPosition[nx][ny]].soldierNum -= dispatchCnt;
                    totalSoldierB += dispatchCnt;
                }
            }
        }

        // 방어 성공 0 리턴
        if (totalSoldierB >= totalSoldierA) {
            nationInfoList[idxB].soldierNum = totalSoldierB - totalSoldierA;
            return 0;
        }

        // 공격 성공하면 공격지 군주는 처형
        unionList[find(idxB)].remove((Integer) idxB);

        // 공격지였던 영토는 동맹관계, 적대관계도 없는 새 영토가 된다.
        nationInfoList[idxB].soldierNum = totalSoldierA - totalSoldierB;        // 남은 병사 결정

        // 새 군주 ID 인 nationCnt 로 정보들을 새로 입력
        nationInfoList[nationCnt] = nationInfoList[idxB];
        nationPosition[nationInfoList[nationCnt].x][nationInfoList[nationCnt].y] = nationCnt;

        // 새로운 군주 등극
        monarchHM.put(general, monarchHM.getOrDefault(general, 0) + nationCnt);

        // 동맹은 A 에 편입, 적대관계 또한 동일한다.
        parents[nationCnt] = find(idxA);
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

        if (mOption == 0) {
            int idx = monarchHM.get(monarch);
            nationInfoList[idx].soldierNum += mNum;
            recruitCnt = nationInfoList[idx].soldierNum;
        } else {
            int rootA = find(monarchHM.get(monarch));
            for (int allies: unionList[rootA]) {
                nationInfoList[allies].soldierNum += mNum;
                recruitCnt += nationInfoList[allies].soldierNum;
            }
        }

        return recruitCnt;
    }

    static int find(int x) {
        if (x == parents[x]) return x;
        else return parents[x] = find(parents[x]);
    }

    static void mkUnion(int x, int y) {
        x = find(x);
        y = find(y);

        // x 기준으로 몰아준다.
        if (x != y) {
            parents[y] = x;

            // x 의 동맹 리스트에 y 동맹 리스트 추가
            for (int allies: unionList[y]) {
                unionList[x].add(allies);
            }
            // x 의 적 리스트에 y 적 리스트 추가
            for (int enemy : enemyList[y]) {
                enemyList[x].add(enemy);
            }
        }
    }

    static boolean isUnion(int x, int y) {
        x = find(x);
        y = find(y);

        return x == y;
    }

    static boolean isEnemy(int x, int y) {
        x = find(x);
        y = find(y);

        for (int enemy : enemyList[x]) {
            if (find(enemy) == y) {
                return true;
            }
        }

        return false;
    }

    static void mkEnemy(int x, int y) {
        x = find(x);
        y = find(y);

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