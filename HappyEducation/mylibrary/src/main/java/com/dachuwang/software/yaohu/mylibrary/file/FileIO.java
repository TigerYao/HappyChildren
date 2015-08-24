package com.dachuwang.software.yaohu.mylibrary.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by yaohu on 15/8/24.
 * email yaohu@dachuwang.com
 */
public class FileIO {
    public static void fromCsvToDb(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            int i = 0;
            String strLine = null;
            String sql = null;
            try {
                BufferedReader bufferedreader = new BufferedReader(new FileReader(filePath));
                while ((strLine = bufferedreader.readLine()) != null) {
                    i++;
                    String[] values = strLine.split(",");//逗号隔开的各个列
                    String cell0 = values[0];
                    String cell1 = values[1];
                    String cell2 = values[2];
                    String cell3 = values[3];
                    String cell4 = values[4];
                    String cell5 = values[5];
                    String cell6 = values[6];
                    String cell7 = values[7];
                    if (i % 500 == 0) {
                        //500条记录提交一次
                        System.out.println("已成功提交" + i + "行!");
                    }
                }
                if (i % 500 != 0) {
                    //不够500条的再提交一次（其实不用判断，直接提交就可以，不会重复提交的）
                    System.out.println("已成功提交" + i + "行!");
                }

            } catch (Exception ex) {
                System.out.println("导出第" + (i + 1) + "条时出错，数据是" + strLine);
                System.out.println("出错的sql语句是：" + sql);
                System.out.println("错误信息：");
                ex.printStackTrace();

            } finally {

            }
        }
    }
}
