package ru.roma.vkchart.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ilan on 27.04.2018.
 */

public class TestSelectSort {

    public static int[] sort(int[] arr) {

        int[] result = Arrays.copyOf(arr, arr.length);
        long start = System.currentTimeMillis();

        for (int n = 0; n < result.length; n++) {

            int least = n;

            for (int i = n+1; i < result.length; i++) {
                if (result[least] > result[i]){
                    least = i;
            }
            }
            if (least!=n){
                int temp = result[n];
                result[n] = result[least];
                result[least] = temp;
            }

        }
            long finish = System.currentTimeMillis();
            System.out.println("\n time SelectSort = " + (finish-start));
        return result;
    }

    public static void main(String[]args){
        Random random = new Random();
        int[] arr = new int[10];
        for (int i =0; i<arr.length;i++){
            arr[i] = random.nextInt(100);
        }

        int[]result = sort(arr);

//        System.out.println( "\n" + "array initial after sort = " + Arrays.toString(arr));
        System.out.println("\n array result after sort = " + Arrays.toString(result));


    }
}
