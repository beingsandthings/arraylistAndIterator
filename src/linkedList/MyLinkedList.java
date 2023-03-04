package linkedList;

import iterator.Iterator;
import iterator.MyIterator;
import java.util.Objects;

public class MyLinkedList<E> implements LinkedList<E>{
    //
    private Node<E> head;
    private Node<E> tail;
    private int length;

    public MyLinkedList() {
        //
        initialize();
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        Node(E item, Node<E> next) {
            this.element = item;
            this.next = next;
        }
    }

    @Override
    public int size() {
        //
        return length;
    }

    @Override
    public boolean empty() {
        //
        return length == 0;
    }

    @Override
    public int indexOf(Object object) {
        //
        Node<E> currentNode = head;
        Node<E> targetNode = null;

        for (int i=0; i<length; i++) {
            if(Objects.equals(currentNode.element, object)) {
                targetNode = currentNode;
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        //
        return indexOf(object) > 0;
    }

    @Override
    public Iterator<E> iterator() {
        //
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<E> {

        private Node<E> current;

        @Override
        public E next() {
            return (E) (current = current.next);
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        //

    }

    @Override
    public void add(E element) {
        //
        addLast(element);
    }

    public void addFirst(E element) {
        //
        Node<E> firstNode = head;

        Node<E> newNode = new Node<>(element, firstNode);

        length++;

        head = newNode;

        if (firstNode == null) {
            tail = newNode;
        }
    }

    public void addLast(E element) {
        //
        Node<E> last = tail;

        Node<E> newNode = new Node<>(element, null);

        length++;

        tail = newNode;

        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
    }

    @Override
    public void add(int index, E element) {
        //
        validateIndex(index);

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == length -1) {
            addLast(element);
            return;
        }

        Node<E> prevNode = getNode(index-1);
        Node<E> delNode = prevNode.next;
        Node<E> newNode = new Node<>(element, delNode);

        prevNode.next = newNode;
        length++;
    }

    @Override
    public E get(int index) {
        //
        validateIndex(index);

        return getNode(index).element;
    }

    @Override
    public void remove(Object object) {
        //
        Node<E> currNode = head;
        Node<E> prevNode = null;

        while( currNode != null) {
            if (Objects.equals(currNode.element, object)) {
                prevNode.next = currNode.next;
                length--;
                break;
            }
            prevNode = currNode;
            currNode = currNode.next;
        }
    }

    @Override
    public void remove(int index) {
        //
        validateIndex(index);

        if (index == 0) {
            removeFirst();
            return;
        }

        Node<E> prevNode = getNode(index-1);
        Node<E> delNode = prevNode.next;
        Node<E> nextNode = delNode.next;

        delNode.element = null;
        delNode.next = null;

        length--;

        prevNode.next = nextNode;
    }

    public void removeFirst() {
        //
        if (head == null) {
            throw new IllegalArgumentException("There's nothing to remove.");
        }

        Node<E> first = head.next;

        head.element = null;
        head.next = null;

        head = first;

        length--;

        if (head == null) {
            tail = null;
        }
    }

    @Override
    public void addAll(LinkedList elementList) {
        //
        if (elementList == null || elementList.size() == 0) {
            return;
        }

        int extraLength = elementList.size();
        for (int i=0; i<extraLength; i++) {
            addLast((E) elementList.get(i));
            length++;
        }
    }

    @Override
    public void clear() {
        //
        initialize();
    }

    @Override
    public Object[] toArray(Object[] some) {
        //
        Object[] array = new Object[length];

        int index = 0;
        Node<E> currNode = head;
        while ( currNode != null ) {
            array[index] = (E) some[index];
            index++;
            currNode = currNode.next;
        }

        return array;
    }

    private Node<E> getNode(int index) {
        //
        validateIndex(index);

        Node<E> currNode = head;
        for (int i=0; i<index; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }

    private void initialize() {
        //
        this.length = 0;
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
//        this.head = null;
//        this.tail = null;
    }

    private void validateIndex(int index) {
        //
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
