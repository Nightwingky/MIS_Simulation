package com.nightwingky.vo;

/**
 * Created by nightwingky on 17-3-30.
 */
public class ResultVO {

    private double avgStayTimeStep1;
    private double totalStayTimeStep1;

    private double avgStayTimeStep2;
    private double totalStayTimeStep2;

    private double avgStayTime;
    private double totalStayTime;

    private int totalCustomerCount;
    private double simulationClock;

    public ResultVO(double avgStayTimeStep1, double totalStayTimeStep1,
                    double avgStayTimeStep2, double totalStayTimeStep2,
                    double avgStayTime, double totalStayTime,
                    int totalCustomerCount, double simulationClock) {
        this.avgStayTimeStep1 = avgStayTimeStep1;
        this.totalStayTimeStep1 = totalStayTimeStep1;
        this.avgStayTimeStep2 = avgStayTimeStep2;
        this.totalStayTimeStep2 = totalStayTimeStep2;
        this.avgStayTime = avgStayTime;
        this.totalStayTime = totalStayTime;
        this.totalCustomerCount = totalCustomerCount;
        this.simulationClock = simulationClock;
    }

    public double getAvgStayTimeStep1() {
        return avgStayTimeStep1;
    }

    public void setAvgStayTimeStep1(double avgStayTimeStep1) {
        this.avgStayTimeStep1 = avgStayTimeStep1;
    }

    public double getTotalStayTimeStep1() {
        return totalStayTimeStep1;
    }

    public void setTotalStayTimeStep1(double totalStayTimeStep1) {
        this.totalStayTimeStep1 = totalStayTimeStep1;
    }

    public double getAvgStayTimeStep2() {
        return avgStayTimeStep2;
    }

    public void setAvgStayTimeStep2(double avgStayTimeStep2) {
        this.avgStayTimeStep2 = avgStayTimeStep2;
    }

    public double getTotalStayTimeStep2() {
        return totalStayTimeStep2;
    }

    public void setTotalStayTimeStep2(double totalStayTimeStep2) {
        this.totalStayTimeStep2 = totalStayTimeStep2;
    }

    public double getAvgStayTime() {
        return avgStayTime;
    }

    public void setAvgStayTime(double avgStayTime) {
        this.avgStayTime = avgStayTime;
    }

    public double getTotalStayTime() {
        return totalStayTime;
    }

    public void setTotalStayTime(double totalStayTime) {
        this.totalStayTime = totalStayTime;
    }

    public int getTotalCustomerCount() {
        return totalCustomerCount;
    }

    public void setTotalCustomerCount(int totalCustomerCount) {
        this.totalCustomerCount = totalCustomerCount;
    }

    public double getSimulationClock() {
        return simulationClock;
    }

    public void setSimulationClock(double simulationClock) {
        this.simulationClock = simulationClock;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "avgStayTimeStep1=" + avgStayTimeStep1 +
                ", totalStayTimeStep1=" + totalStayTimeStep1 +
                ", avgStayTimeStep2=" + avgStayTimeStep2 +
                ", totalStayTimeStep2=" + totalStayTimeStep2 +
                ", avgStayTime=" + avgStayTime +
                ", totalStayTime=" + totalStayTime +
                ", totalCustomerCount=" + totalCustomerCount +
                ", simulationClock=" + simulationClock +
                '}';
    }
}
