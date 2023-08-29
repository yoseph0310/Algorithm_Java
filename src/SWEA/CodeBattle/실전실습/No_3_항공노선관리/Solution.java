package SWEA.CodeBattle.실전실습.No_3_항공노선관리;

import java.util.Scanner;

public class Solution {
    private final static int CMD_INIT = 1;
    private final static int CMD_ADD = 2;
    private final static int CMD_MIN_TRAVEL_TIME = 3;
    private final static int CMD_MIN_PRICE = 4;

//    private final static UserSolution_Coach usersolution = new UserSolution_Coach();
    private final static UserSolution usersolution = new UserSolution();
//    private final static UserSolution_Practice usersolution = new UserSolution_Practice();

    private static boolean run(Scanner sc)
    {
        int numQuery;
        int N, mStartAirport, mEndAirport, mStartTime, mTravelTime, mPrice;
        int userAns, ans;

        boolean isCorrect = false;

        numQuery = sc.nextInt();

        for (int q = 0; q < numQuery; q++)
        {
            int cmd;
            cmd = sc.nextInt();

            switch (cmd)
            {
                case CMD_INIT:
                    N = sc.nextInt();
                    usersolution.init(N);
                    isCorrect = true;
                    break;
                case CMD_ADD:
                    mStartAirport = sc.nextInt();
                    mEndAirport = sc.nextInt();
                    mStartTime = sc.nextInt();
                    mTravelTime = sc.nextInt();
                    mPrice = sc.nextInt();
                    usersolution.add(mStartAirport, mEndAirport, mStartTime, mTravelTime, mPrice);
                    break;
                case CMD_MIN_TRAVEL_TIME:
                    mStartAirport = sc.nextInt();
                    mEndAirport = sc.nextInt();
                    mStartTime = sc.nextInt();
                    userAns = usersolution.minTravelTime(mStartAirport, mEndAirport, mStartTime);
                    ans = sc.nextInt();
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
                    break;
                case CMD_MIN_PRICE:
                    mStartAirport = sc.nextInt();
                    mEndAirport = sc.nextInt();
                    userAns = usersolution.minPrice(mStartAirport, mEndAirport);
                    ans = sc.nextInt();
                    if (userAns != ans)
                    {
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
        int T, MARK;

//        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/실전문제/15965_항공노선관리_input_output/sample_input_one.txt"));
        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/실전문제/15965_항공노선관리_input_output/sample_input.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        MARK = sc.nextInt();

        long start = System.currentTimeMillis();
        for (int tc = 1; tc <= T; tc++)
        {
            int score = run(sc) ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }
        long end = System.currentTimeMillis();
        long diff = end - start;
        System.out.println("총 실행 시간 : " + diff + " (ms)");

        sc.close();
    }
}