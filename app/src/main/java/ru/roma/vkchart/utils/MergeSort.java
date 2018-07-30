package ru.roma.vkchart.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ilan on 28.04.2018.
 */

public class MergeSort {

    public static int[] sort(int[]arr){
        int[] copy = Arrays.copyOf(arr,arr.length);

        int[] result =  mergeSort(copy);


        return result;
    }

    private static int[] mergeSort(int[] arr) {
        int size = arr.length;
        if (size < 2) {
            return arr;
        }
        int midle = size / 2;
        return merge(sort(Arrays.copyOfRange(arr, 0, midle)), sort(Arrays.copyOfRange(arr, midle, size)));
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int len = len1 + len2;
        int[] result = new int[len];
        int a1 = 0, a2 = 0;

        for (int i = 0; i < len; i++) {
            if (a1 == len1) {
                result[i] = arr2[a2++];
            } else {
                if (a2 == len2) {
                    result[i] = arr1[a1++];
                } else {
                    if (arr1[a1] > arr2[a2]) {
                        result[i] = arr2[a2++];
                    } else {
                        result[i] = arr1[a1++];
                    }
                }

            }
//            System.out.println(Arrays.toString(sort(result)));

        }
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
//            arr[i] = (arr.length -i);
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(sort(arr)));
    }
}
