package ru.roma.vkchart.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ilan on 27.04.2018.
 */

public class InsertionSort {

    public static int[] sort(int[] arr) {

        int[] result = Arrays.copyOf(arr, arr.length);

        long start = System.currentTimeMillis();

        for (int i = 1; i < result.length; i++) {

            int key = result[i];
            int n = i - 1;
            while (n >= 0 && result[n] > key) {
                result[n + 1] = result[n];
                n--;
            }
            result[n + 1] = key;
        }

        long finish = System.currentTimeMillis();
        System.out.println("\n time InsertionSort = " + (finish - start));
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000000);
//            arr[i] = (arr.length -i);
//            arr[i] = i;
        }

//        System.out.println(Arrays.toString(arr)
        long start1 = System.currentTimeMillis();
        int[] shell =  ShellSort.sort(arr);
        long finish1 = System.currentTimeMillis();
        System.out.println(" time ShellSort = " + (finish1 - start1));
//        System.out.println("\n" + Arrays.toString(shell));

        long start = System.currentTimeMillis();
        MergeSort.sort(arr);
        long finish = System.currentTimeMillis();
        System.out.println("\n time MergeSort = " + (finish - start));

        long start2 = System.currentTimeMillis();
        QuickSort.sort(arr);
        long finish2 = System.currentTimeMillis();
        System.out.println("\n time QuickSort = " + (finish2 - start2));


//        sort(arr);
//        TestSelectSort.sort(arr);
//        TestBubbleSort.sort(arr);

    }


}
