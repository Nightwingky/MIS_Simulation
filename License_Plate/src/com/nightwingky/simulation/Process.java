package com.nightwingky.simulation;

import com.nightwingky.random.MyRandomNum;
import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-30.
 */
public class Process {

    private String resultStr = "";

    private List<DiscreteEventVO> eventList; //事件链表
    private List<CustomerVO> queue; //单队伍链表
    private List<CustomerVO>[] queues; //多队伍链表
    private CustomerVO[] onService; //服务中队伍数组_单队伍，数字对应服务人员

    private double simulationClock; //模拟时钟
    private double totalStayTime; //总逗留时间

    private DiscreteEventVO currentEvent;
    private int[] status; //服务人员状态，0空闲，1忙
    private int totalCustomerCount; //总顾客人数

    public Process() {

    }

    private void init() {
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

//        oneQueueRun();
        threeQueueRun();
    }

    private void timing() {
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
        //跳转至事件
        switch (currentEvent.getType()) {
            case 1:
                arrival_3();
//                arrival_1();
                break;
            case 2:
                departure_3();
//                departure_1();
                break;
        }
    }

    /**
     * 三对列
     */
    //顾客到达
    void arrival_3() {
        //添加顾客到queues
        CustomerVO customerVO = new CustomerVO();
        customerVO.setArrivalTime(simulationClock);
        customerVO.setServiceTime(MyRandomNum.getUnif(8, 10));
        queues[currentEvent.getQueueNo()].add(customerVO);

        //下一次arrival
        DiscreteEventVO nextArrival = new DiscreteEventVO();
        nextArrival.setType(1);
        nextArrival.setTime(simulationClock + MyRandomNum.getExpon(10));
        nextArrival.setQueueNo(currentEvent.getQueueNo());
        eventList.add(nextArrival);

        //如果服务人员空闲，开始服务当前顾客
        if (status[currentEvent.getQueueNo()] == 0) {
            status[currentEvent.getQueueNo()] = 1;
            occupyService(customerVO);
        }
    }

    //顾客离开
    void departure_3() {
        CustomerVO c = queues[currentEvent.getQueueNo()].get(0);
        queues[currentEvent.getQueueNo()].remove(0); //移除当前队伍首位的顾客

        //计算总逗留时间、总顾客人数
        totalStayTime += simulationClock - c.getArrivalTime();
        totalCustomerCount += 1;

        //判断队伍是否为空：若是则更改服务人员状态，若否则开始服务下一顾客
        if (queues[currentEvent.getQueueNo()].size() == 0)  {
            status[currentEvent.getQueueNo()] = 0;
        }
        else {
            c = queues[currentEvent.getQueueNo()].get(0);
            occupyService(c);
        }
    }

    private void threeQueueRun() {
        for (int i = 0; i < 3; i++) {
            DiscreteEventVO ent = new DiscreteEventVO();
            ent.setType(1); //到达
            ent.setTime(0);
            ent.setQueueNo(i);
            eventList.add(ent);
        }
    }

    /**
     * 单对列
     */
    //顾客到达
    void arrival_1() {
        //添加顾客到queue
        CustomerVO customerVO = new CustomerVO();
        customerVO.setArrivalTime(simulationClock);
        customerVO.setServiceTime(MyRandomNum.getUnif(8, 10));
        customerVO.setStatus(0);
        queue.add(customerVO);

        //下一次arrival
        DiscreteEventVO nextArrival = new DiscreteEventVO();
        nextArrival.setType(1);
        nextArrival.setTime(simulationClock + MyRandomNum.getExpon(10));
        //nextArrival.queueNo = currentEvent.queueNo;
        eventList.add(nextArrival);

        //如果有服务人员空闲，开始服务当前顾客
        for (int i = 0; i < 3; i++) {
            if (status[i] == 0) {
                status[i] = 1;
                currentEvent.setQueueNo(i);

//                CustomerVO tmp = queue.get(queue.size() - 1);
                CustomerVO tmp = queue.get(0);
                tmp.setStatus(1);

                //将顾客移入服务中数组，并从队伍中移除
                onService[i] = tmp;
                queue.remove(0);
//                queue.remove(queue.size() - 1);

                occupyService(tmp);
                break;
            }
        }
    }

    //顾客离开
    void departure_1() {
        CustomerVO c = onService[currentEvent.getQueueNo()];
        onService[currentEvent.getQueueNo()] = null;

        //计算总逗留时间、总顾客人数
        totalStayTime += simulationClock - c.getArrivalTime();
        totalCustomerCount += 1;

        //判断队伍是否为空：若是则更改服务人员状态，若否则开始服务下一顾客
        if (queue.size() == 0) {
            status[currentEvent.getQueueNo()] = 0;
        } else {
            CustomerVO tmp = queue.get(0);
            tmp.setStatus(1);

            onService[currentEvent.getQueueNo()] = tmp;
            queue.remove(0);
            occupyService(tmp);

        }
    }

    private void oneQueueRun() {
        this.onService = new CustomerVO[3];

        for (int i = 0; i < 3; i++)  {
            DiscreteEventVO ent = new DiscreteEventVO();
            ent.setType(1);//到达
            ent.setTime(i / 10);
            this.eventList.add(ent);

            this.onService[i] = null;
        }
    }

    private void occupyService(CustomerVO c) {
        //下一离开事件
        DiscreteEventVO ent = new DiscreteEventVO();
        ent.setType(2);
        ent.setTime(simulationClock + c.getServiceTime());
        ent.setQueueNo(currentEvent.getQueueNo());
        eventList.add(ent);
    }

    private void simulation() {
        init();
        while (simulationClock <= 5000) {
            timing();
        }
    }

    public void run() {
        double totalMean = 0;
        int simulationCount = 500;
        resultStr = "<html>\n<table border=\"2\">\n<tr><td>次数</td><td>平均逗留时间</td><td>总逗留时间</td><td>总顾客数</td><td>模拟时间</td></tr>\n";
        System.out.println(resultStr);

        for (int i = 1; i <= simulationCount; i++) {
            simulation();
            totalMean += totalStayTime / totalCustomerCount;

            resultStr = "<tr><td>" + i + "</td>" +                                           //模拟次数
                    "<td>" + (totalStayTime / totalCustomerCount) + "</td>" + //平均逗留时间
                    "<td>" + totalStayTime + "</td>" +                        //总逗留时间
                    "<td>" + totalCustomerCount + "</td>" +                   //总服务人数
                    "<td>" + simulationClock + "</td></tr>";                  //模拟时间

            System.out.println(resultStr);
        }

        resultStr = "<tr><td colspan='4'>" + simulationCount + " times simulation average AverageStayTime</td><td>" +
                (totalMean / simulationCount) + "</td></tr>";

        System.out.println(resultStr);
    }
}
