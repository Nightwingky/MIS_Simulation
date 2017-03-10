package com.nightwingky;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-8.
 */
public class MyConst {

    //需求到达的间隔时间，服从指数分布
    private static List<Double> demandTime = new ArrayList<Double>();

    private static void setDemandTime() {

        double x, z;

        for (int i = 0; i < 500; i++) {
            z = Math.random();
            x = -Math.log(z);

            demandTime.add(x);
        }
    }

    public static List<Double> getDemandTime() {
        setDemandTime();

        return demandTime;
    }

    //需求数量
    private static List<Integer> demandAmount = new ArrayList<Integer>();

    private static void setDemandAmount() {
        demandAmount.add(1);
        demandAmount.add(2);
        demandAmount.add(2);
        demandAmount.add(3);
        demandAmount.add(3);
        demandAmount.add(4);
    }

    public static List<Integer> getDemandAmount() {
        setDemandAmount();

        return demandAmount;
    }

    //订货成本常数
    private static double const_k = 3.00;

    //单价
    private static double const_price = 10.00;


    public static double getConst_k() {
        return const_k;
    }

    public static double getConst_price() {
        return const_price;
    }

    //最大、最小库存
    private static double inventory_min = 20;
    private static double inventory_max = 100;

    public static double getInventory_min() {
        return inventory_min;
    }

    public static double getInventory_max() {
        return inventory_max;
    }

    //初始库存
    private static double inventory_start = 60;

    public static double getInventory_start() {
        return inventory_start;
    }
}
