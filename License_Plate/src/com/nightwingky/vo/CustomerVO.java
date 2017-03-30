package com.nightwingky.vo;

/**
 * Created by nightwingky on 17-3-30.
 */
public class CustomerVO {

    private double arrivalTime;
    private double serviceTime;
    private int status; //是否已经开始服务，0为否，1为是

    public CustomerVO() {
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
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
                ", serviceTime=" + serviceTime +
                ", status=" + status +
                '}';
    }
}
