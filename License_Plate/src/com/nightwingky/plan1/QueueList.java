package com.nightwingky.plan1;

import com.nightwingky.random.MyRandomNum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightwingky on 17-3-26.
 * 每一条队伍是一个子线程
 */
public class QueueList extends Thread {

    //volatile修饰的变量在各线程内可见
    volatile boolean isRunning = false;

    //表示本队伍的状态，0表示正在等待，１表示正在处理
    public int status = 0;

    //表示第几条队列
    public int type;
    //List存放本队列的成员
    public List<CustomerVO> mList = new ArrayList<>();
    //表示本队伍的时间轴
    public double timeline = 0.00;

    public void addCustomer(CustomerVO vo) {
        this.mList.add(vo);
    }

    @Override
    public void run() {
        super.run();

        while (isRunning) {
            /**
             * 判断是否有队头成员，如果队伍中没人，不进行操作，循环继续
             */
            if (mList.size() == 0) {
                this.status = 0;
                continue;
            }

            /**
             * 如果队伍中有人，但是窗口处于等待状态，则获取队头成员，办理事项，将队伍状态改为正在处理
             */
            if (status == 0) {
                //获取队头成员
                CustomerVO cvo = mList.get(0);

                //确定队头成员处理时间
                double dealing_time = MyRandomNum.getUnif(8, 10);
                cvo.setDealing_time(dealing_time);
                status = 1;
                //开始处理
                deal(cvo);
            }
            /**
             * 如果队伍处于正在处理的状态，此时将新来的顾客加入队伍并添加等待时间
             *
             * 新顾客随着时间轴的移动一个一个加入，所以此时只需要获取队伍中最后一名成员添加等待时间即可，否则会重复
             */
            else if (status == 1) {
                CustomerVO cvo = mList.get(mList.size() - 1);
                /**
                 * 此时队伍中的第一名成员正在处理，刚来的新顾客等待时间加上第一名顾客处理完的时间减去现在的时间
                 */
                cvo.setWaiting_time(mList.get(0).getArrive_time() +
                        mList.get(0).getDealing_time() - cvo.getArrive_time());
            }


        }
    }

    private void deal(CustomerVO cvo) {
//        timeline += cvo.getDealing_time();
        System.out.println(cvo);
        mList.remove(0);
        status = 0;
    }
}


//时间轴移动
//                if (mList.size() > 1) {
//                        CustomerVO c = mList.get(mList.size() - 1);
//                        if (c.getArrive_time() < (timeline + cvo.getDealing_time())) {
//        c.setWaiting_time(timeline + cvo.getDealing_time() - c.getArrive_time());
//        timeline = c.getArrive_time();
//        }
//        }