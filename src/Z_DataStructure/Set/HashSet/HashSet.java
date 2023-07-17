package Z_DataStructure.Set.HashSet;

import Z_DataStructure.A_InterfaceForm.SetInterface;

import java.util.Arrays;

public class HashSet<E> implements SetInterface<E> {

    // 최소 기본 용적이며 2^n 형태가 좋음
    private final static int DEFAULT_CAPACITY = 1 << 4;

    // 3/4 이상 채워질 경우 resize 하기 위함
    private final static float LOAD_FACTOR = 0.75f;

    Node<E>[] table;      // 요소의 정보를 담고 있는 Node를 저장할 Node 타입 배열
    private int size;   // 요소의 개수

    @SuppressWarnings("unchecked")
    public HashSet() {
        table = (Node<E> []) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // 보조 해시 함수 (상속 방지를 위해 private static final 선언)
    private static final int hash(Object key) {
        int hash;
        if (key == null) {
            return 0;
        } else {
            // hashCode() 의 경우 음수가 나올 수 있으므로 절댓값을 통해 양수로 변환한다.
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }

    public boolean add(E e) {
        // key(e) 에 대해 만들어뒀던 보조해시함수의 값과 key(데이터 e)를 보낸다.
        return add(hash(e), e) == null;
    }

    private E add(int hash, E key) {

        int idx = hash % table.length;

        // table[idx] 가 비어있을 경우 새 노드 생성
        if (table[idx] == null) {
            table[idx] = new Node<E>(hash, key, null);
        }

        /*
         * table[idx]에 요소가 이미 존재할 경우 (==해시 충돌)
         *
         * 두 가지 경우의 수 존재
         * 1. 객체가 같은 경우
         * 2. 객체는 같지 않으나 얻어진 index 가 같은 경우
         */
        else {
            Node<E> temp = table[idx];          // 현재 위치 노드
            Node<E> prev = null;                // temp 의 이전 노드

            // 첫 노드(table[idx]) 부터 탐색
            while (temp != null) {
                /*
                 * 만약 현재 노드의 객체가 같은 경우 (hash 값이 같으면서 key 가 같은 경우)는
                 * HashSet 은 중복을 허용하지 않으므로 key 를 반환한다.
                 * (key 가 같은 경우는 주소가 같거나 객체가 같은 경우가 존재)
                 */
                if ((temp.hash == hash) && (temp.key == key || temp.key.equals(key))) {
                    return key;
                }
                prev = temp;
                temp = temp.next;       // 다음 노드로 이동
            }

            // 마지막 노드에 새 노드를 연결
            prev.next = new Node<E>(hash, key, null);
        }

        size++;

        // 데이터 개수가 현재 table 용적의 75%를 넘는다면 용적을 늘려준다.
        if (size >= LOAD_FACTOR * table.length) {
            resize();
        }

        return null;        // 정상적으로 추가 되었을 경우 null 반환
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;

        // 기존 테이블의 두배 용적으로 생성
        final Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];

        // 0번째 인덱스부터 차례대로 순회
        for (int i = 0; i < table.length; i++) {
            // 각 인덱스의 첫번째 노드(head)
            Node<E> value = table[i];

            // 해당 값이 없을 경우 다음으로 넘어간다
            if (value == null) {
                continue;
            }

            table[i] = null;    // gc

            Node<E> nextNode;   // 현재 노드의 다음 노드를 가리키는 변수

            // 현재 인덱스에 연결된 노드들을 순회한다.
            while(value != null) {
                int idx = value.hash % newCapacity;     // 새로운 인덱스를 구한다.

                /*
                 * 새로 담을 index 에 요소가 존재할 경우
                 * == 새로 담을 newTable 에 index 값이 겹칠 경우 (해시 충돌)
                 */
                if (newTable[idx] != null) {
                    Node<E> tail = newTable[idx];

                    // 가장 마지막 노드로 간다.
                    while (tail.next != null) {
                        tail = tail.next;
                    }

                    /*
                     * 반드시 Value 가 참조하고 있는 다음 노드와의 연결을 끊어줘야 한다.
                     * 그렇지 않으면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하여 잘못된 참조 발생가능성이 있다.
                     */
                    nextNode = value.next;
                    value.next = null;
                    tail.next = value;
                }
                // 충돌되지 않는다면 ( 빈공간이라면 해당 노드를 추가 )
                else {
                    /*
                    * 반드시 Value 가 참조하고 있는 다음 노드와의 연결을 끊어줘야 한다.
                    * 그렇지 않으면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하여 잘못된 참조 발생가능성이 있다.
                    */
                    nextNode = value.next;
                    value.next = null;
                    newTable[idx] = value;
                }

                value = nextNode;       // 다음 노드로 이동
            }
        }
        table = null;
        table = newTable;       // 새로 생성한 table 을 table 변수에 연결
    }

    @SuppressWarnings("unchecked")
    private void resize_use_bit_op() {
        int oldCapacity = table.length;     // 기존 용적
        int newCapacity = oldCapacity << 1; // 새용적
        final Node<E> [] newTable = (Node<E> []) new Node[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            Node<E> data = table[i];
            if (data == null) continue;

            table[i] = null;        // gc

            // 데이터에 다음 노드가 없을 경우 (== 하나만 있을 경우)
            if (data.next == null) {
                /*
                    == data.hash % newCapacity
                    2^n 으로 인해 000..0100..00 에서 =1을 한 0001..0011..11 꼴로 만든 뒤
                    AND 연산에 의해 나머지 값이 구해짐
                 */
                newTable[data.hash & (newCapacity - 1)] = data;
                continue;
            }

            // 데이터가 두 개 이상 연결되어 있을 경우

            // 제자리 배치되는 노드(연결리스트)
            Node<E> lowHead = null;
            Node<E> lowTail = null;

            // 새로 배치되는 노드(연결리트스)
            Node<E> highHead = null;
            Node<E> highTail = null;

            /*
                재배치되는 노드는 원래 자리에 그대로 있거나
                원래 자리에 새로 늘어난 크기 만큼의 자리에 배치되거나 둘 중 하나이다.

                ex) oldCapacity = 4, newCapacity = 8

                만약 데이터가 index 2 에 위치했다고 하면
                    * oldTable -> index_of_data = 2

                사이즈가 두 배 늘어 새로운 테이블에 배치될 경우 두 가지 중 하나이다
                newTable -> index_of_data = 2 or 6 (2 + oldCapacity)

             */

            Node<E> next;       // 다음 노드를 가리키는 노드

            // 해당 인덱스에 연결된 모든 노드를 탐색
            do {
                next = data.next;

                // oldCapacity 의 몫이 짝수일 경우 == low 에 배치됨
                if ((data.hash & oldCapacity) == 0) {
                    // low 에 처음 배치되는 노드일 경우 해당 노드를 head 로 둔다.
                    if (lowHead == null) lowHead = data;
                    else lowTail.next = data;
                    lowTail = data; // lowTail = lowTail.next 와 같은 의미
                }
                /*
                    data.hash & oldCapacity != 0

                    oldCapacity = 4, newCapacity = 8 이고 재배치 하려는 index 가 2라면
                    2 or 6 에 위치하게 된다.

                    이때, 6에 위치하는 경우는 재배치 하기 전의 이전 사이즈였던 4에 대해
                    n % 4 = 2 이었을 때, n % 8 = 6 이 된다는 의미이다.
                    쉽게 말해 4로 나눈 몫이 홀수일 경우이다.

                    비트로 생각하면 4에 대응하는 비트가 1인 경우이다.
                    ex)
                    hash = 45 -> 0010 1101 (2)
                    oldCap = 4 -> 0000 0100 (2)
                    newCap = 8 -> 0000 1000 (2)

                    재배치 이전 index = (hash & (oldCap - 1))
                    0010 1101 (2) - 0000 0011(2) = 0000 0001(2) = index 1

                    재배치 이후 index = (hash & (newCap - 1))
                    0010 1101 (2) - 0000 0111(2) = 0000 0101(2) = index 5

                    2진법으로 볼때 새로운 위치에 배치되는 경우는
                    hash & oldCap = 0010 1101(2) & 0000 0100(2)
                    = 0000 0100(2) 로 0이 아닌 경우이다.
                 */
                else {
                    // high 에 처음 배치된다면 해당 노드가 head를 가리키도록 한다.
                    if (highHead == null) highHead = data;
                    else highTail.next =  data;
                    highTail = data;
                }

                data = next;        // 다음 노드로 넘어간다.
            } while (data != null);

            /*
                low 가 존재할 경우 low 의 head 를 원래 인덱스에 그대로 담아준다.
                이때 tail 에 해당되는 노드가 참조하고 있는 다음 노드와의 연결을 끊어야 한다.
                그렇지 않으면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하게 되어 잘못된 참조가 발생할 수 있다.
             */
            if (lowTail != null) {
                lowTail.next = null;
                newTable[i] = lowHead;
            }

            /*
                high 가 존재할 경우 high 의 head 를 (원래 인덱스 + 이전 용적) 값에 담아준다.
                이때 tail 에 해당되는 노드가 참조하고 있는 다음 노드와의 연결을 끊어야 한다.
                그렇지 않으면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하게 되어 잘못된 참조가 발생할 수 있다.
             */
            if (highTail != null) {
                highTail.next = null;
                newTable[i + oldCapacity] = highHead;
            }
        }
        table = newTable;       // 새 테이블을 연결해준다.
    }

    @Override
    public boolean remove(Object o) {
        // null 이 아니라는 것은 노드가 삭제되었다는 의미다.
        return remove(hash(o), o) != null;
    }

    private Object remove(int hash, Object key) {
        int idx = hash % table.length;

        Node<E> node = table[idx];
        Node<E> removedNode = null;
        Node<E> prev = null;

        if (node == null) {
            return null;
        }

        while (node != null) {
            // 같은 노드를 찾았다면
            if (node.hash == hash && (node.key == key || node.key.equals(key))) {
                removedNode = node; // 삭제되는 노드를 반환하기 위해 담아둔다.

                // 해당노드의 이전 노드가 존재하지 않는 경우 ( = head 노드인 경우)
                if (prev == null) {
                    table[idx] = node.next;
                    node = null;
                }
                // 그 외엔 이전 노드의 next 를 삭제할 노드의 다음노드와 연결해준다.
                else {
                    prev.next = node.next;
                    node = null;
                }
                size--;
                break;      // 삭제되었기 때문에 탐색 종료
            }

            prev = node;
            node = node.next;
        }

        return removedNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int idx = hash(o) % table.length;
        Node<E> temp = table[idx];

        /*
            같은 객체 내용이어도 hash 값은 다를 수 있다.
            하지만, 내용이 같은지를 알아보고 싶을 때 쓰는 메소드이므로
            hash 값은 따로 비교 하지 않아도 큰 지장은 없다.
            단 o 가 null 인지는 확인해야 한다.
         */
        while (temp != null) {
            // 같은 객체를 찾았을 경우 true 리턴
            if (o == temp.key || (o != null && (o.equals(temp.key)))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public void clear() {
        if (table != null && size > 0) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;        // 모든 노드 삭제
            }
            size = 0;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        // 만약 파라미터 객체가 현재 객체와 동일하다면 true
        if (o == this) {
            return true;
        }
        // 만약 o 객체가 HashSet 이 아닌 경우 false
        if (!(o instanceof HashSet)) {
            return false;
        }

        HashSet<E> oSet;

        /*
            Object 로부터 HashSet<E>로 캐스팅 되어야 비교가 가능하기 때문에
            만약 캐스팅이 불가능할 경우 ClassCastException 이 발생한다.
            이 경우 false 를 return 하도록 try-catch 문을 사용한다.
         */
        try {
            // HashSet 타입으로 캐스팅
            oSet = (HashSet<E>) o;
            // 사이즈(요소 개수)가 다르다는 것은 명백히 다른 객체다.
            if (oSet.size() != size) {
                return false;
            }

            for (int i = 0; i < oSet.table.length; i++) {
                Node<E> oTable = oSet.table[i];

                while (oTable != null) {
                    /*
                        서로 Capacity 가 다를 수 있기 때문에 index 에 연결된 원소들을
                        비교하는 것이 아닌 contains 로 원소의 존재 여부를 먼저 확인해야 한다.
                     */
                    if (!contains(oTable)) {
                        return false;
                    }
                    oTable = oTable.next;
                }
            }
        } catch (ClassCastException e) {
            return false;
        }

        // 위 검사가 모두 완료되면 같은 객체임이 증명됨
        return true;
    }

    public Object[] toArray() {
        if (table == null) return null;

        Object[] ret = new Object[size];
        int index = 0;

        for (int i = 0; i < table.length; i++) {
            Node<E> node = table[i];

            // 해당 인덱스에 연결된 모든 노드를 하나씩 담는다.
            while (node != null) {
                ret[index] = node.key;
                index++;
                node = node.next;
            }
        }

        return ret;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        Object[] copy = toArray();      // toArray()를 통해 먼저 배열을 얻는다.

        // 들어온 배열이 copy 된 요소 개수보다 작을 경우 size 에 맞게 늘려주면서 복사한다.
        if (a.length < size) {
            return (T[]) Arrays.copyOf(copy, size, a.getClass());
        }

        // 그 외에는 copy 배열을 a 에 0번째부터 채운다.
        System.arraycopy(copy, 0, a, 0, size);

        return a;
    }
}
