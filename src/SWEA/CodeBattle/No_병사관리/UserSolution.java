package SWEA.CodeBattle.No_병사관리;

public class UserSolution {
    final static int TEAM_MAX = 5;
    final static int SCORE_MAX = 5;
    final static int SOLDIER_MAX = 100000;

    Team[] teams;
    int[] soldiers;
    int[] version;

    public void init() {
        teams = new Team[TEAM_MAX + 1];
        soldiers = new int[SOLDIER_MAX + 1];
        version = new int[SOLDIER_MAX + 1];

        for (int i = 1; i <= TEAM_MAX; i++) {
            teams[i] = new Team();
            for (int j = 1; j <= SCORE_MAX; j++) {
                Soldier soldier = Soldier.getSoldier(0, -1);

                teams[i].scoreHead[j] = soldier;
                teams[i].scoreTail[j] = soldier;
            }
        }
    }

    /**
     * 고유번호가 mID, 소속팀이 mTeam, 평판 점수가 mScore 인 병사를 고용
     *
     * @param mID       : 고유 번호 (1 <= mID <= 100,000)
     * @param mTeam     : 소속팀 (1 <= mTeam <= 5)
     * @param mScore    : 평판 점수 (1 <= mScore <= 5)
     */
    public void hire(int mID, int mTeam, int mScore) {
        Soldier soldier = Soldier.getSoldier(mID, 0);

        // mTeam 팀의 mScore 점인 링크드리스트 tail 다음으로 새로 고용한 병사를 넣고
        // 새로운 tail 로 지정
        teams[mTeam].scoreTail[mScore].next = soldier;
        teams[mTeam].scoreTail[mScore] = soldier;

        // mID 의 병사가 mTeam 인지를 기록
        soldiers[mID] = mTeam;
    }

    /**
     * 고유번호가 mID 인 병사를 해고한다. fire 시 mID 병사가 있음이 보장됨
     *
     * @param mID       : 고유 번호 (1 <= mID <= 100,000)
     */
    public void fire(int mID) {
        // mID 번의 병사를 -1 로 해고 당한 상태임을 기록
        version[mID] = -1;
    }

    /**
     * 고유번호가 mID인 병사의 평판 점수를 mScore 로 변경한다.
     * 고유 번호가 mID인 병사가 있음이 보장됨
     *
     * @param mID       : 고유 번호 (1 <= mID <= 100,000)
     * @param mScore    : 평판 점수 (1 <= mScore <= 5)
     */
    public void updateSoldier(int mID, int mScore) {
        Soldier soldier = Soldier.getSoldier(mID, ++version[mID]);

        // hire() 와 마찬가지로 tail 에 등록
        teams[soldiers[mID]].scoreTail[mScore].next = soldier;
        teams[soldiers[mID]].scoreTail[mScore] = soldier;
    }

    /**
     * mTeam 의 병사들의 평판 점수를 모두 변경한다. 한 명 이상 고용되어 있음이 보장된다.
     * '변경 전 평판 점수 + mChangeScore' 가 5보다 클 경우, 평판 점수를 5로 변경
     * '변경 전 평판 점수 + mChangeScore' 가 1보다 작을 경우, 평판 점수 1로 변경
     * 그 외의 경우, 평판 점수를 '변경 전 평판 점수 + mChangeScore' 로 변경한다.
     *
     * @param mTeam           : 소속팀 (1 <= mTeam <= 5)
     * @param mChangeScore    : 평판 점수의 변화량 (-4 <= mChangeScore <= 4)
     */
    public void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore == 0) return;
        if (mChangeScore < 0) {
            for (int i = 1; i <= SCORE_MAX; i++) {
                int tmpScore = i + mChangeScore;
                tmpScore = tmpScore < 1 ? 1 : tmpScore;

                // 변경될 스코어가 i 와 같으면 그대로 이므로 continue
                if (i == tmpScore) continue;
                if (teams[mTeam].scoreHead[i].next == null) continue;

                // i 번의 head 를 변경된 점수인 tmpScore tail 의 next 로 두면 모두 옮겨진다.
                // 그 다음 tmpScore 의 새로운 tail 로 옮긴 i 번의 head 로 설정한다.
                teams[mTeam].scoreTail[tmpScore].next = teams[mTeam].scoreHead[i].next;
                teams[mTeam].scoreTail[tmpScore] = teams[mTeam].scoreTail[i];

                teams[mTeam].scoreHead[i].next = null;
                teams[mTeam].scoreTail[i] = teams[mTeam].scoreHead[i];
            }

        } else {
            for (int i = SCORE_MAX; i >= 1; i--) {
                int tmpScore = i + mChangeScore;
                tmpScore = tmpScore > 5 ? 5 : tmpScore;

                if (i == tmpScore) continue;
                if (teams[mTeam].scoreHead[i].next == null) continue;

                teams[mTeam].scoreTail[tmpScore].next = teams[mTeam].scoreHead[i].next;
                teams[mTeam].scoreTail[tmpScore] = teams[mTeam].scoreTail[i];

                teams[mTeam].scoreHead[i].next = null;
                teams[mTeam].scoreTail[i] = teams[mTeam].scoreHead[i];
            }
        }
    }

    /**
     * mTeam 병사 중 평판 점수가 가장 높은 병사 고유 번호 반환
     * 평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환
     * mTeam 병사가 한 명 이상임이 보장됨
     *
     * @param mTeam     : 소속팀 (1 <= mTeam <= 5)
     * @return          : 점수가 가장 높은 병사 mID 반환
     */
    public int bestSoldier(int mTeam) {
        int maxScoreSoldier = -1;

        for (int i = SCORE_MAX; i >= 1; i--) {
            Soldier firstSoldier = teams[mTeam].scoreHead[i].next;
            if (firstSoldier == null) continue;

            for (Soldier s = firstSoldier; s != null; s = s.next) {
                if (s.version != version[s.mID]) continue;

                maxScoreSoldier = Math.max(maxScoreSoldier, s.mID);
            }

            if (maxScoreSoldier != -1) return maxScoreSoldier;
        }

        return maxScoreSoldier;
    }

    static class Team {
        Soldier[] scoreHead = new Soldier[SCORE_MAX + 1];
        Soldier[] scoreTail = new Soldier[SCORE_MAX + 1];
    }

    static class Soldier {
        int mID, version;
        Soldier next;

        public Soldier(int mID, int version) {
            this.mID = mID;
            this.version = version;
            this.next = null;
        }

        public static Soldier getSoldier(int mID, int version) {
            return new Soldier(mID, version);
        }
    }
}
