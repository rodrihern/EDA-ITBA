package ExamenesViejos.P1_2023_1C;

public class SimpleLinkedList <T>{
    private Node first = null;
    private Node last = null;

    public void insert(T data) {
        Node toInsert = new Node(data);
        if (first == null) {
            first = last = toInsert;
        } else {
            last.next = toInsert;
            last = toInsert;
        }
    }

    public void dump() {
        Node current = first;

        while (current!=null ) {
            // avanzo
            System.out.println(current.data);
            current= current.next;
        }
    }

    private final class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
