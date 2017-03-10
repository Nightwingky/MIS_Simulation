package com.nightwingky;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nightwingky on 17-3-10.
 */
public class MyConst {

    //轴承使用寿命和故障概率(累计概率)
    private static Map<Double, Integer> axletree_working_life = new HashMap<Double, Integer>();

    private static void setAxletree_working_life() {
        axletree_working_life.put(0.00, 0);
        axletree_working_life.put(0.10, 1000 * 60);
        axletree_working_life.put(0.23, 1100 * 60);
        axletree_working_life.put(0.48, 1200 * 60);
        axletree_working_life.put(0.61, 1300 * 60);
        axletree_working_life.put(0.70, 1400 * 60);
        axletree_working_life.put(0.82, 1500 * 60);
        axletree_working_life.put(0.84, 1600 * 60);
        axletree_working_life.put(0.90, 1700 * 60);
        axletree_working_life.put(0.95, 1800 * 60);
        axletree_working_life.put(1.00, 1900 * 60);
    }

    public static Map<Double, Integer> getAxletree_working_life() {
        setAxletree_working_life();

        return axletree_working_life;
    }

    //维修人员到达延迟时间(累计概率)
    private static Map<Double, Integer> reach_time = new HashMap<Double, Integer>();

    private static void setReach_time() {
        reach_time.put(0.0, 0);
        reach_time.put(0.6, 5);
        reach_time.put(0.9, 10);
        reach_time.put(1.0, 15);
    }

    public static Map<Double, Integer> getReach_time() {
        setReach_time();

        return reach_time;
    }

    //维修人员现场工资
    private static int wage = 30;
    //机器停止工作损失
    private static int stop_loss = 10;
    //轴承价格
    private static int axletree_price = 32;
    //模拟工作时间
    private static int total_time = 20000 * 60;

    public static int getWage() {
        return wage;
    }

    public static int getStop_loss() {
        return stop_loss;
    }

    public static int getAxletree_price() {
        return axletree_price;
    }

    public static int getTotal_time() {
        return total_time;
    }

    //轴承更换时间
    private static Map<Integer, Integer> change_time = new HashMap<Integer, Integer>();

    private static void setChange_time() {
        change_time.put(1, 20);
        change_time.put(2, 30);
        change_time.put(3, 40);
    }

    public static Map<Integer, Integer> getChange_time() {
        setChange_time();

        return change_time;
    }
}
