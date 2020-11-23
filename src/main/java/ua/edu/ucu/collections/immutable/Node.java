package ua.edu.ucu.collections.immutable;

public class Node {
    private Node previous;
    private Node next;
    private Object value;

    public Node() {
    }

    private Node(Builder b) {
        this.value = b.value;
        this.previous = b.previous;
        this.next = b.next;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class Builder {
        private Node previous;
        private Node next;

        private Object value;

        public Builder(Object value) {
            this.value = value;
        }

        public Builder previous(Node prev) {
            this.previous = prev;
            return this;
        }

        public Builder next(Node n) {
            this.next = n;
            return this;
        }

        public Node build() {
            return new Node(this);
        }

    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node prev) {
        previous = prev;
    }

    public Object getValue() {
        if (value == null) {
            return null;
        }
        return value;
    }

    public void setValue(Object val) {
        value = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node ne) {
        next = ne;
    }
}

