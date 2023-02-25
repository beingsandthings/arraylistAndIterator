package linkedList;

import iterator.MyIterator;

public interface LinkedList<E> {
    //
    int size();
    boolean empty();
    int indexOf(Object object); // 추가
    boolean contains(Object object);
    MyIterator<E> iterator();
    void add(E element);
    void add(int index, E element); // 임의로 추가
    E get(int index);
    void remove(Object object);
    void remove(int index);
    void addAll(LinkedList<? extends E> collection);
    void clear();
    <T> T[] toArray(T[] some);
}
