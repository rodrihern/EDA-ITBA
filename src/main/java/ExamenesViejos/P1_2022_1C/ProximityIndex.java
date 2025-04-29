package ExamenesViejos.P1_2022_1C;


public class ProximityIndex {
    private String[] elements;
    private int size = 0;

    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }

        for(int rec= 0; rec < elements.length-1; rec++) {
            if (elements[rec].compareTo(elements[rec+1]) >= 0)
                throw new IllegalArgumentException("hay repetidos o no está ordenado");
        }

        this.elements = elements;
        this.size = elements.length;

    }


    public String search(String element, int distance) {
        int i = binarySearch(element); // O(logN)
        if (i < 0) { // no se encontro el elemento
            return null;
        }
        i += distance; // hago el desfazaje
        if (i < 0) {
            i += (-i/size + 1)*size;
        }
        i %= size;  // lo trato como lista circular en O(1)
        return elements[i];
    }

    private int binarySearch(String elem) {
        int left = 0, right = size-1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = elem.compareTo(elements[mid]);
            if (cmp == 0) {
                return mid;
            }
            if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
