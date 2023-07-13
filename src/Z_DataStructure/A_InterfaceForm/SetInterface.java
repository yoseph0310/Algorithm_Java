package Z_DataStructure.A_InterfaceForm;

/**
 *
 * 자바 Set Interface.
 * Set 은 HashSet, LinkedHashSet, TreeSet 에 의해 구현된다.
 *
 * @param <E> the type of elements in this Set
 */
public interface SetInterface<E> {

    /**
     * 지정된 요소가 Set 에 없는 경우 요소를 추가한다.
     *
     * @param e 집합에 추가할 요소
     * @return {@code true} 만약 Set 에 지정 요소가 포함되어있지 않아 정상적으로 추가되었을 경우,
     *          else, {@code false}
     */
    boolean add(E e);

    /**
     * 지정된 요소가 Set 에 있는 경우 해당 요소를 삭제한다.
     *
     * @param o 집합에서 삭제할 요소
     * @return {@code true} 만약 Set 에 지정 요소가 포함되어 정상적으로 삭제되었을 경우,
     *          else, {@code false}
     */
    boolean remove(Object o);

    /**
     * 현재 집합에 특정 요소가 포함되었는지를 확인한다.
     *
     * @param o 집합에서 찾을 요소
     * @return {@code true} Set 에 지정 요소가 포함되어 있을 경우,
     *          else, {@code false}
     */
    boolean contains(Object o);

    /**
     * 지정된 객체가 현재 집합과 같은 여부를 반환한다.
     *
     * @param o 집합과 비교할 객체
     * @return  {@code true} 비교할 집합과 동일한 경우,
     *          else, {@code false}
     */
    boolean equals(Object o);

    /**
     * 현재 집합이 빈 상태인지 확인
     * @return  {@code true} 비어있는 경우,
     *          else, {@code false}
     */
    boolean isEmpty();

    /**
     * 집합의 요소 개수를 반환한다. 만약 들어있는 요소가
     * {@code Integer.MAX_VALUE}를 넘을 경우 {@code Integer.MAX_VALUE}를 반환한다.
     *
     * @return 집합에 들어있는 요소 개수 반환
     */
    int size();

    /**
     * 집합의 모든 요소를 제거한다.
     * 이 작업을 수행하면 빈 집합이 된다.
     */
    void clear();
}
