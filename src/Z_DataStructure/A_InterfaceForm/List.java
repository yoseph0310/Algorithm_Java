package Z_DataStructure.A_InterfaceForm;

/**
 * Java List Interface
 * List는 ArrayList, SinglyLinkedList, DoublyLinkedList에 의해 각각 구현된다.
 */

public interface List<E> {

    /**
     * 리스트에 요소를 추가한다.
     *
     * @param value 리스트에 추가할 요소
     * @return 리스트에서 중복을 허용하지 않을 경우에 리스트에
     *         중복되는 요소가 있을 경우 {@code false} 를 반환하고
     *         중복되는 요소가 없다면 {@code true}를 반환
     */
    boolean add(E value);

    /**
     * 리스트에 요소를 특정 위치에 추가한다.
     * 특정 위치 및 이후의 요소들은 한 칸씩 뒤로 밀린다.
     *
     * @param index index 리스트에 요소를 추가할 특정 위치 변수
     * @param value 리스트에 추가할 요소
     */
    void add(int index, E value);

    /**
     * 리스트의 index 위치에 있는 요소를 삭제한다.
     *
     * @param index index 리스트에서 삭제할 위치 변수
     * @return 삭제된 요소를 반환
     */
    E remove(int index);

    /**
     * 리스트에서 특정 요소를 삭제한다.
     * 동일한 요소가 여러 개일 경우 가장 처음 발견한 요소만 삭제된다.
     *
     * @param value 리스트에서 삭제할 요소
     * @return 리스트에 삭제할 요소가 없거나 삭제에 실패할 경우 {@code false}를 반환,
     *         성공할 경우 {@code true} 를 반환.
     */
    boolean remove(Object value);

    /**
     * 리스트에 있는 특정 위치의 요소를 반환
     *
     * @param index 리스트에 접근할 위치 변수
     * @return 리스트의 index 위치에 있는 요소 반환
     */
    E get(int index);

    /**
     * 리스트에서 특정 위치에 있는 요소를 새 요소로 대체한다.
     *
     * @param index 리스트에 접근할 위치 변수
     * @param value 새로 대체할 요소 변수
     */
    void set(int index, E value);

    /**
     * 리스트에 특정 요소가 있는지 여부를 확인한다.
     *
     * @param value 리스트에서 찾을 특정 요소 변수
     * @return 리스트에 특정 요소가 존재할 경우 {@code true}, 없을 경우 {@code false} 반환
     */
    boolean contains(Object value);

    /**
     * 리스트에 특정 요소가 몇 번째 위치에 있는지를 반환한다.
     *
     * @param value 리스트에서 위치를 찾을 요소 변수
     * @return 리스트에서 처음으로 요소와 일치하는 위치를 반환
     *         만약 일치하는 요소가 없다면 -1 반환
     */
    int indexOf(Object value);

    /**
     * 리스트에 있는 요소의 개수를 반환한다.
     *
     * @return 리스트에 있는 요소 개수 반환
     */
    int size();

    /**
     * 리스트에 요소가 비어있는지를 반환한다.
     *
     * @return 리스트에 요소가 없을 경우 {@code true}, 있을 경우 {@code false} 반환
     */
    boolean isEmpty();

    /**
     * 리스트에 있는 모든 요소 모두 삭제.
     */
    public void clear();
}
