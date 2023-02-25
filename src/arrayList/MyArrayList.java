package arrayList;

import iterator.MyIterator;

public class MyArrayList<E> implements ArrayList<E> {
    //
    private static final int DEFAULT_CAPACITY = 5; // 생성자로 배열이 생성될 때 기본용량

    private E[] item; // 배열
    private int size; // 배열 길이

    public MyArrayList() {
        this.item = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.item = (E[]) new Object[capacity];
        }
        else if (capacity == 0) {
            this.item = (E[]) new Object[DEFAULT_CAPACITY];
        }
        else if (capacity < 0) {
            throw new RuntimeException(new IllegalArgumentException("리스트 용량을 잘못 설정하였습니다."));
        }
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i=0; i<size; i++) {
                if(item[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i=0; i<size; i++) {
                if (item[i].equals(object)) {
                    return i;
                }
            }
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
        resize();

        item[size]  = element;
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            resize();
            add(element);
        }

        else {
            resize();

            for (int i = size; i > index; i--) {
                item[i] = item[i-1];
            }

            item[index] = element;
            size++;
        }
    }

    @Override
    public E get(int index) {
        //
        if (index < 0 || index > size) {
            return null;
        }
        return item[index];
    }

    @Override
    public void remove(Object object) {
        //
        int idx = indexOf(object);

        if (idx == -1) return;

        remove(idx);
    }

    @Override
    public void remove(int index) {
        //
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        item[index] = null;

        for (int i=index; i<size-1; i++) {
            item[i] = item[i+1];
            item[i+1] = null;
        }

        size--;
    }

    @Override
    public void addAll(ArrayList collection) {
        if (collection == null || collection.size() == 0) {
            return;
        }

        int addSize = collection.size();
        resizeWithSize(addSize);

        for (int i=0; i<addSize; i++) {
            item[size] = (E) collection.get(i);
            size++;
        }
    }

    @Override
    public void clear() {
        item = (E[]) new Object[item.length];
        size = 0;
    }

    @Override
    public Object[] toArray(Object[] some) {
        if (some.length <= size) {
            return copyArray(item, size);
        }
        else {
            // System.arraycopy(원본배열, 원본배열 시작위치, 복사할 배열, 복사할배열 시작위치, 복사할 요소 수)
            System.arraycopy(item, 0, some, 0, size);

            // 내부 배열을 복사한 후 바로 다음에 null을 삽입
            if (some.length > size)
                some[size] = null;

            return some;
        }
    }

    private void resize() {
        int element_capacity = item.length;

        if (element_capacity == size) {
            int new_capacity = element_capacity * 2;

            item = (E[]) copyArray(item, new_capacity);
        }
    }

    private void resizeWithSize(int addSize) {
        int element_capacity = item.length + addSize;

        if (element_capacity == size) {
            int new_capacity = element_capacity * 2;

            item = (E[]) copyArray(item, new_capacity);
        }
    }


    private Object[] copyArray(Object object, int size) {
        //
        int newSize = size + 1;
        Object[] newArray = new Object[newSize];

        newArray[newSize-1] = object;

        for (int i=0; i<newArray.length; i++){
            this.item[i] = (E) newArray[i];
        }
        return this.item;
    }


}


