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

    private List<CustomerVO>[] queue_process_3;

    public Process3() {
        this.queue_process_3 = new ArrayList[3];
        this.queue_process_3[0] = new ArrayList<CustomerVO>();
        this.queue_process_3[1] = new ArrayList<CustomerVO>();
        this.queue_process_3[2] = new ArrayList<CustomerVO>();
    }

    @Override
    public void arrival() {
        super.arrival();

        CustomerVO customerVO = new CustomerVO();
        customerVO.setArrivalTime(simulation_clock);
        customerVO.setServiceTime(MyRandomNum.getUnif(8, 10));
        queue_process_3[current.getQueueNo()].add(customerVO);

        DiscreteEventVO nextArrival = new DiscreteEventVO();
        nextArrival.setType(1);
        nextArrival.setTime(simulation_clock + MyRandomNum.getExpon(10));
        nextArrival.setQueueNo(current.getQueueNo());
        eventList.add(nextArrival);

        if (status[current.getQueueNo()] == 0) {
            status[current.getQueueNo()] = 1;
            onService(customerVO);
        }
    }

    @Override
    public void departure() {
        super.departure();

        CustomerVO c = queue_process_3[current.getQueueNo()].get(0);
        queue_process_3[current.getQueueNo()].remove(0);

        customer_total_stay_time += simulation_clock - c.getArrivalTime();
        customer_amount += 1;

        if (queue_process_3[current.getQueueNo()].size() == 0)  {
            status[current.getQueueNo()] = 0;
        } else {
            c = queue_process_3[current.getQueueNo()].get(0);
            onService(c);
        }
    }

    @Override
    public void queueStart() {
        super.queueStart();

        for (int i = 0; i < 3; i++) {
            DiscreteEventVO ent = new DiscreteEventVO();
            ent.setType(1);
            ent.setTime(0);
            ent.setQueueNo(i);
            eventList.add(ent);
        }
    }
}
