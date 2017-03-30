package com.nightwingky.simulation;

import com.nightwingky.random.MyRandomNum;
import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;

/**
 * Created by nightwingky on 17-3-30.
 */
public class Process3 extends BaseProcess {

    @Override
    public void arrival() {
        super.arrival();

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

    @Override
    public void departure() {
        super.departure();

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

    @Override
    public void queueRun() {
        super.queueRun();

        for (int i = 0; i < 3; i++) {
            DiscreteEventVO ent = new DiscreteEventVO();
            ent.setType(1); //到达
            ent.setTime(0);
            ent.setQueueNo(i);
            eventList.add(ent);
        }
    }
}
