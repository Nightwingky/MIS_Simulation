package com.nightwingky.vo;

/**
 * Created by nightwingky on 17-3-30.
 */
public class CustomerVO {

    private double arrivalTime;
    private double step1ServiceTime;
    private double step2ServiceTime;
    private int status; //是否已经开始服务，0为否，1表示step1服务，2表示step2服务

    public CustomerVO() {
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getStep1ServiceTime() {
        return step1ServiceTime;
    }

    public void setStep1ServiceTime(double step1ServiceTime) {
        this.step1ServiceTime = step1ServiceTime;
    }

    public double getStep2ServiceTime() {
        return step2ServiceTime;
    }

    public void setStep2ServiceTime(double step2ServiceTime) {
        this.step2ServiceTime = step2ServiceTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "arrivalTime=" + arrivalTime +
                ", step1ServiceTime=" + step1ServiceTime +
                ", step2ServiceTime=" + step2ServiceTime +
                ", status=" + status +
                '}';
    }
}
