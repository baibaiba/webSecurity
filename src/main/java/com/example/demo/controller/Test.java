package com.example.demo.controller;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.MathContext;

public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(125.7394815);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(8);
        // 4.555/1.3=3.50384615...
        BigDecimal divide = bigDecimal.divide(bigDecimal1);
        System.out.println(divide);

        // create 3 BigDecimal objects
        BigDecimal bg1, bg2, bg3;
        bg1 = new BigDecimal("125");
        bg2 = new BigDecimal("3");
        // 125/3=41.6666666...
        MathContext mc = new MathContext(1);
        bg3 = bg1.divide(bg2, mc);
        System.out.println(bg3);
        WebApplicationContext currentWebApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        Object bean = currentWebApplicationContext.getBean("");
    }
}
