package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList elements;

    public Stack() {
        elements = new ImmutableLinkedList();
    }

    public void push(Object e) {
        elements = elements.addLast(e);
    }

    public Object pop() {
        Object result = peek();
        elements = elements.removeLast();
        return result;
    }

    public Object peek() {
        return elements.getTail().getValue();
    }
}
