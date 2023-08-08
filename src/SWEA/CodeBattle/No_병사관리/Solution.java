package SWEA.CodeBattle.No_병사관리;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.StringTokenizer;

public class Solution {
    private final static int CMD_INIT				= 1;
    private final static int CMD_HIRE				= 2;
    private final static int CMD_FIRE				= 3;
    private final static int CMD_UPDATE_SOLDIER		= 4;
    private final static int CMD_UPDATE_TEAM		= 5;
    private final static int CMD_BEST_SOLDIER		= 6;

    private final static UserSolution usersolution = new UserSolution();

    static int CMD_HIRE_CNT;
    static int CMD_FIRE_CNT;
    static int CMD_UPDATE_SOLDIER_CNT;
    static int CMD_UPDATE_TEAM_CNT;
    static int CMD_BEST_SOLDIER_CNT;


    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st;

        int numQuery;

        int mID, mTeam, mScore, mChangeScore;

        int userAns, ans;

        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch(cmd)
            {
                case CMD_INIT:
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_HIRE:
                    CMD_HIRE_CNT++;

                    mID = Integer.parseInt(st.nextToken());
                    mTeam = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.hire(mID, mTeam, mScore);
                    break;
                case CMD_FIRE:
                    CMD_FIRE_CNT++;

                    mID = Integer.parseInt(st.nextToken());
                    usersolution.fire(mID);
                    break;
                case CMD_UPDATE_SOLDIER:
                    CMD_UPDATE_SOLDIER_CNT++;

                    mID = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.updateSoldier(mID, mScore);
                    break;
                case CMD_UPDATE_TEAM:
                    CMD_UPDATE_TEAM_CNT++;

                    mTeam = Integer.parseInt(st.nextToken());
                    mChangeScore = Integer.parseInt(st.nextToken());
                    usersolution.updateTeam(mTeam, mChangeScore);
                    break;
                case CMD_BEST_SOLDIER:
                    CMD_BEST_SOLDIER_CNT++;

                    mTeam = Integer.parseInt(st.nextToken());
                    userAns = usersolution.bestSoldier(mTeam);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }

        return isCorrect;
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/13072_병사관리_input_output/sample_25_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            Instant beforeTime = Instant.now();
            CMD_HIRE_CNT = 0;
            CMD_FIRE_CNT = 0;
            CMD_UPDATE_SOLDIER_CNT = 0;
            CMD_UPDATE_TEAM_CNT = 0;
            CMD_BEST_SOLDIER_CNT = 0;

            int score = run(br) ? MARK : 0;

            Instant afterTime = Instant.now();
            long diffTime = Duration.between(beforeTime, afterTime).toMillis();

            // 3s 넘으면 호출 횟수 실행시간 확인
//            if (diffTime > 3000) {
//                System.out.println("  CMD_HIRE_CNT 호출 횟수 : " + CMD_HIRE_CNT);
//                System.out.println("  CMD_FIRE_CNT 호출 횟수 : " + CMD_FIRE_CNT);
//                System.out.println("  CMD_UPDATE_SOLDIER_CNT 호출 횟수 : " + CMD_UPDATE_SOLDIER_CNT);
//                System.out.println("  CMD_UPDATE_TEAM_CNT 횟수 : " + CMD_UPDATE_TEAM_CNT);
//                System.out.println("  CMD_BEST_SOLDIER_CNT 호출 횟수 : " + CMD_BEST_SOLDIER_CNT);
//                System.out.println("  실행 시간 (ms) : " + diffTime);
//            }

            // 15 MAX_TEST CASE && 20 TIME OUT CASE
            if (testcase == 15 || testcase == 20) {
                System.out.println("  CMD_HIRE_CNT 호출 횟수 : " + CMD_HIRE_CNT);
                System.out.println("  CMD_FIRE_CNT 호출 횟수 : " + CMD_FIRE_CNT);
                System.out.println("  CMD_UPDATE_SOLDIER_CNT 호출 횟수 : " + CMD_UPDATE_SOLDIER_CNT);
                System.out.println("  CMD_UPDATE_TEAM_CNT 횟수 : " + CMD_UPDATE_TEAM_CNT);
                System.out.println("  CMD_BEST_SOLDIER_CNT 호출 횟수 : " + CMD_BEST_SOLDIER_CNT);
                System.out.println("  실행 시간 (ms) : " + diffTime);
            }

            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}