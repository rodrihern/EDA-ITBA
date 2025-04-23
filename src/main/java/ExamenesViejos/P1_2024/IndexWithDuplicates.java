package ExamenesViejos.P1_2024;

import java.util.Arrays;


public class IndexWithDuplicates  {

    final static private int chunksize= 5;

    private int[] indexedData;
    private int cantElems;


    public IndexWithDuplicates() {
        indexedData= new int[chunksize];
        cantElems= 0;
    }

    public void initialize(int[] unsortedElements) {

        if (unsortedElements == null)
            throw new RuntimeException("Problem: null data collection");

        indexedData= unsortedElements;
        Arrays.sort(indexedData);
        cantElems= indexedData.length;
    }


    public int[] getIndexedData() {
        return indexedData;
    }

    public void print() {
        System.out.print("[");
        for (int i : indexedData)
            System.out.print(i + " ") ;
        System.out.println("]");

    }

    public void merge(IndexWithDuplicates other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int[] otherData = other.getIndexedData();
        int n = indexedData.length, m = otherData.length;
        int[] merged = new int[n + m];

        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            // guardo el menor y avanzo
            if (indexedData[i] <= otherData[j]) {
                merged[k++] = indexedData[i++];
            } else {
                merged[k++] = otherData[j++];
            }
        }

        // completo con los que quedaron restantes
        // solo un while se va a ejecutar
        while (i < n) {
            merged[k++] = indexedData[i++];
        }
        while (j < m) {
            merged[k++] = otherData[j++];
        }

        indexedData = merged;


    }

}

