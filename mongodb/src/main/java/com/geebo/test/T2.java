package com.geebo.test;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 12:04 2020/4/19
 */
public class T2 {
    public static void main(String[] args) throws InterruptedException {
        final TestChild t = new TestChild();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.doSomething();
                System.out.println("run : "+ this.toString());
            }
        }).start();
        Thread.sleep(100);
        t.doSomethingElse();
    }

    public synchronized void doSomething() {
        System.out.println("T2 : "+ this.toString());
        System.out.println("something sleepy!");
        try {
            Thread.sleep(1000);
            System.out.println("woke up!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class TestChild extends T2 {
        public void doSomething() {
            super.doSomething();
        }

        public synchronized void doSomethingElse() {
            System.out.println("TestChild super: "+ super.toString());
            System.out.println("TestChild this: "+ this.toString());
            System.out.println("something else");
        }
    }
}