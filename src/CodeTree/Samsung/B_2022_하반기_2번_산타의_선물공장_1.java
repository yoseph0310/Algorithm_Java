package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_2022_하반기_2번_산타의_선물공장_1 {

    /**
     * 1. 공장 설립.
     *      - m 개의 벨트
     *      - 벨트 위에 n/m 개의 물건이 놓인다. 총 n 개의 물건을 준비. ( m = 3, n = 12 ) 이면 벨트에는 4개가 올라간다.
     *      - 물건 : 고유 번호(ID), 무게(W). 번호는 모두 다름. 무게는 같을 수 있다.
     *
     * 2. 물건 하차
     *      - 물건의 최대 무게 w_max 가 주어짐
     *      - 1 ~ m 까지 벨트를 보면서 각 벨트의 맨 앞에 있는 선물 중 해당 선물 무게가 w_max 보다 작으면 하차. w_max 보다 크거나 같으면 맨 뒤로 보냄.
     *      - 벨트에 있던 상자가 빠지면 한 칸씩 앞으로 내려와야 함.
     *      - 하차된 상자 무게의 합을 출력한다.
     *
     * 3. 물건 제거
     *      - 물건의 고유 번호 r_id 가 주어짐
     *      - 해당 고유 번호에 해당하는 상자가 놓여있는 벨트가 있으면, 해당 벨트에서 상자를 제거하고 뒤에 있던 상자들은 앞으로 한칸씩 내려온다.
     *      - r_id 인 상자가 있으면 r_id, 아니면 -1 출력
     *
     * 4. 물건 확인
     *      - 물건의 고유 번호 f_id 가 주어짐
     *      - 해당 고유 번호에 해당하는 상자가 놓여있는 벨트가 있으면, 해당 벨트 번호 출력, 없으면 -1 출력
     *      - 해당 상자가 있다면 그 상자 위의 상자를 순서를 지키면서 전부 앞으로 가져온다.
     *
     * 5. 벨트 고장
     *      - 벨트 번호 b_num 이 주어진다. b_num 벨트가 고장나면 해당 벨트는 사용 불가.
     *      - b_num 벨트의 바로 오른쪽부터 순서대로 확인하면서 고장나지 않은 최초의 벨트 위로 b_num 의 상자들을 아래서부터 하나씩 옮긴다.
     *      - 만약 m 까지 봤는데 고장나지 않은게 없으면 다시 1번부터 확인.
     *      - 이 명령이 주어졌을 때는 최소 하나 이상의 벨트는 정상임. 모두 고장나는 경우는 없다.
     *      - 벨트가 이미 망가져 있었으면 -1, 그렇지 않았으면 정상적으로 처리했다는 뜻으로 b_num 출력
     *
     *
     * Q 번에 걸쳐 순서대로 진행하면서 원하는 결과를 출력.
     */
    static final int MAX_M = 10;
    static int N, M;

    // ID 별로 상자 무게 저장
    static HashMap<Integer, Integer> weightMap = new HashMap<>();

    // ID 에 해당하는 상자의 next 와 prev 값을 관리
    // 0 이면 없다는 뜻
    static HashMap<Integer, Integer> prevMap = new HashMap<>();
    static HashMap<Integer, Integer> nextMap = new HashMap<>();

    // 각 벨트별 head, tail ID 관리
    static int[] headArr = new int[MAX_M];
    static int[] tailArr = new int[MAX_M];

    // 망가진 벨트 표시
    static boolean[] brokenBeltArr = new boolean[MAX_M];

    // 물건 별로 벨트 번호 관리
    // 벨트 번호 0 이면 사라진 것
    static HashMap<Integer, Integer> beltNumMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            switch (command) {
                case 100:
                    operation1(st);
                    break;
                case 200:
                    sb.append(operation2(st)).append('\n');
                    break;
                case 300:
                    sb.append(operation3(st)).append('\n');
                    break;
                case 400:
                    sb.append(operation4(st)).append('\n');
                    break;
                case 500:
                    sb.append(operation5(st)).append('\n');
                    break;

            }
        }

        System.out.println(sb);
    }

    // 공장 설립
    static void operation1(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());       // 선물 개수
        M = Integer.parseInt(st.nextToken());       // 벨트 개수

        int[] ids = new int[N];
        int[] ws = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            ws[i] = Integer.parseInt(st.nextToken());
        }

        // ID 마다의 무게를 관리
        for (int i = 0; i < N; i++) {
            weightMap.put(ids[i], ws[i]);
        }

        // 벨트 별 상자 목록 관리
        int size = N / M;
        for (int i = 0; i < M; i++) {
            headArr[i] = ids[i * size];
            tailArr[i] = ids[(i + 1) * size - 1];
            for (int j = i * size; j < (i+1) * size; j++) {
                // 상자 ID 마다 벨트 번호 기입
                beltNumMap.put(ids[j], i + 1);

                // next, prev 설정
                if (j < (i + 1) * size - 1) {
                    nextMap.put(ids[j], ids[j + 1]);
                    prevMap.put(ids[j + 1], ids[j]);
                }
            }
        }

    }

    // 물건 하차
    static int operation2(StringTokenizer st) {
        int w_max = Integer.parseInt(st.nextToken());

        int w_sum = 0;

        // TODO 1 ~ M 번 까지 순서대로 벨트를 보면서 각 벨트의 맨 앞(head)의 선물중 w_max 이하면 하차. 아니면 맨 뒤로 보낸다. 벨트에서 상자 빠지면 한칸씩 내려와야함
        for (int i = 0; i < M; i++) {
            if (brokenBeltArr[i]) {
                continue;
            }
            // 벨트 head 확인
            if (headArr[i] != 0) {
                int id = headArr[i];
                int w = weightMap.get(id);

                // w_max 이하면 하차 시키고 무게 더함
                if (w <= w_max) {
                    w_sum += w;

                    removeId(id, true);
                }
                // 그렇지 않다면 맨 뒤로 옮김
                else if (nextMap.get(id) != 0){
                    removeId(id, false);

                    pushId(tailArr[i], id);
                }
            }
        }

        // TODO 하차된 상자 무게 총 합 반환
        return w_sum;
    }

    static void pushId(int targetId, int id) {
        nextMap.put(targetId, id);
        prevMap.put(id, targetId);

        int b_num = beltNumMap.get(targetId) - 1;
        if (tailArr[b_num] == targetId) {
            tailArr[b_num] = id;
        }
    }

    static void removeId(int id, boolean removeBelt) {
        int b_num = beltNumMap.get(id) - 1;

        // 벨트 번호 제거
        if (removeBelt) beltNumMap.put(id, 0);

        // 하나 남았을 경우
        if (headArr[b_num] == tailArr[b_num]) {
            headArr[b_num] = tailArr[b_num] = 0;
        }
        // 제거 하려는 id 가 b_num 맨 앞일 경우
        else if (id == headArr[b_num]) {
            int nextId = nextMap.get(id);
            headArr[b_num] = nextId;
            prevMap.put(nextId, 0);
        }
        // 제거 하려는 id 가 b_num 맨 뒤일 경우
        else if (id == tailArr[b_num]) {
            int prevId = prevMap.get(id);
            tailArr[b_num] = prevId;
            nextMap.put(prevId, 0);
        }
        // 중간에 있는게 삭제 될 경우
        else {
            int prevId = prevMap.get(id);
            int nextId = nextMap.get(id);

            nextMap.put(prevId, nextId);
            prevMap.put(nextId, prevId);
        }

        // next, prev 값 삭제
        nextMap.put(id, 0);
        prevMap.put(id, 0);
    }

    // 물건 제거
    static int operation3(StringTokenizer st) {
        int r_id = Integer.parseInt(st.nextToken());

        // 이미 삭제된 상자라면 -1 출력 후 패스
        if (beltNumMap.getOrDefault(r_id, 0) == 0) {
            return -1;
        }

        // 해당 상자 제거
        removeId(r_id, true);
        return r_id;
    }

    // 물건 확인
    static int operation4(StringTokenizer st) {
        int f_id = Integer.parseInt(st.nextToken());

        // 이미 삭제된 상자라면 -1 출력 후 패스
        if (beltNumMap.getOrDefault(f_id, 0) == 0) {
            return -1;
        }

        // 해당 상자를 찾아 이를 맨 앞으로 당긴다. head 가 아닌 경우에만 유효
        int b_num = beltNumMap.get(f_id) - 1;
        if (headArr[b_num] != f_id) {
            int originTail = tailArr[b_num];
            int originHead = headArr[b_num];

            // tail 갱신
            int curTail = prevMap.get(f_id);
            tailArr[b_num] = curTail;
            nextMap.put(curTail, 0);

            // 기존 tail 의 next 를 head 로, head 의 prev 를 기존 tail 로 만듬
            nextMap.put(originTail, originHead);
            prevMap.put(originHead, originTail);

            // 새로 head 를 지정
            headArr[b_num] = f_id;
        }

        return b_num + 1;
    }

    // 벨트 고장
    static int operation5(StringTokenizer st) {
        int b_num = Integer.parseInt(st.nextToken());

        b_num--;

        if (brokenBeltArr[b_num]) {
            return -1;
        }



        brokenBeltArr[b_num] = true;

        if (headArr[b_num] == 0) {
            return b_num + 1;
        }

        int nextNum = b_num;
        while (true) {
            nextNum = (nextNum + 1) % M;

            // 최초로 망가지지 않은 벨트라면
            if (!brokenBeltArr[nextNum]) {
                // 벨트가 비어 있으면 그대로 옮긴다.
                if (tailArr[nextNum] == 0) {
                    headArr[nextNum] = headArr[b_num];
                    tailArr[nextNum] = tailArr[b_num];
                }
                // 그렇지 않으면 맨 뒤에 머리부터 넣어주고 tail 을 바꿔준다.
                else {
                    pushId(tailArr[nextNum], headArr[b_num]);
                    tailArr[nextNum] = tailArr[b_num];
                }

                int id = headArr[b_num];

                while (id != 0) {
                    beltNumMap.put(id, nextNum + 1);
                    id = nextMap.getOrDefault(id, 0);
                }

                headArr[b_num] = tailArr[b_num] = 0;
                break;
            }
        }

        return b_num + 1;
    }

}
