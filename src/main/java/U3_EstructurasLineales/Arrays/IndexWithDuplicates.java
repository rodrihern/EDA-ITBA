package U3_EstructurasLineales.Arrays;


import java.util.Arrays;

public class IndexWithDuplicates  implements IndexService {

    private int[] array;
    private static final int chunkSize = 20;
    private int idx;
    private int size;

    public IndexWithDuplicates() {
        array = new int[chunkSize];
        size = chunkSize;
        idx = 0;
    }

    private int getClosestPosition(int key) {
        int left = 0, right = idx-1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    @Override
    public void initialize(int[] elements) {
        if (elements == null) {
            throw new RuntimeException("elements must not be null");
        }
        idx = elements.length;
        size = idx;
        array = Arrays.copyOf(elements, idx);
        Arrays.sort(array);

    }

    @Override
    public boolean search(int key) {
        return array[getClosestPosition(key)] == key;
    }

    @Override
    public void insert(int key) {
        // realocar si no hay espacio
        if (idx == 0) {
            array[idx++] = key;
            return;
        }
        if (idx == size) {
            size += chunkSize;
            array = Arrays.copyOf(array, size);
        }
        int i = getClosestPosition(key);
        idx++;
        for (int j = idx; j > i; j--) {
            array[j] = array[j-1];
        }
        array[i] = key;
    }

    @Override
    public void delete(int key) {
        // los tengo que traer a los amigos y decrementar el idx
        int i = getClosestPosition(key);
        if (array[i] == key) {
            idx--;
            for (int j = i; j < idx; j++) {
                array[j] = array[j+1];
            }
        }
    }

    @Override
    public int occurrences(int key) {
        int i = getClosestPosition(key);
        if (array[i] != key) {
            return 0;
        }
        int res = 1;
        // cuento hacia la izquierda
        for (int j = i-1;j >= 0 && array[j] == key; j--) {
            res++;
        }

        // cuento hacia la derecha
        for (int j = i+1; j < idx && array[j] == key; j++) {
            res++;
        }

        return res;
    }



}