package com.nightwingky.random;

/**
 * Created by nightwingky on 17-3-26.
 */
public class MyRandomNum {

    //指数分布
    public static double getExpon(int avg) {

        double num = - avg * Math.log(Math.random());

        return num;
    }

    //均匀分布
    public static double getUnif(double a, double b) {

        double num = Math.random() * Math.abs(b - a) + Math.min(a, b);

        return num;
    }
}
