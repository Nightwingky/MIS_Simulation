package com.nightwingky.simulation;

import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-30.
 */
public class BaseProcess implements EventIntf{

    protected List<DiscreteEventVO> eventList; //事件链表
    protected List<CustomerVO> queue; //单队伍链表
    protected List<CustomerVO>[] queues; //多队伍链表
    protected CustomerVO[] onService; //服务中队伍数组_单队伍，数字对应服务人员

    protected double simulationClock; //模拟时钟
    protected double totalStayTime; //总逗留时间

    protected DiscreteEventVO currentEvent;
    protected int[] status; //服务人员状态，0空闲，1忙
    protected int totalCustomerCount; //总顾客人数

    public BaseProcess() {
        this.simulationClock = 0;
        this.totalStayTime = 0;
        this.totalCustomerCount = 0;

        this.eventList = new ArrayList<DiscreteEventVO>();
        this.queue = new ArrayList<CustomerVO>();
        this.queues = new ArrayList[3];
        this.queues[0] = new ArrayList<CustomerVO>();
        this.queues[1] = new ArrayList<CustomerVO>();
        this.queues[2] = new ArrayList<CustomerVO>();

        this.currentEvent = null;

        this.status = new int[3];
        this.status[0] = 0;
        this.status[1] = 0;
        this.status[2] = 0;
    }

    protected void timing() {
        double minTime = 1.0e+29;
        currentEvent = null;
        //搜索时间最小事件
        for (DiscreteEventVO ent : eventList) {
            if (ent.getTime() < minTime) {
                minTime = ent.getTime();
                currentEvent = ent;
            }
        }
        //时钟后移
        if (currentEvent != null) {
            simulationClock = currentEvent.getTime();
            eventList.remove(currentEvent);
        } else {
            System.out.println("Eventlist is empty");
        }
    }

    protected void occupyService(CustomerVO c) {
        //下一离开事件
        DiscreteEventVO ent = new DiscreteEventVO();
        ent.setType(2);
        ent.setTime(simulationClock + c.getServiceTime());
        ent.setQueueNo(currentEvent.getQueueNo());
        eventList.add(ent);
    }

    protected void simulation() {
        queueRun();

        while (simulationClock <= 5000) {
            timing();
            switch (currentEvent.getType()) {
                case 1:
                    arrival();
                    break;
                case 2:
                    departure();
                    break;
            }
        }
    }

    @Override
    public void arrival() {

    }

    @Override
    public void departure() {

    }

    @Override
    public void queueRun() {

    }
}
