package com.nightwingky.simulation;

import com.nightwingky.random.MyRandomNum;
import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-30.
 */
public class Process3 extends BaseProcess {

    private List<CustomerVO>[] queue_step1_3;

    public Process3() {
        this.queue_step1_3 = new ArrayList[3];
        this.queue_step1_3[0] = new ArrayList<CustomerVO>();
        this.queue_step1_3[1] = new ArrayList<CustomerVO>();
        this.queue_step1_3[2] = new ArrayList<CustomerVO>();
    }

    @Override
    public void arrivalStep1() {
        super.arrivalStep1();

        CustomerVO customerVO = new CustomerVO();
        customerVO.setArrivalTime(simulation_clock);
        customerVO.setStep1ServiceTime(MyRandomNum.getUnif(8, 10));
        queue_step1_3[current.getQueueNo()].add(customerVO);

        DiscreteEventVO nextArrival = new DiscreteEventVO();
        nextArrival.setType(1);
        nextArrival.setTime(simulation_clock + MyRandomNum.getExpon(10));
        nextArrival.setQueueNo(current.getQueueNo());
        eventList.add(nextArrival);

        if (status[current.getQueueNo()] == 0) {
            status[current.getQueueNo()] = 1;
            onServiceStep1(customerVO);
        }
    }

    @Override
    public void departureStep1() {
        super.departureStep1();

        CustomerVO c = queue_step1_3[current.getQueueNo()].get(0);
        queue_step1_3[current.getQueueNo()].remove(0);

        step1StayTime = simulation_clock - c.getArrivalTime();
        step1_total_stay_time += step1StayTime;
        customer_amount += 1;

        if (queue_step1_3[current.getQueueNo()].size() == 0)  {
            status[current.getQueueNo()] = 0;
        } else {
            c = queue_step1_3[current.getQueueNo()].get(0);
            onServiceStep1(c);

            this.arrivalStep2(current, c);
        }
    }

    @Override
    public void queueStep1Start() {
        super.queueStep1Start();

        for (int i = 0; i < 3; i++) {
            DiscreteEventVO ent = new DiscreteEventVO();
            ent.setType(1);
            ent.setTime(0);
            ent.setQueueNo(i);
            eventList.add(ent);
        }
    }
}
