package pro.sky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayIntegerListTest {
    private ArrayIntegerList arrayList;

    @BeforeEach
    public void setUp() {
        arrayList = new ArrayIntegerList();
    }

    @Test
    public void testAdd() {
        Integer item = 42;

        assertEquals(item, arrayList.add(item));
        assertEquals(item, arrayList.get(0));
    }

    @Test
    public void testNullFails() {
        assertThrows(NullParameterException.class, () -> arrayList.add(null));
        assertThrows(NullParameterException.class, () -> arrayList.add(0, null));
        assertThrows(NullParameterException.class, () -> arrayList.indexOf(null));
        assertThrows(NullParameterException.class, () -> arrayList.lastIndexOf(null));
        assertThrows(NullParameterException.class, () -> arrayList.remove(null));
        assertThrows(NullParameterException.class, () -> arrayList.set(0, null));
        assertThrows(NullParameterException.class, () -> arrayList.contains(null));
    }

    @Test
    public void testOutOfBoundsFails() {
        assertThrows(IndexWrongException.class, () -> arrayList.add(-1, 0));
        assertThrows(IndexWrongException.class, () -> arrayList.add(arrayList.size() + 1, 0));
        assertThrows(IndexWrongException.class, () -> arrayList.get(-1));
        assertThrows(IndexWrongException.class, () -> arrayList.get(arrayList.size()));
        assertThrows(IndexWrongException.class, () -> arrayList.set(-1, 0));
        assertThrows(IndexWrongException.class, () -> arrayList.set(arrayList.size(), 0));
        assertThrows(IndexWrongException.class, () -> arrayList.remove(-1));
        assertThrows(IndexWrongException.class, () -> arrayList.remove(arrayList.size()));
    }

    @Test
    public void testAddWithIndex() {
        Integer item1 = 1;
        Integer item2 = 2;

        arrayList.add(0, item1);

        assertEquals(item2, arrayList.add(0, item2));
        assertEquals(item2, arrayList.get(0));
        assertEquals(item1, arrayList.get(1));
    }

    @Test
    public void testSet() {
        Integer item1 = 1;
        Integer item2 = 2;

        arrayList.add(item1);

        assertEquals(item2, arrayList.set(0, item2));
        assertEquals(item2, arrayList.get(0));
    }

    @Test
    public void testRemove() {
        Integer item = 42;

        arrayList.add(item);

        assertEquals(item, arrayList.remove(item));
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testRemoveWithIndex() {
        Integer item = 42;

        arrayList.add(item);

        assertEquals(item, arrayList.remove(0));
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testContains() {
        Integer item = 42;

        arrayList.add(item);

        assertTrue(arrayList.contains(item));

        arrayList.add(43);

        assertTrue(arrayList.contains(43));

        arrayList.add(40);

        assertTrue(arrayList.contains(43));

    }

    @Test
    public void testContainsFails() {
        Integer item = 42;

        assertFalse(arrayList.contains(item));
    }

    @Test
    public void testIndexOf() {
        Integer item = 42;

        arrayList.add(item);

        assertEquals(0, arrayList.indexOf(item));
        assertEquals(-1, arrayList.indexOf(0));
    }

    @Test
    public void testLastIndexOf() {
        Integer item = 42;

        arrayList.add(item);

        assertEquals(0, arrayList.lastIndexOf(item));
        assertEquals(-1, arrayList.lastIndexOf(0));
    }

    @Test
    public void testIndexOfFails() {
        Integer item = 42;

        assertEquals(-1, arrayList.indexOf(item));
    }

    @Test
    public void testLastIndexOfFails() {
        Integer item = 42;

        assertEquals(-1, arrayList.lastIndexOf(item));
    }

    @Test
    public void testGet() {
        Integer item = 42;

        arrayList.add(item);

        assertEquals(item, arrayList.get(0));
    }

    @Test
    public void testGetFails() {
        assertThrows(IndexWrongException.class, () -> arrayList.get(0));
    }

    @Test
    public void testEqualsOK() {
        ArrayIntegerList otherList = new ArrayIntegerList();

        assertTrue(arrayList.equals(otherList));

        otherList.add(42);
        arrayList.add(42);

        assertTrue(arrayList.equals(otherList));

        otherList = arrayList;

        assertTrue(arrayList.equals(otherList));
    }

    @Test
    public void testEqualsFails() {
        ArrayIntegerList otherList = new ArrayIntegerList();

        otherList.add(42);

        assertFalse(arrayList.equals(otherList));

        assertThrows(NullParameterException.class, () -> arrayList.equals(null));

        arrayList.add(1);

        assertFalse(arrayList.equals(otherList));
    }

    @Test
    public void testSize() {
        arrayList.add(42);

        assertEquals(1, arrayList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(arrayList.isEmpty());

        arrayList.add(42);

        assertFalse(arrayList.isEmpty());
    }

    @Test
    public void testClear() {
        arrayList.add(42);
        arrayList.clear();

        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testToArray() {
        Integer item = 42;

        arrayList.add(item);

        assertArrayEquals(new Integer[]{item}, arrayList.toArray());
    }

    @Test
    public void testCheckIndex() {
        assertThrows(IndexWrongException.class, () -> arrayList.get(-1));
        assertThrows(IndexWrongException.class, () -> arrayList.get(0));
    }

    @Test
    public void testGrow() {
        for (int i = 0; i < 20; i++) {
            arrayList.add(42 + i);
        }

        assertEquals(20, arrayList.size());
    }
}
