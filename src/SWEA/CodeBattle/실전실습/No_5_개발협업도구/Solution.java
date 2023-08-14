package SWEA.CodeBattle.실전실습.No_5_개발협업도구;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private static final int CMD_CREATE = 100;
    private static final int CMD_EDIT = 200;
    private static final int CMD_BRANCH = 300;
    private static final int CMD_MERGE = 400;
    private static final int CMD_READ = 500;

    private static final int MAXN = 10000;
    private static final int MAXL = 11;

    private static char mBranch[] = new char[MAXL];
    private static char mChild[] = new char[MAXL];
    private static char mFile[] = new char[MAXL];
    private static char mData[] = new char[MAXL];
    private static char retString[] = new char[MAXL];

    private static int mstrcmp(char str1[], char str2[]) {
        int c = 0;
        while (str1[c] != 0 && str1[c] == str2[c])
            ++c;
        return str1[c] - str2[c];
    }

    private static void String2Char(char buf[], StringTokenizer st) {
        String inputStr = st.nextToken();
        for (int k = 0; k < inputStr.length(); ++k) {
            buf[k] = inputStr.charAt(k);
        }
        buf[inputStr.length()] = '\0';
    }

    private static boolean run() throws Exception {

        int Q, ans;
        boolean isOkey = true;

        usersolution.init();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Q = Integer.parseInt(st.nextToken());

        for (int q = 1; q <= Q; ++q) {
            int cmd, len;

            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case CMD_CREATE:
                    String2Char(mBranch, st);
                    String2Char(mFile, st);
                    String2Char(mData, st);
                    usersolution.create(q, mBranch, mFile, mData);
                    break;

                case CMD_EDIT:
                    String2Char(mBranch, st);
                    String2Char(mFile, st);
                    String2Char(mData, st);
                    usersolution.edit(q, mBranch, mFile, mData);
                    break;

                case CMD_BRANCH:
                    String2Char(mBranch, st);
                    String2Char(mChild, st);
                    usersolution.branch(q, mBranch, mChild);
                    break;

                case CMD_MERGE:
                    String2Char(mBranch, st);
                    usersolution.merge(q, mBranch);
                    break;

                case CMD_READ:
                    String2Char(mBranch, st);
                    String2Char(mFile, st);
                    len = usersolution.readfile(q, mBranch, mFile, retString);
                    ans = Integer.parseInt(st.nextToken());
                    String2Char(mData, st);
                    if (ans != len) {
                        isOkey = false;
                    } else if (mstrcmp(retString, mData) != 0) {
                        isOkey = false;
                    }
                    break;

                default:
                    isOkey = false;
            }
        }

        return isOkey;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;
        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/17694_개발협업도구_input_output/sample_input.txt"));
//        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/17694_개발협업도구_input_output/sample_input_one.txt"));
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");

        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; ++tc) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }

        br.close();
    }
}