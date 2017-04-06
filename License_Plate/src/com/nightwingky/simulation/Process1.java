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

    private List<CustomerVO> queue_step1_1;

    public Process1() {
        this.queue_step1_1 = new ArrayList<CustomerVO>();
    }

    @Override
    public void arrivalStep1() {
        super.arrivalStep1();

        CustomerVO customerVO = new CustomerVO();
        customerVO.setArrivalTime(simulation_clock);
        customerVO.setStep1ServiceTime(MyRandomNum.getUnif(8, 10));
        customerVO.setStatus(0);
        queue_step1_1.add(customerVO);

        DiscreteEventVO nextArrival = new DiscreteEventVO();
        nextArrival.setType(1);
        nextArrival.setTime(simulation_clock + MyRandomNum.getExpon(10));
        eventList.add(nextArrival);

        for (int i = 0; i < 3; i++) {
            if (status[i] == 0) {
                status[i] = 1;
                current.setQueueNo(i);

                CustomerVO tmp = queue_step1_1.get(0);
                tmp.setStatus(1);

                on_service_customer[i] = tmp;
                queue_step1_1.remove(0);

                onServiceStep1(tmp);
                break;
            }
        }
    }

    @Override
    public void departureStep1() {
        super.departureStep1();

        CustomerVO c = on_service_customer[current.getQueueNo()];
        on_service_customer[current.getQueueNo()] = null;

        step1StayTime = simulation_clock - c.getArrivalTime();
        step1_total_stay_time += step1StayTime;
        customer_amount += 1;

        if (queue_step1_1.size() == 0) {
            status[current.getQueueNo()] = 0;
        } else {
            CustomerVO tmp = queue_step1_1.get(0);
            tmp.setStatus(1);

            on_service_customer[current.getQueueNo()] = tmp;
            onServiceStep1(tmp);

            CustomerVO vo = queue_step1_1.get(0);

            //开启过程二
            this.arrivalStep2(current, vo);

            queue_step1_1.remove(0);
        }
    }

    @Override
    public void queueStep1Start() {
        super.queueStep1Start();

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
