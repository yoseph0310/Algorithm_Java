package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class A_2022_하반기_2번_산타의_선물공장_2 {

    static int N, M;
    static LinkedList<Integer>[] beltList;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            switch (command) {
                case 100:
                    // TODO 1. 공장 설립
                    buildFactory();
                    break;
                case 200:
                    // TODO 2. 물건 모두 옮기기
                    moveAllPresent();
                    break;
                case 300:
                    // TODO 3. 앞 물건만 교체하기
                    moveFrontPresent();
                    break;
                case 400:
                    // TODO 4. 물건 나누기
                    dividePresent();
                    break;
                case 500:
                    // TODO 5. 선물 정보 얻기
                    getPresentInfo();
                    break;
                case 600:
                    // TODO 6. 벨트 정보 얻기
                    getBeltInfo();
                    break;
            }


//            System.out.println("-------- " + (i+1) + " 번 째 명령 실행 후 벨트와 선물 상태 --------");
//            for (int b = 1; b <= N; b++) {
//                int size = beltList[b].size();
//                for (int j = 0; j < size; j++) {
//                    int p_num = beltList[b].pollFirst();
//                    System.out.print(p_num + " ");
//                    beltList[b].addLast(p_num);
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
    }

    // 1번
    static void buildFactory() {
//        System.out.println("100번 명령 수행");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        beltList = new LinkedList[N + 1];
        for (int i = 1; i <= N; i++) {
            beltList[i] = new LinkedList<>();
        }

        for (int i = 1; i <= M; i++) {
            int b_num = Integer.parseInt(st.nextToken());
            beltList[b_num].add(i);
        }

    }

    // 2번
    static void moveAllPresent() {
//        System.out.print("200번 명령 수행 : ");
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        while (!beltList[src].isEmpty()) {
            beltList[dst].addFirst(beltList[src].pollLast());
        }

        System.out.println(beltList[dst].size());
    }

    // 3번
    static void moveFrontPresent() {
//        System.out.print("300번 명령 수행 : ");
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        int src_p = 0;
        int dst_p = 0;

        // src 와 dst 둘 다 물건이 있을 때
        if (!beltList[src].isEmpty() && !beltList[dst].isEmpty()) {
            src_p = beltList[src].pollFirst();
            dst_p = beltList[dst].pollFirst();

            beltList[dst].addFirst(src_p);
            beltList[src].addFirst(dst_p);
        }

        // src 에만 물건이 없을 때
        else if (beltList[src].isEmpty() && !beltList[dst].isEmpty()) {
            dst_p = beltList[dst].pollFirst();

            beltList[src].addFirst(dst_p);
        }

        // dst 에만 물건이 없을 때
        else if (!beltList[src].isEmpty() && beltList[dst].isEmpty() ) {
            src_p = beltList[src].pollFirst();

            beltList[dst].addFirst(src_p);
        }


        System.out.println(beltList[dst].size());
    }

    // 4번
    static void dividePresent() {
//        System.out.print("400번 명령 수행 : ");
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        if (beltList[src].size() != 1) {
            int idx = beltList[src].size() / 2;

            for (int i = 0; i < idx; i++) {
                beltList[dst].addFirst(beltList[src].pollFirst());
            }
        }

        System.out.println(beltList[dst].size());
    }

    // 5번
    static void getPresentInfo() {
//        System.out.print("500번 명령 수행 : ");
        int p_num = Integer.parseInt(st.nextToken());
        int b_idx = 0;      // 선물 p 가 올려져 있는 벨트 인덱스

        int a = 0;
        int b = 0;

        for (int i = 1; i <= N; i++) {
            if (beltList[i].contains(p_num)) {
                b_idx = i;
                break;
            }
        }

        int size = beltList[b_idx].size();
        Object[] arr = beltList[b_idx].toArray();

        // p_num 밖에 없는 경우 (== 맨 앞이자 맨 뒤인 경우) -> 앞뒤가 다없음
        if (size == 1) {
            a = -1;
            b = -1;
        }
        // p_num 이 맨 앞인 경우 -> 앞의 선물이 없음
        if (beltList[b_idx].peekFirst() == p_num) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].equals(p_num)) {
                    b = Integer.parseInt(arr[i+1].toString());
                    break;
                }
            }
            a = -1;
        }
        // p_num 이 맨 뒤인 경우 -> 뒤의 선물이 없음
        else if (beltList[b_idx].peekLast() == p_num) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i].equals(p_num)) {
                    a = Integer.parseInt(arr[i-1].toString());
                    break;
                }
            }
            b = -1;
        }
        // 그 외
        else {
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i].equals(p_num)) {
                    a = Integer.parseInt(arr[i-1].toString());
                    b = Integer.parseInt(arr[i+1].toString());
                    break;
                }
            }
        }
        System.out.println(a + (2 * b));
    }

    // 6번
    static void getBeltInfo() {
//        System.out.print("600번 명령 수행 : ");
        int b_num = Integer.parseInt(st.nextToken());

        Object[] arr = beltList[b_num].toArray();

        int a = 0;
        int b = 0;
        int c = beltList[b_num].size();

        if (c == 0) {
            a = -1;
            b = -1;
        } else {
            a = beltList[b_num].peekFirst();
            b = beltList[b_num].peekLast();
        }

        System.out.println(a + (2*b) + (3*c));
    }

}
