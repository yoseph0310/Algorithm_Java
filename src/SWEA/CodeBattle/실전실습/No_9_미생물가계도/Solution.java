package SWEA.CodeBattle.실전실습.No_9_미생물가계도;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    private static final int MAX_LENGTH				= 11 + 1;
    private static final int CMD_INIT				= 100;
    private static final int CMD_ADD				= 200;
    private static final int CMD_DISTANCE				= 300;
    private static final int CMD_COUNT				= 400;

    private static void String2Char(String s, char[] b, int maxlen)
    {
        int n = s.length();
        for (int i = 0; i < n; ++i)
            b[i] = s.charAt(i);
        for (int i = n; i < maxlen; ++i)
            b[i] = '\0';
    }

    private static UserSolution usersolution = new UserSolution();

    private static boolean run(BufferedReader br) throws Exception
    {
        int Q;

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        Q = Integer.parseInt(stdin.nextToken());

        boolean okay = false;

        for (int q = 0; q < Q; ++q)
        {
            stdin = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(stdin.nextToken());
            int ret, ans, mDay1, mDay2;
            char mStr1[] = new char[MAX_LENGTH], mStr2[] = new char[MAX_LENGTH];

            switch(cmd)
            {
                case CMD_INIT:
                    String2Char(stdin.nextToken(), mStr1, MAX_LENGTH);
                    mDay1 = Integer.parseInt(stdin.nextToken());
                    usersolution.init(mStr1, mDay1);
                    okay = true;
                    break;
                case CMD_ADD:
                    String2Char(stdin.nextToken(), mStr1, MAX_LENGTH);
                    String2Char(stdin.nextToken(), mStr2, MAX_LENGTH);
                    mDay1 = Integer.parseInt(stdin.nextToken());
                    mDay2 = Integer.parseInt(stdin.nextToken());
                    ret = usersolution.add(mStr1, mStr2, mDay1, mDay2);
                    ans = Integer.parseInt(stdin.nextToken());
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_DISTANCE:
                    String2Char(stdin.nextToken(), mStr1, MAX_LENGTH);
                    String2Char(stdin.nextToken(), mStr2, MAX_LENGTH);
                    ret = usersolution.distance(mStr1, mStr2);
                    ans = Integer.parseInt(stdin.nextToken());
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_COUNT:
                    mDay1 = Integer.parseInt(stdin.nextToken());
                    ret = usersolution.count(mDay1);
                    ans = Integer.parseInt(stdin.nextToken());
                    if (ans != ret)
                        okay = false;
                    break;
                default:
                    okay = false;
                    break;
            }

        }

        return okay;
    }

    public static void main(String[] args) throws Exception
    {
        // System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
//        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/17698_미생물가계도_input_output/sample_input_one.txt"));
        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/17698_미생물가계도_input_output/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(stinit.nextToken());
        int MARK = Integer.parseInt(stinit.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}