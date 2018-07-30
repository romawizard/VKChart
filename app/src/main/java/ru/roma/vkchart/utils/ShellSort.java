package ru.roma.vkchart.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ilan on 01.05.2018.
 */

public class ShellSort {

    public static int[] sort(int[] arr){
        int[] result = Arrays.copyOf(arr,arr.length);

        int h = 1;
        while (h <=result.length/3){
            h = h*3 +1;
        }
        while (h>0){
            for (int i = h;i< result.length; i++){
                int key = result[i];
                int j = i;
                while (j> h-1 && result[j - h] >= key){
                    result[j] = result[j-h];
                    j -= h;
                }
                result[j] = key;
            }
            h = (h-1)/3;
        }
        return result;
    }
    public static void main(String[]args){
        Random random = new Random();
        int[] arr = new int[50];
        for (int i =0; i<arr.length;i++){
            arr[i] = random.nextInt(100);
        }

        int[]result = sort(arr);

        System.out.println( "\n" + "array initial after sort = " + Arrays.toString(arr));
        System.out.println("\n array result after sort = " + Arrays.toString(result));

    }
}

