package pro.sky;

import java.util.Arrays;
import java.util.Random;

public class CompareSortMethods {
    public static void main(String[] args) {
        int MAX_ELEMENTS = 100_000;
        Random random = new Random();
        int[] arr = random.ints().limit(MAX_ELEMENTS).toArray();
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, arr.length);

        long start;
        start = System.currentTimeMillis();
        sortBubbles(arr);
        System.out.println("bubble sort, ms " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        sortChoices(arr2);
        System.out.println("choice sort, ms " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        sortMerge(arr3);
        System.out.println("merge sort, ms " + (System.currentTimeMillis() - start));

        System.out.println(Arrays.equals(arr3, arr2));
    }

    private static int[] sortMerge(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int middle = arr.length / 2;
        int[] arrL = sortMerge(Arrays.copyOfRange(arr, 0, middle));
        int[] arrR = sortMerge(Arrays.copyOfRange(arr, middle, arr.length));
        int[] arrC = new int[arr.length];

        int n = 0, k = 0, m = 0;
        while (n < arrL.length && m < arrR.length) {
            if (arrL[n] <= arrR[m]) {
                arrC[k++] = arrL[n++];
            } else {
                arrC[k++] = arrR[m++];
            }
        }

        while (n < arrL.length) {
            arrC[k++] = arrL[n++];
        }

        while (m < arrR.length) {
            arrC[k++] = arrR[m++];
        }

        System.arraycopy(arrC, 0, arr, 0, arr.length);
        return arr;
    }

    private static void sortChoices(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }

    private static void sortBubbles(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
