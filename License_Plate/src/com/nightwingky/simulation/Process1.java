package com.nightwingky.simulation;

import com.nightwingky.random.MyRandomNum;
import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-30.
 */
public class Process1 extends BaseProcess{

    private List<CustomerVO> queue_process_1;

    public Process1() {
        this.queue_process_1 = new ArrayList<CustomerVO>();
    }

    @Override
    public void arrival() {
        super.arrival();

        CustomerVO customerVO = new CustomerVO();
        customerVO.setArrivalTime(simulation_clock);
        customerVO.setServiceTime(MyRandomNum.getUnif(8, 10));
        customerVO.setStatus(0);
        queue_process_1.add(customerVO);

        DiscreteEventVO nextArrival = new DiscreteEventVO();
        nextArrival.setType(1);
        nextArrival.setTime(simulation_clock + MyRandomNum.getExpon(10));
        eventList.add(nextArrival);

        for (int i = 0; i < 3; i++) {
            if (status[i] == 0) {
                status[i] = 1;
                current.setQueueNo(i);

                CustomerVO tmp = queue_process_1.get(0);
                tmp.setStatus(1);

                on_service_customer[i] = tmp;
                queue_process_1.remove(0);

                onService(tmp);
                break;
            }
        }
    }

    @Override
    public void departure() {
        super.departure();

        CustomerVO c = on_service_customer[current.getQueueNo()];
        on_service_customer[current.getQueueNo()] = null;

        customer_total_stay_time += simulation_clock - c.getArrivalTime();
        customer_amount += 1;

        if (queue_process_1.size() == 0) {
            status[current.getQueueNo()] = 0;
        } else {
            CustomerVO tmp = queue_process_1.get(0);
            tmp.setStatus(1);

            on_service_customer[current.getQueueNo()] = tmp;
            queue_process_1.remove(0);
            onService(tmp);
        }
    }

    @Override
    public void queueStart() {
        super.queueStart();

        this.on_service_customer = new CustomerVO[3];

        for (int i = 0; i < 3; i++)  {
            DiscreteEventVO ent = new DiscreteEventVO();
            ent.setType(1);
            ent.setTime(i / 10);
            this.eventList.add(ent);

            this.on_service_customer[i] = null;
        }
    }
}
