package ru.roma.vkchart.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ilan on 03.05.2018.
 */

public class QuickSort {

    private static int[] sortedArray;

    public static int[] sort(int[] arr) {

        int[] result = Arrays.copyOf(arr, arr.length);
        sortedArray = result;
        quickSort(0, result.length - 1);
        return sortedArray;

    }

    private static void quickSort(int first, int last) {

        int i = first;
        int j = last;

        int pivot = sortedArray[first + (last - first) / 2];

        while (i <= j) {

            while (sortedArray[i] < pivot) {
                i++;
            }
            while (sortedArray[j] > pivot) {
                j--;
            }
            if (sortedArray[i] >= sortedArray[j]) {
                int temp = sortedArray[i];
                sortedArray[i] = sortedArray[j];
                sortedArray[j] = temp;
                i++;
                j--;
            }
        }
        if (i < last) {
            quickSort(i, last);
        }
        if (first  <j ) {
            quickSort(first, j);
        }


    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println("\n" + "array initial after sort = " + Arrays.toString(arr));

        int[] result = sort(arr);

        System.out.println("\n array result after sort = " + Arrays.toString(result));
    }
}
