package SWEA.CodeBattle.No_병사관리;

public class UserSolution_Practice {

    static final int MAX_SOLDIER = 100_000;
    static final int MAX_SCORE = 5;
    static final int MAX_TEAM = 5;

    static class Team {
        Soldier[] scoreHead;
        Soldier[] scoreTail;

        public Team() {
            this.scoreHead = new Soldier[MAX_SCORE + 1];
            this.scoreTail = new Soldier[MAX_SCORE + 1];
        }
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

    static Team[] team;
    static int[] soldiers;
    static int[] versions;

    void init() {
        team = new Team[MAX_TEAM + 1];
        soldiers = new int[MAX_SOLDIER + 1];
        versions = new int[MAX_SOLDIER + 1];

        for (int i = 1; i <= MAX_TEAM; i++) {
            team[i] = new Team();
            for (int j = 1; j <= MAX_SCORE; j++) {
                Soldier soldier = Soldier.getSoldier(0, -1);

                team[i].scoreHead[j] = soldier;
                team[i].scoreTail[j] = soldier;
            }
        }
    }

    void hire(int mID, int mTeam, int mScore) {
        Soldier soldier = Soldier.getSoldier(mID, 0);

        team[mTeam].scoreTail[mScore].next = soldier;
        team[mTeam].scoreTail[mScore] = soldier;

        soldiers[mID] = mTeam;
    }

    void fire(int mID) {
        versions[mID] = -1;
    }

    void updateSoldier(int mID, int mScore) {
        Soldier soldier = Soldier.getSoldier(mID, ++versions[mID]);

        team[soldiers[mID]].scoreTail[mScore].next = soldier;
        team[soldiers[mID]].scoreTail[mScore] = soldier;
    }

    void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore == 0) return;
        if (mChangeScore < 0) {
            for (int i = 1; i <= MAX_SCORE; i++) {
                int updateScore = i + mChangeScore;
                updateScore = Math.max(updateScore, 1);

                if (i == updateScore) continue;
                if (team[mTeam].scoreHead[i].next == null) continue;

                team[mTeam].scoreTail[updateScore].next = team[mTeam].scoreHead[i].next;
                team[mTeam].scoreTail[updateScore] = team[mTeam].scoreTail[i];

                team[mTeam].scoreHead[i].next = null;
                team[mTeam].scoreTail[i] = team[mTeam].scoreHead[i];
            }
        } else{
            for (int i = MAX_SCORE; i >= 1; i--) {
                int updateScore = i + mChangeScore;
                updateScore = Math.min(updateScore, 5);

                if (i == updateScore) continue;
                if (team[mTeam].scoreHead[i].next == null) continue;

                team[mTeam].scoreTail[updateScore].next = team[mTeam].scoreHead[i].next;
                team[mTeam].scoreTail[updateScore] = team[mTeam].scoreTail[i];

                team[mTeam].scoreHead[i].next = null;
                team[mTeam].scoreTail[i] = team[mTeam].scoreHead[i];
            }
        }
    }

    int bestSoldier(int mTeam) {
        int maxID = -1;

        for (int i = MAX_SCORE; i >= 1; i--) {
            Soldier soldier = team[mTeam].scoreHead[i].next;
            if (soldier == null) continue;

            for (Soldier s = soldier; s!= null; s = s.next) {
                if (s.version != versions[s.mID]) continue;

                maxID = Math.max(maxID, s.mID);
            }

            if (maxID != -1) return maxID;
        }

        return maxID;
    }
}
