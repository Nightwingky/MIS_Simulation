package com.nightwingky.simulation;

import com.nightwingky.random.MyRandomNum;
import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;

/**
 * Created by nightwingky on 17-3-30.
 */
public class Process1 extends BaseProcess{

    @Override
    public void arrival() {
        super.arrival();

        CustomerVO customerVO = new CustomerVO();
        customerVO.setArrivalTime(simulationClock);
        customerVO.setServiceTime(MyRandomNum.getUnif(8, 10));
        customerVO.setStatus(0);
        queue.add(customerVO);

        //下一次arrival
        DiscreteEventVO nextArrival = new DiscreteEventVO();
        nextArrival.setType(1);
        nextArrival.setTime(simulationClock + MyRandomNum.getExpon(10));
        eventList.add(nextArrival);

        //如果有服务人员空闲，开始服务当前顾客
        for (int i = 0; i < 3; i++) {
            if (status[i] == 0) {
                status[i] = 1;
                currentEvent.setQueueNo(i);

                CustomerVO tmp = queue.get(0);
                tmp.setStatus(1);

                //将顾客移入服务中数组，并从队伍中移除
                onService[i] = tmp;
                queue.remove(0);

                occupyService(tmp);
                break;
            }
        }
    }

    @Override
    public void departure() {
        super.departure();

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

    @Override
    public void queueRun() {
        super.queueRun();

        this.onService = new CustomerVO[3];

        for (int i = 0; i < 3; i++)  {
            DiscreteEventVO ent = new DiscreteEventVO();
            ent.setType(1);//到达
            ent.setTime(i / 10);
            this.eventList.add(ent);

            this.onService[i] = null;
        }
    }
}
