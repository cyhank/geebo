package com.geebo.test;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 11:44 2020/4/19
 */
public class Demo2 extends Test{

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                sleep(2000);
                System.out.println("线程1, 等lock2");
                synchronized (lock2) {
                    System.out.println("线程1完成");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                sleep(2000);
                System.out.println("线程2, 等lock1");
                synchronized (lock1) {
                    System.out.println("线程2完成");
                }
            }
        }).start();
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
