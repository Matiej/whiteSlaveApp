package com.whiteslave.whiteslaveApp;

import java.time.LocalDate;
import java.util.Date;

public class App {

    public static void main(String[] args) {
        String s = LocalDate.now().toString();
        System.out.println(s);
        Date date = new Date();
        System.out.println(date);
    }
}
