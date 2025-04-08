package U3_EstructurasLineales;

import java.util.Arrays;

public class IndexGeneric<T extends Comparable<? super T>> {

    private Object[] array;
    public static final int chunkSize = 20;
    private int idx;
    private int size;

    public IndexGeneric() {
        array = new Object[chunkSize];
        size = chunkSize;
        idx = 0;
    }


    public void initialize(T[] elements) {
        if (elements == null) {
            throw new RuntimeException("elements must not be null");
        }
        idx = elements.length;
        size = idx;
        array = Arrays.copyOf(elements, idx);
        Arrays.sort(array);

    }

    private int getClosestPosition(T key) {
        if (idx == 0) {
            return 0;
        }

        int left = 0, right = idx;


        while (left < right) {
            int mid = (left + right) / 2;
            T elem = (T) array[mid];
            int cmp = elem.compareTo(key);

            if (cmp == 0) {
                return mid;
            }
            if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }

        return left;
    }


    public boolean search(T key) {

        return idx != 0 && array[getClosestPosition(key)].equals(key);
    }

    public T[] range(T from, T to, boolean leftIncluded, boolean rightIncluded) {
        if (to.compareTo(from) < 0) {
            return (T[]) java.lang.reflect.Array.newInstance(from.getClass(), 0);
        }
        // encontramos el i
        int i = getClosestPosition(from);
        if (i >= idx) {
            return (T[]) java.lang.reflect.Array.newInstance(from.getClass(), 0);
        }
        if (leftIncluded) {
            while (i >= 0 && array[i].equals(from)) {
                i--;
            }
            i++;
        } else {
            while (i < idx && array[i].equals(from)) {
                i++;
            }
        }
        // encontramos el j
        int j = getClosestPosition(to);
        if (j >= idx) {
            return (T[]) java.lang.reflect.Array.newInstance(from.getClass(), 0);
        }
        if (rightIncluded) {
            while (j < idx && to.compareTo((T) array[j]) >= 0) {
                j++;
            }
        } else {
            while (j < idx && to.compareTo((T) array[j]) > 0) {
                j++;
            }
        }
        if (i > j) {
            return (T[]) java.lang.reflect.Array.newInstance(from.getClass(), 0);
        }
        return (T[]) Arrays.copyOfRange(array, i, j);
    }

    public void insert(T key) {
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

    public void delete(T key) {
        // los tengo que traer a los amigos y decrementar el idx
        if (idx == 0) {
            return;
        }
        int i = getClosestPosition(key);
        if (array[i].equals(key)) {
            idx--;
            for (int j = i; j < idx; j++) {
                array[j] = array[j+1];
            }
        }
    }

    public int occurrences(T key) {
        if (idx == 0) {
            return 0;
        }
        int i = getClosestPosition(key);
        if (!array[i].equals(key)) {
            return 0;
        }
        int res = 1;
        // cuento hacia la izquierda
        for (int j = i-1; j >= 0 && array[j].equals(key); j--) {
            res++;
        }

        // cuento hacia la derecha
        for (int j = i+1; j < idx && array[j].equals(key)  ; j++) {
            res++;
        }

        return res;
    }
}
