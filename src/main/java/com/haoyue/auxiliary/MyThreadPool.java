package com.haoyue.auxiliary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chen1 on 2018/1/15.
 * TO DO:
 */

public class MyThreadPool {

    /**
     * TODO:创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * @param ThreadPoolNum
     */
    public static void newCachedThreadPool(int ThreadPoolNum){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < ThreadPoolNum; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    /**
     * TODO:创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * @param sleepTime
     */
    public static void newFixedThreadPool(int ThreadPoolNum, final long sleepTime){
        ThreadPoolExecutor poolExecutor;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < ThreadPoolNum; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     *
     * @param time
     * @param timeBode:TimeUnit.SECONDS......
     * TODO:创建一个定长线程池，支持定时及周期性任务执行
     */
    public static void newScheduledThreadPool(int time, TimeUnit timeBode){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, time, timeBode);
    }

    /**
     * TODO:创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     * @param ThreadPoolNum
     */
    public static void newSingleThreadExecutor(int ThreadPoolNum){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < ThreadPoolNum; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
