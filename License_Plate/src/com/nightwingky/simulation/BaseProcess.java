package com.nightwingky.simulation;

import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;
import com.nightwingky.vo.ResultVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-30.
 */
public class BaseProcess implements EventIntf {

    protected double simulation_clock;
    protected double customer_total_stay_time;

    protected List<DiscreteEventVO> eventList;

    protected CustomerVO[] on_service_customer;

    protected DiscreteEventVO current;
    protected int[] status;
    protected int customer_amount;

    public BaseProcess() {
        this.simulation_clock = 0;
        this.customer_total_stay_time = 0;
        this.customer_amount = 0;

        this.eventList = new ArrayList<DiscreteEventVO>();

        this.current = null;

        this.status = new int[3];
        this.status[0] = 0;
        this.status[1] = 0;
        this.status[2] = 0;
    }

    protected void timing() {
        double minTime = 1.0e+29;
        current = null;

        for (DiscreteEventVO eventVO : eventList) {
            if (eventVO.getTime() < minTime) {
                minTime = eventVO.getTime();
                current = eventVO;
            }
        }

        if (current != null) {
            simulation_clock = current.getTime();
            eventList.remove(current);
        }
    }

    protected void onService(CustomerVO c) {

        DiscreteEventVO eventVO = new DiscreteEventVO();

        eventVO.setType(2);
        eventVO.setTime(simulation_clock + c.getServiceTime());
        eventVO.setQueueNo(current.getQueueNo());

        eventList.add(eventVO);
    }

    protected void simulationStart() {
        queueStart();

        while (simulation_clock <= 5000) {
            timing();
            switch (current.getType()) {
                case 1:
                    arrival();
                    break;
                case 2:
                    departure();
                    break;
            }
        }
    }

    public ResultVO run() {
        simulationStart();

        ResultVO resultVO = new ResultVO(
                customer_total_stay_time / customer_amount,
                customer_total_stay_time,
                customer_amount,
                simulation_clock
        );

        System.out.println(resultVO);

        return resultVO;
    }

    @Override
    public void arrival() {

    }

    @Override
    public void departure() {

    }

    @Override
    public void queueStart() {

    }
}
