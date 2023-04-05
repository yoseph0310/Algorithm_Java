package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class A_2022_하반기_2번_산타의_선물공장_2_정답 {

    static int N, M;

    static Map<Integer, Node> pInfoMap;
    static Node[] head;         // 1 ~ N 까지 벨트의 첫번째 선물들
    static Node[] tail;         // 1 ~ N 까지 벨트의 마지막 선물들R
    static int[] size;          // 1 ~ N 까지 벨트의 사이즈
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
        }
    }

    // 1번
    static void buildFactory() {
//        System.out.println("100번 명령 수행");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pInfoMap = new HashMap<>();
        head = new Node[N + 1];
        tail = new Node[N + 1];
        size = new int[N + 1];

        List<Node>[] temp = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            temp[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            int b_num = Integer.parseInt(st.nextToken());

            Node node = new Node(i);
            temp[b_num].add(node);
            pInfoMap.put(i, node);
        }

        for (int i = 1; i <= N; i++) {

            size[i] = temp[i].size();
            for (int idx = 0; idx < size[i]; idx++) {
                Node now = temp[i].get(idx);

                // 맨 앞
                if (idx == 0) {
                    head[i] = now;
                    if (idx + 1 < size[i])
                        now.next = temp[i].get(idx + 1);
                }

                // 맨 뒤
                if (idx == size[i] - 1) {
                    tail[i] = now;
                    if (idx - 1 >= 0) {
                        now.prev = temp[i].get(idx - 1);
                    }
                }

                // 맨 앞과 맨 뒤가 아닌 것들
                if (0 < idx && idx < size[i] - 1) {
                    now.next = temp[i].get(idx + 1);
                    now.prev = temp[i].get(idx - 1);
                }

            }
        }

    }

    // 2번
    static void moveAllPresent() {
//        System.out.print("200번 명령 수행 : ");
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        if (head[src] == null) {
            System.out.println(size[dst]);
            return;
        }

        if (head[dst] != null) {
            tail[src].next = head[dst];
            head[dst].prev = tail[src];
            head[dst] = head[src];
            tail[src] = tail[dst];
        }

        head[dst] = head[src];
        tail[dst] = tail[src];

        head[src] = tail[src] = null;

        size[dst] += size[src];
        size[src] = 0;

        System.out.println(size[dst]);
    }

    // 3번
    static void moveFrontPresent() {
//        System.out.print("300번 명령 수행 : ");
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        // src 와 dst 둘 다 물건이 없으면
        if (head[src] == null && head[dst] == null) {
            System.out.println(0);
            return;
        }

        // 둘 중 하나만 없을 때
        if (head[src] == null || head[dst] == null) {
            int exist = 0, empty = 0;
            // src 가 없을 때
            if (head[src] == null) {
                empty = src;
                exist = dst;
            }
            // dst 가 없을 때
            else {
                empty = dst;
                exist = src;
            }

            // 있는 쪽 것을 빼서 없는 쪽에 넣는다.
            push(poll(exist), empty);
        }
        // 둘 다 있을 때
        else {
            // 서로 빼서 서로 넣어준다.
            Node srcNode = poll(src);
            Node dstNode = poll(dst);

            push(srcNode, dst);
            push(dstNode, src);
        }

        System.out.println(size[dst]);
    }

    static void push(Node newNode, int b_num) {
        if (head[b_num] == null) {
            head[b_num] = tail[b_num] = newNode;
        } else {
            newNode.next = head[b_num];
            head[b_num].prev = newNode;
            head[b_num] = newNode;
        }
        size[b_num]++;
    }

    static Node poll(int b_num) {
        if (head[b_num] == null) return null;

        Node pollNode = head[b_num];
        if (head[b_num] == tail[b_num]) {
            head[b_num] = tail[b_num] = null;
        } else {
            head[b_num] = head[b_num].next;
            head[b_num].prev = null;
        }

        pollNode.next = null;
        pollNode.prev = null;
        size[b_num]--;

        return pollNode;
    }

    // 4번
    static void dividePresent() {
//        System.out.print("400번 명령 수행 : ");
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        int idx = size[src] / 2;

        Node[] removed = new Node[idx];
        for (int i = 0; i < idx; i++) {
            removed[i] = poll(src);
        }
        for (int i = idx - 1; i >= 0 ; i--) {
            push(removed[i], dst);
        }

        System.out.println(size[dst]);
    }

    // 5번
    static void getPresentInfo() {
//        System.out.print("500번 명령 수행 : ");
        int p_num = Integer.parseInt(st.nextToken());

        Node now = pInfoMap.get(p_num);
        int a = now.prev == null ? -1 : now.prev.num;
        int b = now.next == null ? -1 : now.next.num;

        System.out.println(a + (2 * b));
    }

    // 6번
    static void getBeltInfo() {
//        System.out.print("600번 명령 수행 : ");
        int b_num = Integer.parseInt(st.nextToken());

        int a = head[b_num] == null ? -1 : head[b_num].num;
        int b = tail[b_num] == null ? -1 : tail[b_num].num;
        int c = size[b_num];

        System.out.println(a + (2 * b) + (3 * c));
    }

    static class Node {
        int num;
        Node prev;
        Node next;

        public Node(int num) {
            this.num = num;
        }
    }
}
