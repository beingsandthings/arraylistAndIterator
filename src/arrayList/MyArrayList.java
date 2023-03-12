package arrayList;

import iterator.Iterator;

import java.util.NoSuchElementException;

public class MyArrayList<E> implements ArrayList<E> {
    //
    private static final int DEFAULT_CAPACITY = 5;

    private int capacity;
    private E[] elements;
    private int length;
    
    public MyArrayList() {
        //
        initialize(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        //
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be positive number : " + capacity);
        }

        initialize(capacity);
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
        for (int index=0; index<length; index++) {
            if ((object == null && elements[index] == null) || (object != null && elements[index].equals(object))) {
                return index;
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

        @Override
        public E next() {
            //
            return (E) elements[index++];
        }

        @Override
        public boolean hasNext() {
            //
            return index < length;
        }
    }

    @Override
    public void add(E element) {
        //
        increaseCapacity(1);

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
            increaseCapacity(1);

            shiftRight(index);

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
        int index = indexOf(object);

        if (index == -1) {
            throw new NoSuchElementException("Index: " + index);
        }

        remove(index);
    }

    @Override
    public void remove(int index) {
        //
        validateIndex(index);

        elements[index] = null;

        shiftLeft(index);

        length--;
    }

    @Override
    public void addAll(ArrayList elementList) {
        //
        if (elementList == null || elementList.size() == 0) {
            throw new IllegalArgumentException("Length of elementList to add should be greater than zero: " + elementList.size());
        }

        int extraLength = elementList.size();
        increaseCapacity(extraLength);

        for (int i=0; i<extraLength; i++) {
            elements[length] = (E) elementList.get(i);
            length++;
        }
    }

    @Override
    public void clear() {
        //
        initialize(DEFAULT_CAPACITY);
    }

    @Override
    public Object[] toArray(Object[] target) {
        //
        E[] newElements = (E[]) new Object[capacity];
        System.arraycopy(target, 0, newElements, 0, length);

        int targetLength = length;

        if (target.length > targetLength) {
            increaseCapacity(newElements.length - length);

            for (int i=length; i< target.length; i++) {
                target[i] = null;
            }
        }

        elements = newElements;

        return newElements;
    }


    private void increaseCapacity(int extraLength) {
        //
        int minimumCapacity = elements.length + extraLength;

        if (minimumCapacity > capacity) {
            int newCapacity = elements.length + extraLength * 2;
            capacity = newCapacity;
            E[] newElements = (E[]) new Object[capacity];
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

    private void initialize(int capacity) {
        this.capacity = capacity;
        this.elements = (E[]) new Object[capacity];
        this.length = 0;
    }

    private void shiftLeft(int index) {
        for (int i=index; i<length-1; i++) {
            elements[i] = elements[i+1];
            elements[i+1] = null;
        }
    }

    private void shiftRight(int index) {
        for (int i=index; i<length; i++) {
            elements[i+1] = elements[i];
        }
    }
}


