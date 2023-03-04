package arrayList;

import iterator.Iterator;
import iterator.MyIterator;
import linkedList.MyLinkedList;

public class MyArrayList<E> implements ArrayList<E> {
    //
    private static final int DEFAULT_CAPACITY = 5;

    private int currentCapacity;
    private E[] elements;
    private int length;


    public MyArrayList() {
        //
        initialize();
    }

    public MyArrayList(int capacity) {
        //
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be positive number");
        }

        this.currentCapacity = capacity;
        this.elements = (E[]) new Object[capacity];
        this.length = 0;
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
        for (int i=0; i<length; i++) {
            if ((object == null && elements[i] == null) || (object != null && elements[i].equals(object))) {
                return i;
            }
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
        return new MyArrayIterator();
    }

    private class MyArrayIterator implements Iterator<E> {
        //
        private int index = 0;
        private E[] elements;
        private int length;

        public MyIterator myIterator() {
            this.index = 0;
            this.elements = (E[]) new Object[0];
            this.length = 0;
            return new MyIterator();
        }

        @Override
        public E next() {
            return (E) elements[index++];
        }

        @Override
        public boolean hasNext() {
            return index < length;
        }
    }

    @Override
    public void add(E element) {
        //
        checkCapacity(1);

        elements[length] = element;
        length++;
    }

    @Override
    public void add(int index, E element) {
        //
        validateIndex(index);

        if (index == length) {
            add(element);
        }

        else {
            checkCapacity(1);

            for (int i=index; i<length; i++) {
                elements[i+1] = elements[i];
            }

            elements[index] = element;
            length++;
        }
    }

    @Override
    public E get(int index) {
        //
        validateIndex(index);

        return elements[index];
    }

    @Override
    public void remove(Object object) {
        //
        int idx = indexOf(object);

        validateIndex(idx);

        remove(idx);
    }

    @Override
    public void remove(int index) {
        //
        validateIndex(index);

        elements[index] = null;

        for (int i=index; i<length-1; i++) {
            elements[i] = elements[i+1];
            elements[i+1] = null;
        }

        length--;
    }

    @Override
    public void addAll(ArrayList elementList) {
        //
        if (elementList == null || elementList.size() == 0) {
            throw new IllegalArgumentException("Length of elementList to add should be greater than zero");
        }

        int extraLength = elementList.size();
        checkCapacity(extraLength);

        for (int i=0; i<extraLength; i++) {
            elements[length] = (E) elementList.get(i);
            length++;
        }
    }

    @Override
    public void clear() {
        //
        initialize();
    }

    @Override
    public Object[] toArray(Object[] elementList) {
        //
        E[] newElements = (E[]) new Object[currentCapacity];
        System.arraycopy(elementList, 0, newElements, 0, length);

        if (elementList.length > length) {
            checkCapacity(newElements.length - length);

            for (int i=length; i< elementList.length; i++) {
                elementList[i] = null;
            }
        }

        elements = newElements;

        return newElements;
    }

    private void initialize() {
        this.currentCapacity = DEFAULT_CAPACITY;
        this.elements = (E[]) new Object[currentCapacity];
        this.length = 0;
    }


    private void checkCapacity(int extraLength) {
        //
        int element_capacity = elements.length + extraLength;

        if (element_capacity >= length) {
            int new_capacity = element_capacity * 2;
            currentCapacity = new_capacity;

            E[] newElements = (E[]) new Object[currentCapacity];
            System.arraycopy(elements, 0, newElements, 0, length);
            elements = newElements;
        }
    }

    private void validateIndex(int index) {
        //
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
    }





}


