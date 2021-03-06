package com.nightwingky.vo;

/**
 * Created by nightwingky on 17-3-30.
 * 表示离散事件
 */
public class DiscreteEventVO {

    private int type;
    private double time;
    private int queueNo;

    public DiscreteEventVO() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(int queueNo) {
        this.queueNo = queueNo;
    }

    @Override
    public String toString() {
        return "DiscreteEventVO{" +
                "type=" + type +
                ", time=" + time +
                ", queueNo=" + queueNo +
                '}';
    }
}
