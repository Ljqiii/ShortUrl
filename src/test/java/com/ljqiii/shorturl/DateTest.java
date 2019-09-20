package com.ljqiii.shorturl;

import org.junit.Test;


public class DateTest {

    @Test
    public void testSystemtime() throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis());
    }


    @Test
    public void testString(){
        String a="http://127.0.0.1:8080/add";
        a.split("/");
        a.getBytes();
    }


}
