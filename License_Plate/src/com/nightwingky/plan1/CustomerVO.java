package com.nightwingky.plan1;

/**
 * Created by nightwingky on 17-3-26.
 */
public class CustomerVO {

    //顾客类型
    private int type;
    //到达时间
    private double arrive_time;
    //等待时间
    private double waiting_time;
    //处理时间
    private double dealing_time;

    public CustomerVO(int type, double arrive_time) {
        this.type = type;
        this.arrive_time = arrive_time;
        this.waiting_time = 0.00;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(double arrive_time) {
        this.arrive_time = arrive_time;
    }

    public double getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(double waiting_time) {
        this.waiting_time = waiting_time;
    }

    public double getDealing_time() {
        return dealing_time;
    }

    public void setDealing_time(double dealing_time) {
        this.dealing_time = dealing_time;
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "type=" + type +
                ", arrive_time=" + arrive_time +
                ", waiting_time=" + waiting_time +
                ", dealing_time=" + dealing_time +
                '}';
    }
}
