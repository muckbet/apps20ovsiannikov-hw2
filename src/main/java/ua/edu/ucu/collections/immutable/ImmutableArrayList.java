package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.Objects;

public final class ImmutableArrayList implements ImmutableList {
    private int size;
    private Object[] elementsArray;

    public ImmutableArrayList(Object[] elements) {
        size = elements.length;
        elementsArray = Arrays.copyOf(elements, elements.length);
    }

    public ImmutableArrayList() {
        size = 0;
        elementsArray = new Object[]{};
    }

    private void checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return this.add(size, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        checkIndex(index);
        int i;
        Object[] myArray = new Object[c.length + size];
        for (i = 0; i < index; i++) {
            myArray[i] = elementsArray[i];
        }
        for (i = index; i < index + c.length; i++) {
            myArray[i] = c[i - index];
        }
        for (i = index + c.length;
             i < size + c.length;
             i++) {
            myArray[i] = elementsArray[i - c.length];
        }
        return new ImmutableArrayList(myArray);
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return elementsArray[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public ImmutableList remove(int index) {
        Object[] tmp = Arrays.copyOf(elementsArray, elementsArray.length - 1);
        for (int i = index;
             i < elementsArray.length - 1;
             i++) {
            tmp[i] = elementsArray[i + 1];
        }
        return new ImmutableArrayList(tmp);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        Object[] tmp = toArray();
        tmp[index] = e;
        return new ImmutableArrayList(tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (hashCode() != o.hashCode()) {
            return false;
        }
        ImmutableArrayList that = (ImmutableArrayList) o;
        if (getSize() == that.getSize()) {
            return false;
        }
        Object[] r = that.toArray();
        for (int i = 0; i < size; i++) {
            if (!r[i].equals(elementsArray[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getSize());
        result = 31 * result + Arrays.hashCode(elementsArray);
        return result;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < elementsArray.length; i++) {
            if (elementsArray[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        if (size() == 0) {
            return new Object[]{};
        }
        Object[] res = Arrays.copyOf(elementsArray, elementsArray.length);
        return res;
//        return Arrays.copyOf(elementsArray, elementsArray.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

}
