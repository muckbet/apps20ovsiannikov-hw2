package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList elements;

    public Queue() {
        elements = new ImmutableLinkedList();
    }

    public Object peek() {

        return elements.getTail().getValue();
    }

    public Object dequeue() {

        Object result = peek();
        elements = elements.removeLast();
        return result;
    }

    public void enqueue(Object e) {

        elements = elements.addFirst(e);
    }
}
