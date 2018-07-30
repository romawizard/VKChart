package ru.roma.vkchart.utils;

/**
 * Created by Ilan on 29.04.2018.
 */

public class MyTask {

    public static void print(int n) {
        StringBuilder sb;

        for (int i = 1; i <= n; i++) {
            sb = new StringBuilder(i);
            String x = "";
            String y = "";
            String num = String.valueOf(i);
            if (i % 3 == 0) {
                x = "x";
                num = "";
            }
            if (i % 5 == 0) {
                y = "y";
                num = "";
            }
            sb.append(num);
            sb.append(x);
            sb.append(y);
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        print(30);
    }
}
