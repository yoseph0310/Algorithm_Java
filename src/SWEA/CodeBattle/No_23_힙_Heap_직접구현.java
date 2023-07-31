package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    힙 : 부모 노드의 키값이 자식 노드의 키값보다 항상 크거나 같은 '최대 힙' 항상 작거나 같은 '최소 힙'

    command 1 : 자연수 x 삽입
    command 2 : 최대 힙의 루트 노드 키 값 출력 후, 해당 노드 삭제

    연산 2의 결과를 공백을 두고 출력한다.
 */
public class No_23_힙_Heap_직접구현 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb = new StringBuilder();

            Heap heap = new Heap();

            sb.append("#").append(t).append(" ");

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int command = Integer.parseInt(st.nextToken());
                int x = 0;
                // 1 연산일 경우 삽입할 x 를 입력 받는다.
                if (command == 1) {
                    x = Integer.parseInt(st.nextToken());
                    heap.add(x);
                } else {
                    sb.append(heap.poll()).append(" ");
                }
            }



            System.out.println(sb);
        }
    }

    static class Heap {
        private final int DEFAULT_CAPACITY = 5;
        private int[] array;
        private int size;

        public Heap() {
            this.array = new int[DEFAULT_CAPACITY];
            this.size = 0;
        }

        private int getParent(int idx) {
            return idx / 2;
        }

        private int getLeftChild(int idx) {
            return idx * 2;
        }

        private int getRightChild(int idx) {
            return (idx * 2) + 1;
        }

        private void resize(int capacity) {
            int[] newArray = new int[capacity];

            for (int i = 0; i <= size; i++) {
                newArray[i] = array[i];
            }

            this.array = null;
            this.array = newArray;
        }

        // 힙에 원소를 추가
        public void add(int num) {
            // 만약 현재 원소 개수가 배열 용적보다 크거나 같다면 resize 한다.
            if (size + 1 == array.length) {
                resize(array.length * 2);
            }

            // 다음 인덱스에 num 을 추가한다.
            array[size + 1] = num;
            siftUp(size + 1, num);
            size++;
        }

        private void siftUp(int idx, int value) {
            // 최하위 인덱스의 값을 부모 인덱스보다 작을때까지 올려준다.
            while (idx > 1) {
                int parentIdx = getParent(idx);         // 삽입된 노드의 부모노드 idx
                int parentValue = array[parentIdx];     // 삽입된 노드의 부모노드 value

                // parentValue 가 삽입된 노드 value 보다 크다면 반복문을 종료한다.
                if (value < parentValue) break;

                /*
                    부모노드 value 가 삽입된 노드 value 보다 작다면
                    삽입된 위치에 부모 노드 값을 넣고
                    idx 를 부모노드 idx 로 변경한다.
                 */
                array[idx] = parentValue;
                idx = parentIdx;
            }

            array[idx] = value;
        }

        // 힙의 원소를 삭제
        public int poll() {
            if (array[1] == 0) {
                return -1;
            }

            int result = array[1];      // 삭제 ( 반환 ) 될 요소
            int target = array[size];   // 타겟이 될 요소

            if (size == 1) {
                array[1] = 0;
                return result;
            }
            siftDown(1, target);

            return result;
        }

        private void siftDown(int idx, int target) {
            array[idx] = 0;
            size--;

            int parentIdx = idx;
            int childIdx;

            // 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 동안만 반복
            while ((childIdx = getLeftChild(parentIdx)) < size) {
                int rightIdx = getRightChild(parentIdx);         // 오른쪽 자식 인덱스
                int childVal = array[childIdx];        // 왼쪽 자식 값

                /*
                    오른쪽 자식 인덱스가 size 를 넘지 않으면서
                    재배치할 노드는 값이 더 큰 자식과 비교해야 하므로
                    child 와 childVal 을 더 큰 자식 값으로 바꾼다.
                 */
                if (rightIdx <= size && childVal < array[rightIdx]) {
                    childIdx = rightIdx;
                    childVal = array[childIdx];
                }

                // 재배치할 노드가 자식 노드보다 작을 경우 반복문 종료
                if (target > childVal) break;

                array[parentIdx] = childVal;
                parentIdx = childIdx;
            }

            array[parentIdx] = target;


            if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
                resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
            }
        }


    }


}
