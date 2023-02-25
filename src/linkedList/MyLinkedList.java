package linkedList;

import iterator.MyIterator;

import java.util.Objects;

public class MyLinkedList<E> implements LinkedList<E>{
    //singly linkedList
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size==0;
    }

    @Override
    public int indexOf(Object object) {
        Node<E> currentNode = head;
        Node<E> targetNode = null;

        for (int i=0; i<size; i++) {
            if(Objects.equals(currentNode.item, object)) {
                targetNode = currentNode;
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) > 0;
    }

    @Override
    public MyIterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    public void addFirst(E element) {
        Node<E> first = head;

        Node<E> newNode = new Node<>(element, first);

        size++;

        head = newNode;

        if (first == null) {
            tail = newNode;
        }
    }

    public void addLast(E element) {
        Node<E> last = tail;

        Node<E> newNode = new Node<>(element, null);

        size++;

        tail = newNode;

        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size -1) {
            addLast(element);
            return;
        }

        Node<E> prevNode = getNode(index-1);
        Node<E> delNode = prevNode.next;
        Node<E> newNode = new Node<>(element, delNode);

        prevNode.next = newNode;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size ) {
            throw new IndexOutOfBoundsException();
        }

        return getNode(index).item;
    }

    @Override
    public void remove(Object object) {
        Node<E> currNode = head;
        Node<E> prevNode = null;

        while( currNode != null) {
            if (Objects.equals(currNode.item, object)) {
                prevNode.next = currNode.next;
                break;
            }
            prevNode = currNode;
            currNode = currNode.next;
        }

        size--;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        Node<E> prevNode = getNode(index-1);
        Node<E> delNode = prevNode.next;
        Node<E> nextNode = delNode.next;

        delNode.item = null;
        delNode.next = null;

        size--;

        prevNode.next = nextNode;
    }

    public void removeFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> first = head.next;

        head.item = null;
        head.next = null;

        head = first;

        size--;

        if (head == null) {
            tail = null;
        }
    }

    @Override
    public void addAll(LinkedList collection) {
        if (collection == null || collection.size() == 0) {
            return;
        }

        int addSize = collection.size();
        for (int i=0; i<addSize; i++) {
            addLast((E) collection.get(i));
            size++;
        }
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
    }

    @Override
    public Object[] toArray(Object[] some) {
        Object[] array = new Object[size];

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
        Node<E> currNode = head;
        for (int i=0; i<index; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }
}
