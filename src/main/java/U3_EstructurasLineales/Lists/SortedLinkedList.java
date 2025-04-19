package U3_EstructurasLineales.Lists;


import java.util.Iterator;
import java.util.NoSuchElementException;

// lista simplemente encadenada, no acepta repetidos (false e ignora) ni nulls (exception)
public class SortedLinkedList<T extends Comparable<? super T>> implements SortedListService<T>{

    private Node root;
    private int size = 0;
    private T max = null;

    @Override
    // Iterativo
    public boolean insert(T data) {

        if (data == null)
            throw new IllegalArgumentException("data cannot be null");

        Node prev= null;
        Node current = root;

        while (current!=null && current.data.compareTo(data) < 0) {
            // avanzo
            prev= current;
            current= current.next;
        }

        // repetido?
        if (current!=null && current.data.compareTo(data) == 0) {
            System.err.printf("Insertion failed. %s repeated%n", data);
            return false;
        }

        Node aux= new Node(data, current);
        // es el lugar para colocarlo
        if (current == root) {
            // el primero es un caso especial: cambia root
            root= aux;
        }
        else {
            // nodo interno
            if (prev.next == null) {
                max = data;
            }
            prev.next= aux;
        }

        size++;
        return true;
    }


    // recursivo
    public boolean insert2(T data) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null");

        boolean[] rta = new boolean[1];
        root = insertRec(data, root, rta);
        if (rta[0]) {
            size++;
        }
        return rta[0];
    }


    private Node insertRec(T data, Node current, boolean[] added) {
        if (current == null || current.data.compareTo(data) > 0) {
            // inserto
            added[0] = true;
            return new Node(data, current);
        } else if (current.data.compareTo(data) < 0) {
            // avanzo
            current.next = insertRec(data, current.next, added);
        }

        return null;
    }

    // insert resuelto delegando al nodo
    public boolean insert3(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        if (root == null) {
            root = new Node(data);
        }

        boolean[] rta = new boolean[1];
        root = root.insert(data, rta);
        if (rta[0]) {
            size++;
        }
        return rta[0];

    }


    @Override
    public boolean find(T data) {
        return getPos(data) != -1;
    }


    // iterativo
    @Override
    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node current = root;
        Node prev = null;
        while (current != null && current.data.compareTo(data) <= 0) {
            if (current.data.equals(data)) {
                // remove
                if (prev == null) {
                    root = current.next;
                } else {
                    prev.next = current.next;
                }
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }


    // recursivo
    public boolean remove2(T data) {
        boolean[] removed = new boolean[1];
        root = removeRec(data, root, removed);
        return removed[0];

    }


    private Node removeRec(T data, Node current, boolean[] removed) {
        if (current == null || current.data.compareTo(data) > 0) {
            return current;
        }
        if (current.data.compareTo(data) == 0) {
            // remove
            removed[0] = true;
            return current.next;
        }
        current.next = removeRec(data, current.next, removed);
        return current;
    }


    // delete resuelto delegando al nodo
    public boolean remove3(T data) {
        // TODO: completar
        return true;
    }



    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        int res= 0;

        Node current = root;

        while (current!=null ) {
            // avanzo
            res++;
            current= current.next;
        }
        return res;
    }


    @Override
    public void dump() {
        Node current = root;

        while (current!=null ) {
            // avanzo
            System.out.println(current.data);
            current = current.next;
        }
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SortedLinkedList))
            return false;

        @SuppressWarnings("unchecked")
        SortedLinkedList<T> auxi = (SortedLinkedList<T>) other;

        Node current = root;
        Node currentOther= auxi.root;
        while (current!=null && currentOther != null ) {
            if (current.data.compareTo(currentOther.data) != 0)
                return false;

            // por ahora si, avanzo ambas
            current= current.next;
            currentOther= currentOther.next;
        }

        return current == null && currentOther == null;
    }

    // -1 si no lo encontro
    protected int getPos(T data) {
        Node current = root;
        int pos= 0;

        while (current!=null ) {
            if (current.data.compareTo(data) == 0)
                return pos;
            // avanzo
            current= current.next;
            pos++;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    @Override
    public T getMin() {
        return root == null ? null : root.data;
    }


    @Override
    public T getMax() {

        return max;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = root;

            @Override
            public boolean hasNext() {
                return current != null && current.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T res = current.data;
                current = current.next;
                return res;

            }
        };
    }


    private final class Node {
        private final T data;
        private Node next;

        private Node(T data, Node next) {
            this.data= data;
            this.next= next;
        }

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node insert(T data, boolean[] added) {

            if (this.data.compareTo(data) > 0) {
                // inserto
                added[0] = true;
                return new Node(data, this);
            } else if (this.data.compareTo(data) < 0) {
                // avanzo
                if (this.next == null) {
                    added[0] = true;
                    this.next = new Node(data);
                    return this;
                }
                next = next.insert(data, added);
            }

            return this;
        }

        public Node remove(T data, boolean[] removed) {
            if (this.data.compareTo(data) > 0) {
                return this;
            }
            if (this.data.compareTo(data) == 0) {
                removed[0] = true;
                return this.next;
            }
            // tengo que seguir buscando remover en mi next
            this.next = next.remove(data, removed);
            return this;
        }

    }





    public static void main(String[] args) {
        SortedLinkedList<String> l = new SortedLinkedList<>();

        System.out.println("lista " +  (l.isEmpty()? "":"NO") + " vacia");
        System.out.println(l.size() );
        System.out.println(l.getMin() );
        System.out.println(l.getMax() );
        System.out.println();

        System.out.println(l.insert("hola"));
        l.dump();
        System.out.println();

        System.out.println("lista " +  (l.isEmpty()? "":"NO") + " vacia");
        System.out.println();

        System.out.println(l.insert("tal"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("ah"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("veo"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("bio"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("tito"));
        l.dump();
        System.out.println();


        System.out.println(l.insert("hola"));
        l.dump();
        System.out.println();


        System.out.println(l.insert("aca"));
        l.dump();
        System.out.println();

        System.out.println(l.size() );
        System.out.println(l.getMin() );
        System.out.println(l.getMax() );
    }


}
