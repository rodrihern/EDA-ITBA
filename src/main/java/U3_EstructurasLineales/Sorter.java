package U3_EstructurasLineales;

import java.util.Arrays;

public class Sorter {

    // Ordena un array con el algoritmo QUICK SORT
    public static void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length-1);
    }

    public static void quicksort(int[] arr, int left, int right) {
        if (right <= left) {
            return;
        }

        int pivot = arr[left];

        swap(arr, left, right);

        int pivotIdx = partition(arr, left, right-1, pivot);

        swap(arr, pivotIdx, right);

        quicksort(arr, left, pivotIdx-1);
        quicksort(arr, pivotIdx+1, right);


    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            if (arr[left] <= pivot) {
                left++;
            } else {
                swap(arr, left, right);
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Ordena un array con el algoritmo MERGE SORT
    public static void mergesort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;
        int[] leftHalf = Arrays.copyOfRange(arr, 0, mid);
        int[] rightHalf = Arrays.copyOfRange(arr, mid, arr.length);

        mergesort(leftHalf);
        mergesort(rightHalf);

        merge(arr, leftHalf, rightHalf);
    }

    private static void merge(int[] arr, int[] a1, int[] a2) {
        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] <= a2[j]) {
                arr[k++] = a1[i++];
            } else {
                arr[k++] = a2[j++];
            }
        }

        // llenamos con lo que haya sobrado
        while (i < a1.length) {
            arr[k++] = a1[i++];
        }
        while (j < a2.length) {
            arr[k++] = a2[j++];
        }
    }


}
