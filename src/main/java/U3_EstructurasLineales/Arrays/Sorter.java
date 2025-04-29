package U3_EstructurasLineales.Arrays;

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

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        // Dividir recursivamente
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // Combinar las mitades ordenadas
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // Tamaños de los subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Crear arreglos temporales
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copiar los datos a los arreglos temporales
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];

        // Mezclar los arreglos temporales
        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copiar los elementos restantes
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }




}
