package SWEA.CodeBattle.실전실습.No_6_뉴스알림_해설;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int INIT = 0;
    private final static int REGI = 1;
    private final static int OFFER = 2;
    private final static int CANCEL = 3;
    private final static int CHECK = 4;

    private static int gids[] = new int[30];
    private static int ansids[] = new int[3];
    private static int retids[] = new int[3];

    private static boolean run() throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N, K, cmd, ans, ret;
        int time, num, uid, gid, nid, delay;

        int Q = Integer.parseInt(st.nextToken());
        boolean ok = false;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());

            if (cmd == INIT) {
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                usersolution.init(N, K);
                ok = true;
            } else if (cmd == REGI) {
                time = Integer.parseInt(st.nextToken());
                uid = Integer.parseInt(st.nextToken());
                num = Integer.parseInt(st.nextToken());
                for (int m = 0; m < num; m++) {
                    gids[m] = Integer.parseInt(st.nextToken());
                }

                usersolution.registerUser(time, uid, num, gids);
            } else if (cmd == OFFER) {
                time = Integer.parseInt(st.nextToken());
                nid = Integer.parseInt(st.nextToken());
                delay = Integer.parseInt(st.nextToken());
                gid = Integer.parseInt(st.nextToken());
                ans = Integer.parseInt(st.nextToken());

                ret = usersolution.offerNews(time, nid, delay, gid);
                if (ans != ret) {
                    ok = false;
                }
            }
            else if (cmd == CANCEL) {
                time = Integer.parseInt(st.nextToken());
                nid = Integer.parseInt(st.nextToken());

                usersolution.cancelNews(time, nid);
            }
            else if (cmd == CHECK) {
                time = Integer.parseInt(st.nextToken());
                uid = Integer.parseInt(st.nextToken());

                ret = usersolution.checkUser(time, uid, retids);

                ans = Integer.parseInt(st.nextToken());
                num = ans;
                if (num > 3) num = 3;
                for (int m = 0; m < num; m++) {
                    ansids[m] = Integer.parseInt(st.nextToken());
                }
                if (ans != ret) {
//                    System.out.println("개수가 다름");
                    ok = false;
                }
                else {
                    for (int m = 0; m < num; m++) {
//                        System.out.println("  나와야할 결과 : " + ansids[m] + "  구해진 결과 : " + retids[m]);
                        if (ansids[m] != retids[m]) {
//                            System.out.println("  뉴스 id 가 다름");
                            ok = false;
                        }
                    }
                }
            }
            else ok = false;
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
//        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/실전문제/17695_뉴스알림_input_output/sample_input_one.txt"));
        System.setIn(new java.io.FileInputStream("/Users/seoyoseb/Desktop/DX특강_CodeBattle/실전문제/17695_뉴스알림_input_output/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }

        br.close();
    }
}