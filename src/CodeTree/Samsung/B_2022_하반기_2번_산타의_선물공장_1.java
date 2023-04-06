package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

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

    // 벨트의 최대는 10.
    static final int MAX_M = 10;
    static int N, M, Q;

    // 물건이 어느 벨트에 있는지 관리. <물건의 num, 물건이 위치한 belt>
    static HashMap<Integer, Integer> beltMap = new HashMap<>();
    // 각 물건들의 다음 물건 정보 관리. 0이면 없는 것. <물건의 num, 다음 물건의 num>
    static HashMap<Integer, Integer> nextMap = new HashMap<>();
    // 각 물건들의 이전 물건 정보 관리. 0이면 없는 것. <물건의 num, 이전 물건의 num>
    static HashMap<Integer, Integer> prevMap = new HashMap<>();
    // 각 물건들의 무게 정보 관라. <물건의 num, 물건의 무게>
    static HashMap<Integer, Integer> weightMap = new HashMap<>();

    static boolean[] brokenBelt = new boolean[MAX_M];

    static int[] heads = new int[MAX_M];
    static int[] tails = new int[MAX_M];

    // 1. 공장 설립
    static void operation1(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] idxArr = new int[N];
        int[] wArr = new int[N];

        for (int i = 0; i < N; i++) {
            idxArr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            wArr[i] = Integer.parseInt(st.nextToken());
        }

        // 각 상자의 무게 정보 저장
        for (int i = 0; i < N; i++) {
            weightMap.put(idxArr[i], wArr[i]);
        }

        int size = N / M;
        // head, tail 정보 저장 및 벨트 정보 저장 및 다음 이전 정보 저장
        for (int i = 0; i < M; i++) {
            heads[i] = idxArr[i * size];
            tails[i] = idxArr[(i + 1) * size - 1];
            for (int j = i * size; j < (i+1) * size; j++) {
                beltMap.put(idxArr[j], i + 1);

                if (j < (i + 1) * size - 1) {
                    nextMap.put(idxArr[j], idxArr[j + 1]);
                    prevMap.put(idxArr[j + 1], idxArr[j]);
                }
            }
        }
    }
    // 2. 물건 하차
    static int operation2(StringTokenizer st) {
        int w_max = Integer.parseInt(st.nextToken());

        int w_sum = 0;

        // 1 ~ M 까지 순서대로 벨트를 보며 맨 앞에 있는 선물 중 w_max 이하 라면
        for (int i = 0; i < M; i++) {
            if (brokenBelt[i]) continue;

            // 0 이면 없는 것.
            if (heads[i] != 0) {
                int idx = heads[i];
                int w = weightMap.get(idx);

                if (w <= w_max) {
                    // TODO 하차
                    w_sum += w;

                    remove(idx, true);
                }
                // 지금 원소가 tail 일 수도 있음
                else if (nextMap.get(idx) != 0){
                    // TODO 맨 뒤로 보낸다.
                    remove(idx, false);
                    // 현재 물건(head)을 tail 로 보낸다.
                    push(idx, tails[i]);
                }
            }
        }

        // TODO 하차된 상자 무게 총합 반환
        return w_sum;
    }

    static void remove(int idx, boolean removeThisFromBelt) {
        int b_num = beltMap.get(idx) - 1;

        // 벨트 번호 제거
        if (removeThisFromBelt) beltMap.put(idx, 0);

        // 벨트에 원소 하나인 경우
        if (heads[b_num] == tails[b_num]) {
            heads[b_num] = tails[b_num] = 0;
        }
        // 제거하려는 원소가 맨 앞인 경우
        else if (idx == heads[b_num]) {
            int nextIdx = nextMap.get(idx);
            heads[b_num] = nextIdx;
            prevMap.put(nextIdx, 0);
        }
        // 제거하려는 원소가 맨 뒤인 경우
        else if (idx == tails[b_num]) {
            int prevIdx = prevMap.get(idx);
            tails[b_num] = prevIdx;
            nextMap.put(prevIdx, 0);
        }
        // 중간에 있는 경우
        else {
            int nextIdx = nextMap.get(idx);
            int prevIdx = prevMap.get(idx);

            nextMap.put(prevIdx, nextIdx);
            prevMap.put(nextIdx, prevIdx);
        }

        nextMap.put(idx, 0);
        prevMap.put(idx, 0);
    }

    static void push(int idx, int targetIdx) {
        // TODO idx 를 targetIdx 뒤로 보낼 것이다.
        nextMap.put(targetIdx, idx);
        prevMap.put(idx, targetIdx);

        int b_num = beltMap.get(targetIdx) - 1;
        if (tails[b_num] == targetIdx) {
            tails[b_num] = idx;
        }
    }

    // 3. 물건 제거
    static int operation3(StringTokenizer st) {
        int r_id = Integer.parseInt(st.nextToken());

        // 이미 삭제 됐으면 -1 반환
        if (beltMap.getOrDefault(r_id, 0) == 0) {
            return -1;
        }

        remove(r_id, true);
        return r_id;
    }
    // 4. 물건 확인
    static int operation4(StringTokenizer st) {
        int f_id = Integer.parseInt(st.nextToken());

        if (beltMap.getOrDefault(f_id, 0) == 0) {
            return -1;
        }

        int b_num = beltMap.get(f_id) - 1;
        // 맨 앞으로 가져오므로 맨 앞이 아닌 경우만 해당
        if (heads[b_num] != f_id) {
            int originHead = heads[b_num];
            int originTail = tails[b_num];

            // tail 을 f_id의 이전으로 바꾸고 f_id의 이전 노드는 없다고 바꾼다.
            int newTail = prevMap.get(f_id);
            tails[b_num] = newTail;
            nextMap.put(newTail, 0);

            // 원래 tail 의 다음으로 원래 head 를 가리키게 하고 원래 head 의 이전 노드로 원래 tail 로 바꾼다.
            nextMap.put(originTail, originHead);
            prevMap.put(originHead, originTail);

            // 새로운 헤드로 f_id 로 설정한다.
            heads[b_num] = f_id;
        }

        return b_num + 1;
    }
    // 5. 벨트 고장
    static int operation5(StringTokenizer st) {
        int b_num = Integer.parseInt(st.nextToken()) - 1; // -> 반환할 때는 +1 해줘야함 !!

        if (brokenBelt[b_num]) {
            return -1;
        }

        brokenBelt[b_num] = true;

        if (heads[b_num] == 0) {
            return b_num + 1;
        }

        int nextNum = b_num;
        while (true) {
            nextNum = (nextNum + 1) % M;

            // 벨트가 망가지지 않았다면
            if (!brokenBelt[nextNum]) {

                // 벨트가 비어 있으면 그대로 옮긴다. b_num -> 에서 nextNum 으로
                if (tails[nextNum] == 0) {
                    heads[nextNum] = heads[b_num];
                    tails[nextNum] = tails[b_num];
                }
                // 그렇지 않으면 맨 뒤로 머리넣고 tail 을 바꿔준다.
                else {
                    push(heads[b_num], tails[nextNum]);
                    tails[nextNum] = tails[b_num];
                }

                // belt 정보를 nextNum 으로 바꾸고 현재 벨트를 모두 없는 것으로 처리하고 멈춘다.
                int idx = heads[b_num];

                while (idx != 0) {
                    beltMap.put(idx, nextNum + 1);
                    idx = nextMap.getOrDefault(idx, 0);
                }

                heads[b_num] = tails[b_num] = 0;
                break;
            }

        }

        return b_num + 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

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

}