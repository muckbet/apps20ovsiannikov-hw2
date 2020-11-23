package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableLinkedList listOne;
    private ImmutableLinkedList listTwo;
    private ImmutableLinkedList listThree;
    private ImmutableLinkedList listEmpty;
    private ImmutableLinkedList listMixed;

    @Before
    public void setUp() {
        listOne = new ImmutableLinkedList(new Object[]{1});
        listTwo = new ImmutableLinkedList(new Object[]{1, 'a'});
        listThree = new ImmutableLinkedList(new Object[]{1, 2, 3});
        listEmpty = new ImmutableLinkedList();
        listMixed = new ImmutableLinkedList(new Object[]{1, 2, 3, 'a', 'b', 'c'});
    }

    @Test
    public void testGetHead() {
        assertEquals(1, listOne.getHead().getValue());
        assertEquals(listOne.getHead().getValue(), listOne.get(0));
        assertNull(listEmpty.getHead());
    }

   @Test
    public void testGetTail() {
        assertEquals(3, listThree.getTail().getValue());
        assertEquals(listOne.getTail().getValue(), 1);
        assertNull(listEmpty.getTail());
    }

    @Test
    public void testAddFirst() {
        ImmutableLinkedList tmpEmpty = listEmpty.addFirst(3);
        ImmutableLinkedList tmp = listTwo.addFirst(0);
        assertArrayEquals(tmp.toArray(), new Object[]{0, 1, 'a'});
        assertArrayEquals(tmpEmpty.toArray(), new Object[]{3});
    }

    @Test
    public void testAdd() {
        ImmutableLinkedList tmp = (ImmutableLinkedList) listThree.add(1, 'a');
        assertArrayEquals(new Object[]{1, 'a', 2, 3}, tmp.toArray());
    }

    @Test
    public void testAddLast() {
        ImmutableLinkedList tmpEmpty = listEmpty.addLast(3);
        ImmutableLinkedList tmp = listTwo.addLast(0);
        assertArrayEquals(tmp.toArray(), new Object[]{1, 'a', 0});
        assertArrayEquals(tmpEmpty.toArray(), new Object[]{3});
    }

    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList tmp = listOne.removeFirst();
        ImmutableLinkedList tmp2 = listTwo.removeFirst();
        ImmutableLinkedList tmp3 = listMixed.removeFirst();
        assertArrayEquals(tmp.toArray(), new Object[]{});
        assertArrayEquals(tmp2.toArray(), new Object[]{'a'});
        assertArrayEquals(tmp3.toArray(), new Object[]{2, 3, 'a', 'b', 'c'});
    }



    @Test
    public void testAddAll() {
        ImmutableLinkedList tmp = (ImmutableLinkedList) listMixed.addAll(0, listThree.toArray());
        ImmutableLinkedList tmpEmpty = (ImmutableLinkedList) listEmpty.addAll(listThree.toArray());
        assertArrayEquals(new Object[]{1, 2, 3}, tmpEmpty.toArray());
        assertArrayEquals(new Object[]{1, 2, 3, 1, 2, 3, 'a', 'b', 'c'}, tmp.toArray());
    }

    @Test
    public void testRemove() {
        ImmutableLinkedList tmp = (ImmutableLinkedList) listMixed.remove(0);
        ImmutableLinkedList tmp2 = (ImmutableLinkedList) listMixed.remove(listMixed.size());
        ImmutableLinkedList tmp3 = (ImmutableLinkedList) listMixed.remove(3);
        assertArrayEquals(tmp3.toArray(), new Object[]{1, 2, 3, 'b', 'c'});
        assertArrayEquals(tmp.toArray(), new Object[]{2, 3, 'a', 'b', 'c'});
        assertArrayEquals(tmp2.toArray(), new Object[]{1, 2, 3, 'a', 'b'});
    }

    @Test
    public void testSet() {
        ImmutableLinkedList tmp = (ImmutableLinkedList) listMixed.set(0, 32);
        ImmutableLinkedList tmp2 = (ImmutableLinkedList) listMixed.set(listMixed.size(), 'z');
        System.out.println(Arrays.toString(tmp.toArray()));
        assertArrayEquals(tmp.toArray(), new Object[]{32, 2, 3, 'a', 'b', 'c'});
        assertArrayEquals(tmp2.toArray(), new Object[]{1, 2, 3, 'a', 'b', 'z'});
    }

    @Test
    public void testGet() {
        assertEquals(1, listThree.getHead().getValue());
        assertEquals('c', listMixed.getTail().getValue());
    }
    @Test
    public void testIndexOf() {
        assertEquals(0, listThree.indexOf(1));
        assertEquals(-1, listThree.indexOf('a'));
    }


    @Test
    public void testSize() {
        assertEquals(0, listEmpty.size());
        assertEquals(6, listMixed.size());
        assertEquals(3, listThree.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(listEmpty.isEmpty());
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Object[]{1, 2, 3}, listThree.toArray());
        assertArrayEquals(new Object[]{1, 2, 3, 'a', 'b', 'c'}, listMixed.toArray());
    }

}
