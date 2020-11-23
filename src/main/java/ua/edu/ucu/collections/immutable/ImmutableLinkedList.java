package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.Objects;

public final class ImmutableLinkedList implements ImmutableList {
    private Node head = null;
    private Node tail = null;

    public ImmutableLinkedList() {
    }

    public ImmutableLinkedList(Node head, Node tail) {
        this.head = new Node.Builder(head.getValue()).build();
        Node current = this.head;
        Node copyFrom = head;
        while (copyFrom != tail) {
            current.setNext(new Node.Builder(copyFrom.getNext()
                    .getValue())
                    .previous(current)
                    .build());
            current = current.getNext();
            copyFrom = copyFrom.getNext();
        }
        this.tail = current;
    }

    public ImmutableLinkedList(ImmutableLinkedList prevList) {
        head = prevList.head;
        tail = prevList.tail;
    }

    public ImmutableLinkedList(Object[] elements) {
        head = new Node.Builder(elements[0]).build();
        Node previousNode = head;
        Node current;
        if (elements.length == 1) {
            current = head;
        } else {
            current = null;
        }
        for (int i = 1; i < elements.length; i++) {
            current = new Node.Builder(elements[i])
                    .previous(previousNode).build();
            previousNode.setNext(current);
            previousNode = current;
        }
        tail = current;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        if (tail == null) {
            return head;
        }
        return tail;
    }

    public void checkIndex(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) addAll(0, new Object[]{e});
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) addAll(size(), new Object[]{e});
    }

    public Object getFirst() {
        return tail.getValue();
    }

    public Object getLast() {
        return head.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(size());
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(size(), new Object[]{e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        checkIndex(index);
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        checkIndex(index);
        ImmutableLinkedList currentCopy = new ImmutableLinkedList(this);
        ImmutableLinkedList additionalList = new ImmutableLinkedList(c);
        if (getHead() == null || getTail() == null) {
            return additionalList;
        }
        if (index == 0) {
            additionalList.getTail().setNext(currentCopy.getHead());
            currentCopy.getHead().setPrevious(additionalList.getTail());
            return new ImmutableLinkedList(
                    additionalList.getHead(),
                    currentCopy.getTail());
        }
        if (index == size()) {
            currentCopy.getTail().setNext(additionalList.getHead());
            additionalList.getHead().setPrevious(currentCopy.getTail());
            return new ImmutableLinkedList(
                    currentCopy.getHead(),
                    additionalList.getTail());
        }
        Node tmp = currentCopy.getHead();
        for (int i = 0; i < index - 1; i++) {
            tmp = tmp.getNext();
        }
        tmp.getNext().setPrevious(additionalList.getTail());
        tmp.getNext().getPrevious().setNext(tmp.getNext());
        tmp.setNext(additionalList.getHead());
        tmp.getNext().setPrevious(tmp);
        return currentCopy;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        Node tmp = head;
        int counter = 0;
        while (counter < index) {
            counter++;
            tmp = tmp.getNext();
        }
        return tmp.getValue();
    }

    @Override
    public ImmutableList remove(int index) {
        if (getHead() == null || getTail() == null) {
            throw new IndexOutOfBoundsException();
        }
        checkIndex(index);
        if (head.getNext() == null) {
            return new ImmutableLinkedList();
        }

        if (index == 0) {
            return new ImmutableLinkedList(head.getNext(),
                    tail);
        }
        if (index == size()) {
            return new ImmutableLinkedList(head,
                    tail.getPrevious());
        }
        ImmutableLinkedList result =
                new ImmutableLinkedList(head, tail);
        Node tmp = result.head;
        int counter = 0;
        while (counter < index) {
            counter++;
            tmp = tmp.getNext();
        }
        tmp.getNext().setPrevious(tmp.getPrevious());
        tmp.getPrevious().setNext(tmp.getNext());
        return result;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkIndex(index);
        ImmutableLinkedList result =
                new ImmutableLinkedList(head, tail);
        if (index == 0) {
            result.head = new Node.Builder(e)
                    .next(result.getHead().getNext()).build();
            result.head.getNext().setPrevious(result.head);
        } else if (index == size()) {
            result.tail = new Node.Builder(e)
                    .previous(result.tail.getPrevious()).build();
            result.tail.getPrevious().setNext(result.tail);
        } else {
            Node tmp = result.head;
            int counter = 0;
            while (counter < index) {
                counter++;
                tmp = tmp.getNext();
            }
            tmp = new Node.Builder(e)
                    .next(tmp.getNext())
                    .previous(tmp.getPrevious())
                    .build();
            tmp.getPrevious().setNext(tmp);
            tmp.getNext().setPrevious(tmp);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (hashCode() != o.hashCode()) {
            return false;
        }
        ImmutableLinkedList t = (ImmutableLinkedList) o;
        Object[] element = toArray();
        Object[] that = t.toArray();
        if (element.length != that.length) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!element[i].equals(that[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHead(), getTail());
    }

    @Override
    public int indexOf(Object e) {
        int counter = 0;
        Node current = head;
        while (current.getNext() != null) {
            if (current.getValue().equals(e)) {
                return counter;
            }
            counter++;
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        if (head == null && tail == null) {
            return 0;
        }
        int counter = 0;
        Node current = head;
        while (current != null) {
            counter++;
            current = current.getNext();
        }
        return counter;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return tail == null && head == null;
    }

    @Override
    public Object[] toArray() {
        if (head == null && tail == null) {
            return new Object[]{};
        }
        Node current = head;
        Object[] result = new Object[size()];
        int counter = 0;
        while (current != null) {
            result[counter] = current.getValue();
            counter++;
            current = current.getNext();
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
