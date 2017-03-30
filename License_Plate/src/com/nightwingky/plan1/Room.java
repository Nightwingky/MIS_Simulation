package com.nightwingky.plan1;

import com.nightwingky.random.MyRandomNum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-26.
 */
public class Room extends Thread {

    public double total_time_line = 0;

    private List<Double> arrive_time_list = new ArrayList<>();

    public Room() {
        set_arrival_list();
    }

    private void set_arrival_list() {
        double sum = 0.00;

        while (sum <= 5000) {
            double arrive_time = MyRandomNum.getExpon(10);
            sum += arrive_time;
            this.arrive_time_list.add(sum);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Room().addClient();
    }

    private void addClient() throws InterruptedException {
        QueueList list1 = new QueueList();
        Thread threadList1 = new Thread(list1, "threadList1");
        threadList1.start();
        list1.isRunning = true;

        while (total_time_line <= 5000.00) {

            CustomerVO customerVO = new CustomerVO(1, this.arrive_time_list.get(0));
            list1.addCustomer(customerVO);
            System.out.println("add");

            total_time_line = this.arrive_time_list.get(0);
            list1.timeline = total_time_line;
            this.arrive_time_list.remove(0);

            Thread.yield();

//            if (total_time_line <= list1.timeline) {
//                CustomerVO customerVO = new CustomerVO(1, this.arrive_time_list.get(0));
//                list1.addCustomer(customerVO);
//                total_time_line = this.arrive_time_list.get(0);
//                this.arrive_time_list.remove(0);
//            } else {
//                list1.timeline = total_time_line;
//                CustomerVO customerVO = new CustomerVO(1, this.arrive_time_list.get(0));
//                list1.addCustomer(customerVO);
//                total_time_line = this.arrive_time_list.get(0);
//                this.arrive_time_list.remove(0);
//            }

        }
    }

    @Override
    public void run() {
        super.run();

        try {
            addClient();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
