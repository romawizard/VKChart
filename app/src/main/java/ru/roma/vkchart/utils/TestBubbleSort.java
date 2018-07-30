package ru.roma.vkchart.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ilan on 26.04.2018.
 */

public class TestBubbleSort {

    public static int[] sort(int[] arr){

        int [] result =  new int[arr.length];

        for (int i=0;i< arr.length;i++){
            result[i] = arr[i];
        }

        long start = System.currentTimeMillis();

        while (true){
            int change = 0;
            for (int n =0; n<(result.length -1);n++){
                if (result[n]>result[n+1]){
                    int temp = result[n];
                    result[n] = result[n +1];
                    result[n+1] = temp;
                    change++;
                }
            }
            if (change == 0){
                break;
            }
        }

        long finish = System.currentTimeMillis();
        System.out.println("\n time BubbleSort = " + (finish-start));
        return result;

    }

    public static void main(String[]args){
        Random random = new Random();
        int[] arr = new int[100];
        for (int i =0; i<arr.length;i++){
            arr[i] = random.nextInt(1000);
        }

        int[]result = sort(arr);

        System.out.println("array result after sort = " + Arrays.toString(result));
        System.out.println("array initial after sort = " + Arrays.toString(arr));


    }

}
