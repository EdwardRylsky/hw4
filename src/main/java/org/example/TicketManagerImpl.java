package org.example;

import tech.vanyo.treePrinter.TreePrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class TicketManagerImpl implements TicketManager {
    private final Ticket[] heap;
    public boolean isDbg = true;
    private int size = 0;

    public TicketManagerImpl(int capacity) {
        heap = new Ticket[capacity];
    }

    /**
     * Регистрация талона в очереди
     *
     * @param ticket ticket
     */
    @Override
    public void add(Ticket ticket) {
        heap[size] = ticket;
        checkRules(size++);
    }

    private void checkRules(int cur) {
        int par = getParent(cur);
        Ticket value = heap[cur];
        while (cur > 0 && value.compareTo(heap[par]) > 0) {
            heap[cur] = heap[par];
            cur = par;
            par = getParent(cur);
        }
        heap[cur] = value;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Получение следующего талона или null если очередь пуста
     * В первую очередь идут талоны с Ticket.type = "pension", далее все остальные.
     * Внутри групп ("pension" или остальные) упорядочиваем по времени регистрации Ticket.registerTime по возрастанию (FIFO)
     *
     * @return
     */
    @Override
    public Ticket next() {
        if (size == 0) {
            return null;
        }
        Ticket res = heap[0];
        int newSize = --size;
        heap[0] = heap[newSize];
        heap[newSize] = null;
        heapify(0);
        return res;
    }

    private void heapify(int i) {
        debug("heapify " + i + " = " + heap[i]);
        debug(print());
        int left;
        int right;
        int largest;
        while (heap[i] != null) {
            largest = i;
            left = 2 * i + 1;
            right = 2 * i + 2;
            if (left < heap.length && bigger(largest, left)) {
                largest = left;
            }
            if (right < heap.length && bigger(largest, right)) {
                largest = right;
            }
            if (largest == i) {
                break;
            }
            Ticket tmp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = tmp;
            i = largest;
        }
    }

    private void debug(String str) {
        if (isDbg) {
            System.out.println(str);
        }
    }


    private boolean bigger(int a, int b) {
        return heap[b] != null && heap[a].compareTo(heap[b]) < 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String print() {
        VirtualNode<Ticket> root = new VirtualNode<>(heap, 0);
        TreePrinter<VirtualNode<Ticket>> printer = new TreePrinter<>(VirtualNode::getLabel, VirtualNode::getLeft, VirtualNode::getRight);
        printer.setSquareBranches(true);
        printer.setHspace(1);
        printer.setTspace(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        printer.setPrintStream(new PrintStream(byteArrayOutputStream));
        printer.printTree(root);
        return byteArrayOutputStream.toString();

    }

    @Override
    public String toString() {
        return "TicketManagerImpl{" + "heap=" + Arrays.toString(heap) + '}';
    }

    static class VirtualNode<V> {
        private int index;
        private V heap[];

        public VirtualNode(V heap[], int index) {
            this.index = index;
            this.heap = heap;
        }

        String getLabel() {
            return String.format("[%d]%s", index, heap[index]);
        }

        VirtualNode<V> getLeft() {
            int left = 2 * index + 1;
            if (check(left)) {
                return null;
            }
            return new VirtualNode<>(heap, left);
        }

        VirtualNode<V> getRight() {
            int right = 2 * index + 2;
            if (check(right)) {
                return null;
            }
            return new VirtualNode<>(heap, right);
        }

        boolean check(int index) {
            return index < 0 || index >= heap.length || heap[index] == null;
        }
    }
}