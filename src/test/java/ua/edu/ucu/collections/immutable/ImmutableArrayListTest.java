package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.COMM_FAILURE;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private ImmutableArrayList firstArray;
    private ImmutableArrayList secondArray;
    private ImmutableArrayList thirdArray;
    private int firstElement;
    private double secondElement;
    private String thirdElement;

    @Before
    public void setUp() {
        firstArray = new ImmutableArrayList();
        secondArray = new ImmutableArrayList(new Object[]{1, 2, 3, 4});
        thirdArray = new ImmutableArrayList(new Object[]{11, 12, 13, 14});
        firstElement = 10;
        secondElement = 90.0;
        thirdElement = "asd";
    }

    @Test
    public void testToArray() {
        Object[] tmp = secondArray.toArray();
        for (int i = 0; i < secondArray.size(); i++) {
            assertEquals(tmp[i], secondArray.get(i));
        }
    }

    @Test
    public void testAdd() {
        ImmutableArrayList tmp = (ImmutableArrayList) firstArray.add(firstElement);
        ImmutableArrayList tmp2 = new ImmutableArrayList(new Object[]{firstElement});
        ImmutableArrayList tmp3 = (ImmutableArrayList) secondArray.add(1, secondElement);
        ImmutableArrayList tmp4 = new ImmutableArrayList(new Object[]{1, 90.0, 2, 3, 4});
        ImmutableArrayList mixed = (ImmutableArrayList) secondArray.add(1, thirdElement);
        assertArrayEquals(tmp3.toArray(), tmp4.toArray());
        assertArrayEquals(tmp.toArray(), tmp2.toArray());
        assertArrayEquals(new Object[]{1, "asd", 2, 3, 4}, mixed.toArray());
    }

    @Test
    public void testAddAll() {
        ImmutableArrayList tmp = (ImmutableArrayList) secondArray.addAll(thirdArray.toArray());
        ImmutableArrayList tmp2 = new ImmutableArrayList(new Object[]{1, 2, 3, 4, 11, 12, 13, 14});
        ImmutableArrayList tmp3 = (ImmutableArrayList) secondArray.addAll(2, thirdArray.toArray());
        ImmutableArrayList tmp4 = new ImmutableArrayList(new Object[]{1, 2, 11, 12, 13, 14, 3, 4});
        assertArrayEquals(tmp3.toArray(), tmp4.toArray());
        assertArrayEquals(tmp.toArray(), tmp2.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() {
        secondArray.get(6);
    }

    @Test
    public void testRemove() {
        ImmutableArrayList tmp = (ImmutableArrayList) secondArray.remove(2);
        ImmutableArrayList tmp2 = new ImmutableArrayList(new Object[]{1, 2, 4});
        assertArrayEquals(tmp.toArray(), tmp2.toArray());
    }

    @Test
    public void testSet() {
        ImmutableArrayList tmp = (ImmutableArrayList) secondArray.set(2, 10);
        ImmutableArrayList tmp2 = new ImmutableArrayList(new Object[]{1, 2, 10, 4});
        assertArrayEquals(tmp.toArray(), tmp2.toArray());
    }

    @Test
    public void testIndexOf() {
        int in = secondArray.indexOf(1);
        assertEquals(in, 0);
        assertEquals(-1, secondArray.indexOf('a'));
    }

    @Test
    public void testClear() {
        ImmutableLinkedList tmp = new ImmutableLinkedList(new Object[]{1, 2, 3, 4});
        tmp = (ImmutableLinkedList) tmp.clear();
        assertArrayEquals(new Object[]{}, tmp.toArray());
        assertTrue(tmp.isEmpty());
    }
}
