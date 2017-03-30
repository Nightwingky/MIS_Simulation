package com.nightwingky;

import com.nightwingky.simulation.Process1;
import com.nightwingky.simulation.Process3;
import com.nightwingky.vo.ResultVO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static void output(List<ResultVO> resultVOList, String filePath) throws IOException {

        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n<h2>信管14-2&nbsp;&nbsp;140614406&nbsp;&nbsp;阙琨洋</h2>\n");

        sb.append("<table border=\"2\">\n<tr><th>模拟次数</th><th>平均逗留时间</th><th>总逗留时间</th><th>顾客人数</th>");
        fileOutputStream.write(sb.toString().getBytes("utf-8"));

        int num = 1;

        for (ResultVO r : resultVOList) {
            sb = new StringBuilder();
            sb.append("<tr><td>" + num + "</td>" +
                    "<td>" + r.getAvgStayTime() + "</td>" +
                    "<td>" + r.getTotalStayTime() + "</td>" +
                    "<td>" + r.getTotalCustomerCount() + "</td></tr>\n"
            );
            fileOutputStream.write(sb.toString().getBytes("utf-8"));
        }

        sb = new StringBuilder();
        sb.append("</table>\n</html>\n");
        fileOutputStream.write(sb.toString().getBytes("utf-8"));

        fileOutputStream.close();
    }

    private static void run() throws IOException {
        List<ResultVO> resultList;

        resultList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            resultList.add(new Process1().run());
        }
        output(resultList, "one.html");

        resultList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            resultList.add(new Process3().run());
        }
        output(resultList, "three.html");
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        run();
    }
}
