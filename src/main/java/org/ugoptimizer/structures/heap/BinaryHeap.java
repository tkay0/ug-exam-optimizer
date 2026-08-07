package org.ugoptimizer.structures.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    private final List<T> heap;
    private final Comparator<? super T> comparator;

    public BinaryHeap(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public BinaryHeap() {
        this(null);
    }

    public void add(T value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap.get(0);
    }

    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T result = heap.get(0);
        T last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }

        return result;
    }

    public boolean remove(T value) {
        int index = heap.indexOf(value);

        if (index == -1) {
            return false;
        }

        T last = heap.remove(heap.size() - 1);

        if (index < heap.size()) {
            heap.set(index, last);

            int parent = parent(index);
            if (index > 0 && compare(heap.get(index), heap.get(parent)) < 0) {
                siftUp(index);
            } else {
                siftDown(index);
            }
        }

        return true;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = parent(index);

            if (compare(heap.get(index), heap.get(parent)) >= 0) {
                break;
            }

            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = leftChild(index);
            int right = rightChild(index);
            int smallest = index;

            if (left < heap.size() && compare(heap.get(left), heap.get(smallest)) < 0) {
                smallest = left;
            }

            if (right < heap.size() && compare(heap.get(right), heap.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    @SuppressWarnings("unchecked")
    private int compare(T first, T second) {
        if (comparator != null) {
            return comparator.compare(first, second);
        }

        return ((Comparable<? super T>) first).compareTo(second);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int first, int second) {
        T temp = heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, temp);
    }
}

