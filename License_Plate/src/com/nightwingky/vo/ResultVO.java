package com.nightwingky.vo;

/**
 * Created by nightwingky on 17-3-30.
 */
public class ResultVO {

    private double avgStayTime;
    private double totalStayTime;
    private int totalCustomerCount;
    private double simulationClock;

    public ResultVO(double avgStayTime, double totalStayTime, int totalCustomerCount, double simulationClock) {
        this.avgStayTime = avgStayTime;
        this.totalStayTime = totalStayTime;
        this.totalCustomerCount = totalCustomerCount;
        this.simulationClock = simulationClock;
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
                "avgStayTime=" + avgStayTime +
                ", customer_total_stay_time=" + totalStayTime +
                ", customer_amount=" + totalCustomerCount +
                ", simulation_clock=" + simulationClock +
                '}';
    }
}
