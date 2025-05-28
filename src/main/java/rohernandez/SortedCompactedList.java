package rohernandez;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedCompactedList<T extends Comparable<? super T>> implements Iterable<T> {
    private Node root;

    public void insert(T data) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null");

        if (root == null) {
            root = new Node(data);
            return;
        }

        root = root.insert(data);
        return;
    }

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        boolean[] rta = new boolean[1];
        root = root.remove(data, rta);
        return rta[0];
    }

    public int size() {
        if (root == null)
            return 0;
        return root.size();
    }

    public void dump() {
        Node current = root;

        System.out.println("Dump");

        while (current != null) {
            for (int i = 0; i < current.count; i++) {
                System.out.println(current.data);
            }
            current = current.next;
        }
    }

    public void dumpNodes() {
        // No modificar
        Node current = root;

        System.out.println("DumpNodes");

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // clase nodo
    private final class Node {
        private T data;
        private int count;
        private Node next;

        private Node(T data) {
            this.data = data;
            count = 1;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
            count = 1;
        }

        private Node insert(T data) {
            if (this.data.compareTo(data) == 0) {
                count++;
                return this;
            }
            if (this.data.compareTo(data) < 0) {

                if (next == null) {
                    next = new Node(data);
                    return this;
                }
                next = next.insert(data);
                return this;
            }

            return new Node(data, this);
        }

        private Node remove(T data, boolean[] rta) {
            if (this.data.compareTo(data) == 0) {
                rta[0] = true;
                count--;
                if (count <= 0) {
                    return next;
                }
                return this;
            }

            if (next != null && this.data.compareTo(data) < 0) {
                next = next.remove(data, rta);
                return this;
            }

            rta[0] = false;
            return this;
        }

        private int size() {
            if (next == null)
                return count; // modificado para que devuelva la cantidad de elementos en lugar de la cantidad de nodos
            return count + next.size();
        }
    }

    public Iterator<T> iterator() {
        return new SortedCompactedListIterator();
    }

    private class SortedCompactedListIterator implements Iterator<T> {
        private Node current;
        private int itemsLeft;
        private Node prev;


        SortedCompactedListIterator() {
            current = root;
            prev = null;
            itemsLeft = (root != null) ? root.count : 0;
        }

        public boolean hasNext() {
            return current != null && (itemsLeft > 0 || current.next != null);
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (itemsLeft == 0) {
                prev = current;
                current = current.next;
                itemsLeft = current.count;
            }
            itemsLeft--;

            return current.data;
        }

        public void remove() {
            if (current == null) {
                throw new IllegalStateException("No se puede remover");
            }

            // Decremento el count del nodo actual
            current.count--;

            if (current.count == 0) { // tengo que eliminar el nodo
                if (prev == null) {
                    root = current.next;
                } else {
                    prev.next = current.next;
                }

                current = current.next;

                // Si current es null, significa que hemos eliminado el último nodo
                itemsLeft = (current != null) ? current.count : 0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("**** TEST 1 ****");
        test1();
        System.out.println("**** TEST 2 ****");
        test2();
        System.out.println("**** TEST 3 ****");
        test3();
        System.out.println("**** TEST 4 ****");
        test4();
        System.out.println("**** TEST 5 ****");
        test5();

    }

    private static void initializeList(SortedCompactedList<String> l) {
        l.insert("hola");
        l.insert("tal");
        l.insert("ah");
        l.insert("veo");
        l.insert("ah");
        l.insert("bio");
        l.insert("ah");
        l.insert("veo");
        l.insert("ah");
        l.insert("tal");
    }

    private static void test1() {
        SortedCompactedList<String> l = new SortedCompactedList<>();
        initializeList(l);

        System.out.print("Size: ");
        System.out.println(l.size());
        System.out.println();

        l.dump();
        System.out.println();
        l.dumpNodes();
        System.out.println();
    }

    private static void test2() {
        SortedCompactedList<String> l = new SortedCompactedList<>();
        initializeList(l);

        l.remove("hola");
        l.remove("tal");
        l.remove("ah");
        l.remove("veo");

        System.out.println("After Removing");

        System.out.print("Size: ");
        System.out.println(l.size());
        System.out.println();

        l.dump();
        System.out.println();
        l.dumpNodes();
        System.out.println();
    }

    private static void test3() {
        SortedCompactedList<String> l = new SortedCompactedList<>();
        initializeList(l);

        System.out.println("Dump with Iterator");
        for (String s : l) {
            System.out.println(s);
        }
    }

    private static void test4() {
        SortedCompactedList<String> l = new SortedCompactedList<>();
        initializeList(l);

        // borro items pares
        for (Iterator<String> it = l.iterator(); it.hasNext(); ) {
            String currData = it.next();
            System.out.println("Salteo: " + currData);
            if (it.hasNext()) {
                currData = it.next();
                System.out.println("Borro: " + currData);
                it.remove();
            }
        }

        System.out.println("After Removing");

        System.out.print("Size: ");
        System.out.println(l.size());
        System.out.println();

        l.dump();
        System.out.println();
        l.dumpNodes();
        System.out.println();

        System.out.println("Dump with Iterator");

        for (String s : l) {
            System.out.println(s);
        }
    }

    private static void test5() {
        SortedCompactedList<String> l = new SortedCompactedList<>();
        initializeList(l);

        // borro items inpares
        for (Iterator<String> it = l.iterator(); it.hasNext(); ) {
            String currData = it.next();
            System.out.println("Borro: " + currData);
            it.remove();
            if (it.hasNext()) {
                currData = it.next();
                System.out.println("Salteo: " + currData);
            }
        }

        System.out.println("After Removing");

        System.out.print("Size: ");
        System.out.println(l.size());
        System.out.println();

        l.dump();
        System.out.println();
        l.dumpNodes();
        System.out.println();

        System.out.println("Dump with Iterator");

        for (String s : l) {
            System.out.println(s);
        }
    }
}