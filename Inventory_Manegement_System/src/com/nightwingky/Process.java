package com.nightwingky;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nightwingky on 17-3-8.
 */
public class Process {

    private Random random = new Random();

    //当前库存
    private double inventory_now;

    //当前时间
    private double time_now;

    //进货量
    private double purchase_amount;

    //当前进货时间延迟
    private double purchase_time;

    //当前需求到达间隔时间
    private double demand_time_now;

    //表示第ｘ次需求
    private int demand_x;

    private List<List<String>> dataList = new ArrayList<List<String>>();
    private List<String> mList;

    public void processStart() throws IOException {

        mList = new ArrayList<String>();
        mList.add("时间");
        mList.add("当前库存");
        mList.add("需求量");
        mList.add("需求间隔时间");
        mList.add("进货量");
        mList.add("进货延迟时间");
        dataList.add(mList);

        inventory_now = MyConst.getInventory_start();
        time_now = 0;
        demand_x = 0;

        while (true) {

            if (time_now > 120) {
                break;
            }

            //获取需求量
            int demand = MyConst.getDemandAmount().get(random.nextInt(6));
            //获取需求到达间隔时间
            demand_time_now = MyConst.getDemandTime().get(demand_x);

            mList = new ArrayList<String>();
            mList.add(String.valueOf(time_now));
            mList.add(String.valueOf(inventory_now));
            mList.add(String.valueOf(demand));
            mList.add(String.valueOf(demand_time_now));

            //判断当前库存是否小于安全库存
            if (inventory_now < MyConst.getInventory_min()) {
                //获取进货量
                purchase_amount = MyConst.getInventory_max() - inventory_now;
                mList.add(String.valueOf(purchase_amount));
                //获取进货时间延迟，服从0-2均匀分布
                purchase_time = (random.nextDouble() * 2);
                mList.add(String.valueOf(purchase_time));
                dataList.add(mList);

                //进货时间 > 需求到达间隔时间
                while(purchase_time > demand_time_now) {
                    time_now += demand_time_now;
                    demand_x ++;
                    mList = new ArrayList<String>();
                    demand = demand + MyConst.getDemandAmount().get(random.nextInt(6));
                    demand_time_now = MyConst.getDemandTime().get(demand_x);
                    if (demand_time_now < purchase_time) {
                        purchase_time = purchase_time - demand_time_now;
                    }

                    mList.add(String.valueOf(time_now));
                    mList.add(String.valueOf(inventory_now));
                    mList.add(String.valueOf(demand));
                    mList.add(String.valueOf(demand_time_now));
                    mList.add(String.valueOf(purchase_amount));
                    mList.add(String.valueOf(purchase_time));
                    dataList.add(mList);
                }

                //进货到达
                time_now += purchase_time;
                inventory_now += purchase_amount;

                //发货
                time_now = time_now + demand_time_now - purchase_time;
                inventory_now -= demand;
                demand_x ++;
            } else {
                //时间条走动
                time_now += demand_time_now;
                //发货
                inventory_now -= demand;
                demand_x ++;

                mList.add(String.valueOf(0));
                mList.add(String.valueOf(0));
                dataList.add(mList);
            }

        }

        printList();
    }

    private void printList() throws IOException {
        String path = "result.html";

        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file, false);

        StringBuilder sb = new StringBuilder();
        sb.append("<h2>信管14-2&nbsp;&nbsp;140614406&nbsp;&nbsp;阙琨洋</h2>\n<h3>库存管理系统模拟</h3>");
        sb.append("<html>\n<table border=\"2\">\n");
        fileOutputStream.write(sb.toString().getBytes("utf-8"));

        for (List<String> m : dataList) {
            sb = new StringBuilder();
            sb.append("<tr>");
            for (String s : m) {
                sb.append("<td>" + s + "</td>");
            }
            sb.append("</tr>\n");
            fileOutputStream.write(sb.toString().getBytes("utf-8"));
        }

        sb = new StringBuilder();
        sb.append("</html>\n</table>\n");
        fileOutputStream.write(sb.toString().getBytes("utf-8"));

        fileOutputStream.close();
    }

}
