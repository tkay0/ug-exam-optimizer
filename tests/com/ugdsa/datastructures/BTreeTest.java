package com.ugdsa.datastructures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    // --- normal cases ---

    @Test
    void insertAndSearchRoundTripForSeveralKeys() {
        BTree<Integer, String> tree = new BTree<>();

        tree.insert(10, "ten");
        tree.insert(20, "twenty");
        tree.insert(5, "five");
        tree.insert(15, "fifteen");

        assertEquals("ten", tree.search(10));
        assertEquals("twenty", tree.search(20));
        assertEquals("five", tree.search(5));
        assertEquals("fifteen", tree.search(15));
        assertNull(tree.search(999));
        assertEquals(4, tree.size());
    }

    @Test
    void insertingExistingKeyUpdatesValueWithoutGrowingSize() {
        BTree<Integer, String> tree = new BTree<>();
        tree.insert(10, "ten");
        tree.insert(20, "twenty");

        tree.insert(10, "TEN-UPDATED");

        assertEquals("TEN-UPDATED", tree.search(10));
        assertEquals(2, tree.size());
    }

    @Test
    void inorderTraverseVisitsKeysInAscendingOrder() {
        BTree<Integer, String> tree = new BTree<>();
        int[] insertOrder = {50, 10, 40, 20, 5, 30, 45, 25};
        for (int key : insertOrder) {
            tree.insert(key, "v" + key);
        }

        List<Integer> visited = new ArrayList<>();
        tree.inorderTraverse((key, value) -> visited.add(key));

        List<Integer> expectedSorted = new ArrayList<>(visited);
        expectedSorted.sort(Integer::compareTo);
        assertEquals(expectedSorted, visited, "inorder traversal must yield ascending key order");
        assertEquals(insertOrder.length, visited.size());
    }

    // --- node split behavior (minDegree = 2, so a node overflows at 3 keys) ---

    @Test
    void rootSplitsWhenItOverflowsAndTreeStaysSearchable() {
        BTree<Integer, String> tree = new BTree<>(2); // maxKeys per node = 3

        tree.insert(10, "10");
        tree.insert(20, "20");
        tree.insert(30, "30");

        assertTrue(tree.isRootLeaf(), "root should still be a leaf before it overflows");
        assertEquals(3, tree.rootKeyCount());
        assertEquals(1, tree.height());

        tree.insert(40, "40"); // this insert forces the root to split

        assertFalse(tree.isRootLeaf(), "root must no longer be a leaf after a split promotes a median key");
        assertEquals(1, tree.rootKeyCount(), "root should hold exactly the promoted median key");
        assertEquals(2, tree.height(), "tree height grows by one level after a root split");
        assertEquals(4, tree.size());

        assertEquals("10", tree.search(10));
        assertEquals("20", tree.search(20));
        assertEquals("30", tree.search(30));
        assertEquals("40", tree.search(40));
    }

    // --- boundary cases ---

    @Test
    void emptyTreeIsEmptyAndSearchReturnsNull() {
        BTree<Integer, String> tree = new BTree<>();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertNull(tree.search(1));
    }

    @Test
    void singleKeyTreeFindsThatKeyAndOnlyThatKey() {
        BTree<Integer, String> tree = new BTree<>();
        tree.insert(7, "seven");

        assertEquals("seven", tree.search(7));
        assertNull(tree.search(8));
        assertEquals(1, tree.size());
    }

    // --- invalid input ---

    @Test
    void insertWithNullKeyThrows() {
        BTree<Integer, String> tree = new BTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.insert(null, "x"));
    }

    @Test
    void searchWithNullKeyThrows() {
        BTree<Integer, String> tree = new BTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.search(null));
    }

    @Test
    void constructorRejectsMinDegreeBelowTwo() {
        assertThrows(IllegalArgumentException.class, () -> new BTree<Integer, String>(1));
    }
}
