package com.ai.spring.cloud.auth;

import org.junit.Test;

import java.util.stream.IntStream;

public class TestMultiThread {

    @Test
    public void testCounter() throws InterruptedException {
        int times = 100;
        Counter counter = new Counter();
        Runnable worker = () -> IntStream.range(0, times).forEach(i -> counter.number++);

        Thread t1 = new Thread(worker);
        Thread t2 = new Thread(worker);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.number);
    }

    class Counter {
        volatile int number = 0;
    }

}
