package Z_DataStructure.A_InterfaceForm;

/**
 * Java Queue Interface 이다.
 * Queue 는 ArrayQueue, LinkedQueue, Deque, PriorityQueue 에 의해 구현된다.
 *
 * @param <E> the type of elements in this Queue
 */

public interface QueueInterface<E> {
    /**
     * 큐의 가장 마지막에 요소를 추가한다.
     *
     * @param e 큐에 추가할 요소
     * @return 큐에 요소가 정상적으로 추가되었을 경우 true 를 반환
     */
    boolean offer(E e);

    /**
     * 큐의 첫 번째 요소를 삭제하고 삭제된 요소를 반환한다.
     *
     * @return 큐의 삭제된 요소 반환
     */
    E poll();

    /**
     * 큐의 첫 번째 요소를 반환한다.
     *
     * @return 큐의 첫 번째 요소 반환.
     */
    E peek();
}
