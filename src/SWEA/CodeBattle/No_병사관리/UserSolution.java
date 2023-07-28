package SWEA.CodeBattle.No_병사관리;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class UserSolution  {

    final int MAX_TEAM_LEN = 5;

    HashMap<Integer, Soldier> soldierInfo;
    ArrayList<Soldier>[] teamList;

    public void init() {

        soldierInfo = new HashMap<>();      // mID 를 key 로 하는 Soldier 실제 객체 저장하는 Map
        teamList = new ArrayList[MAX_TEAM_LEN + 1];    // team 으로 조회하기 위한 List

        for (int i = 1; i <= MAX_TEAM_LEN; i++) {
            teamList[i] = new ArrayList<>();
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
//        System.out.println("hire() : " + mID + " 번 병사를 " + mTeam + " 에 고용합니다. 점수는 " + mScore + " 입니다.");

        Soldier soldier = new Soldier(mID, mTeam, mScore);

        soldierInfo.put(mID, soldier);
        teamList[mTeam].add(soldier);

//        print_mTeam(mTeam);
    }

    /**
     * 고유번호가 mID 인 병사를 해고한다. fire 시 mID 병사가 있음이 보장됨
     *
     * @param mID       : 고유 번호 (1 <= mID <= 100,000)
     */
    public void fire(int mID) {
//        System.out.println("fire() : " + mID + " 번 병사를 해고합니다.");

        // mID 병사가 있음이 보장되므로 contains 는 굳이 사용 안함.
        Soldier soldier = soldierInfo.get(mID);

        soldierInfo.remove(soldier.mID);
        teamList[soldier.mTeam].remove(soldier);
//        print_mTeam(soldier.mTeam);
    }

    /**
     * 고유번호가 mID인 병사의 평판 점수를 mScore 로 변경한다.
     * 고유 번호가 mID인 병사가 있음이 보장됨
     *
     * @param mID       : 고유 번호 (1 <= mID <= 100,000)
     * @param mScore    : 평판 점수 (1 <= mScore <= 5)
     */
    public void updateSoldier(int mID, int mScore) {
//        System.out.println("updateSoldier() : " + mID + " 번 병사의 점수를 " + mScore + " 로 변경합니다.");

        // mID 병사가 있음이 보장되므로 contains 는 굳이 사용 안함.
        Soldier soldier = soldierInfo.get(mID);
//        System.out.println("  변경 전 : " + soldier.mScore);
        soldier.mScore = mScore;
//        System.out.println("  변경 후 : " + soldier.mScore);
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
//        System.out.println("updateTeam() : " + mTeam + " 의 모든 병사들의 점수를 조건에 맞게 각각 변경합니다.");

//        print_mTeam(mTeam);
        ArrayList<Soldier> tmp = teamList[mTeam];

        for (int i = 0; i < tmp.size(); i++) {
            Soldier s = tmp.get(i);

            if ((s.mScore + mChangeScore) > 5) s.mScore = 5;
            else s.mScore = Math.max((s.mScore + mChangeScore), 1);
        }
//        System.out.println("  팀 점수 변경 후");
//        print_mTeam(mTeam);
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
        // 여기 때문에 PriorityQueue vs ArrayList 가 고민됨 - 일단 ArrayList 먼저 선택
//        System.out.println("bestSoldier() : " + mTeam + " 번 팀에서 가장 뛰어난 병사를 반환합니다.");

        Collections.sort(teamList[mTeam]);

//        System.out.println("  점수가 가장 높은 병사 : " + teamList[mTeam].get(0).mID);

        return teamList[mTeam].get(0).mID;
    }

    static class Soldier implements Comparable<Soldier>{
        int mID, mTeam, mScore;

        public Soldier(int mID, int mTeam, int mScore) {
            this.mID = mID;
            this.mTeam = mTeam;
            this.mScore = mScore;
        }

        @Override
        public int compareTo(Soldier s) {
            if (s.mScore == mScore) return s.mID - mID;
            else return s.mScore - mScore;
        }
    }

}
