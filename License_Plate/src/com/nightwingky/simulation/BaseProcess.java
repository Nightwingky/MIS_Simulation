package com.nightwingky.simulation;

import com.nightwingky.random.MyRandomNum;
import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;
import com.nightwingky.vo.ResultVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-30.
 */
public class BaseProcess implements EventStep1Intf, EventStep2Intf{

    //step1
    protected double simulation_clock;
    protected double step1_total_stay_time;

    protected List<DiscreteEventVO> eventList;

    protected CustomerVO[] on_service_customer;

    protected DiscreteEventVO current;
    protected int[] status;
    protected int customer_amount;

    protected double step1StayTime;

    //step2
    protected List<CustomerVO> queue_process_2;

    protected double step2_total_stay_time;
    protected double step2StayTime;


    public BaseProcess() {
        //step1
        this.simulation_clock = 0;
        this.step1_total_stay_time = 0;
        this.customer_amount = 0;

        this.eventList = new ArrayList<DiscreteEventVO>();

        this.current = null;

        this.status = new int[3];
        this.status[0] = 0;
        this.status[1] = 0;
        this.status[2] = 0;

        //step2
        this.queue_process_2 = new ArrayList<>();
    }

    protected void timing() {
        double minTime = 1.0e+29;
        current = null;

        for (DiscreteEventVO eventVO : eventList) {

            if (eventVO.getTime() <= minTime) {
                minTime = eventVO.getTime();
                current = eventVO;
            }
        }

        if (current != null) {
            simulation_clock = current.getTime();
            eventList.remove(current);
        }
    }

    protected void simulationStart() {
        queueStep1Start();

        while (simulation_clock <= 5000) {
            timing();
            switch (current.getType()) {
                case 1:
                    arrivalStep1();
                    break;
                case 2:
                    departureStep1();
                    break;
                case 3:
                    departureStep2();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 过程一
     */
    //开始
    @Override
    public void queueStep1Start() {
        //子类覆盖
    }

    //到达
    @Override
    public void arrivalStep1() {
        //子类覆盖
    }

    //离开
    @Override
    public void departureStep1() {
        //子类覆盖
    }

    //处理
    @Override
    public void onServiceStep1(CustomerVO c) {

        DiscreteEventVO eventVO = new DiscreteEventVO();

        eventVO.setType(2);
        eventVO.setTime(simulation_clock + c.getStep1ServiceTime());
        eventVO.setQueueNo(current.getQueueNo());

        eventList.add(eventVO);
    }

    /**
     * 过程二
     */
    //到达
    @Override
    public void arrivalStep2(DiscreteEventVO event, CustomerVO c) {
        event.setType(3);
        event.setTime(c.getArrivalTime() + step1StayTime);
        eventList.add(event);

        c.setStatus(0);
        queue_process_2.add(c);
    }

    //离开
    @Override
    public void departureStep2() {
        if (queue_process_2.size() != 0) {
            CustomerVO c = queue_process_2.get(0);
            c.setStep2ServiceTime(MyRandomNum.getUnif(2.66, 3.33));

            c.setStatus(2);
            onServiceStep2(c);

            // TODO: 17-3-31
            System.out.print("step1StayTime:" + step1StayTime + " ");
            System.out.print("simulation_clock:" + simulation_clock + " ");
            System.out.print("getArrivalTime:" + c.getArrivalTime() + " ");
            step2StayTime = simulation_clock - c.getArrivalTime() + step1StayTime;
            System.out.println("step2StayTime:" + step2StayTime);
            step2_total_stay_time += step2StayTime;

            System.out.println(c);
            queue_process_2.remove(c);
        }

    }

    //处理
    @Override
    public void onServiceStep2(CustomerVO c) {
        DiscreteEventVO eventVO = new DiscreteEventVO();

        eventVO.setType(3);
        eventVO.setTime(simulation_clock + c.getStep2ServiceTime());

        eventList.add(eventVO);
    }

    public ResultVO run() {
        simulationStart();

        ResultVO resultVO = new ResultVO(
                step1_total_stay_time / customer_amount,
                step1_total_stay_time,
                step2_total_stay_time / customer_amount,
                step2_total_stay_time,
                (step1_total_stay_time + step2_total_stay_time) / customer_amount,
                step1_total_stay_time + step2_total_stay_time,
                customer_amount,
                simulation_clock
        );

        System.out.println(resultVO);

        return resultVO;
    }
}
