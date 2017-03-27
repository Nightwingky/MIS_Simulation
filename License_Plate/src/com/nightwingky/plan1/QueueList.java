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

    //表示第几条队列
    public int type;
    //表示本队伍的状态，0表示正在等待，１表示正在处理
    public int status = 0;
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
            //判断是否有队头成员
            if (mList.size() == 0) {
                this.status = 0;
                continue;
            }

            if (status == 0) {
                //获取队头成员
                CustomerVO cvo = mList.get(0);

                //确定队头成员处理时间
                double dealing_time = MyRandomNum.getUnif(8, 10);
                cvo.setDealing_time(dealing_time);
                //时间轴移动
                if (mList.size() > 1) {
                    CustomerVO c = mList.get(mList.size() - 1);
                    if (c.getArrive_time() < (timeline + cvo.getDealing_time())) {
                        c.setWaiting_time(timeline + cvo.getDealing_time() - c.getArrive_time());
                        timeline = c.getArrive_time();
                    }
                }
            }


        }
    }
}
