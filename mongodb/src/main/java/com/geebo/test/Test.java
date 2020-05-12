package com.geebo.test;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 15:48 2020/4/14
 */
public class Test implements Runnable {
    private int count = 10;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "  count = " + count);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            Test t = new Test();
            t.run();
            new Thread(t, "THREAD:" + i).start();
        }
    }

    synchronized void testSuper() {
        System.out.println("test class");
    }
}
