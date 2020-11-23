package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.COMM_FAILURE;

import static org.junit.Assert.*;

public class NodeTest {
    private Node firstNode;
    private Node secondNode;
    private Node thirdNode;
    private Node chainOne;
    private Node chainTwo;
    private Node chainThree;

    @Before
    public void setUp(){
        firstNode = new Node.Builder(23).build();
        secondNode = new Node.Builder(45).build();
        thirdNode = new Node.Builder("asd").build();
        chainOne = new Node.Builder("a").build();
        chainTwo = new Node.Builder("b").previous(chainOne).build();
        chainOne.setNext(chainTwo);
        chainThree = new Node.Builder("c").previous(chainTwo).build();
        chainTwo.setNext(chainThree);
    }

    @Test
    public void testToString() {
        Object curr = chainTwo.getValue();
        assertEquals("b", curr);
        curr = firstNode.toString();
        assertEquals("23", curr);
    }

    @Test
    public void getPrevious() {
        assertEquals(chainTwo, chainThree.getPrevious());
        assertEquals(chainOne, chainTwo.getPrevious());
        assertNull(chainOne.getPrevious());
    }

    @Test
    public void getValue() {
        assertEquals(45, secondNode.getValue());
        assertEquals("asd", thirdNode.getValue());
        assertNull(new Node().getValue());
    }

    @Test
    public void getNext() {
        assertEquals(chainOne.getNext(), chainTwo);
        assertEquals(chainTwo.getNext(), chainThree);
        assertNull(chainThree.getNext());
    }

    @Test
    public void setValue() {
        firstNode.setValue('a');
        assertEquals('a', firstNode.getValue());
    }
}
