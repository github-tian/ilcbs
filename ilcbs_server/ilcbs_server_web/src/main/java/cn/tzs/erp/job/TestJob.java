package cn.tzs.erp.job;

import cn.tzs.utils.UtilFuns;

import java.text.ParseException;
import java.util.Date;

public class TestJob {
    public void showTime() throws ParseException {
        System.out.println(UtilFuns.dateTimeFormat(new Date(),"HH:mm:ss"));
        System.out.println(new Date(System.currentTimeMillis()).toString());
        System.out.println("----------------------------------------------");
    }
}
