package com.geebo.Queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 2:25 2020/3/22
 */
public class LoopQueueTest {
    @Test
    public void testLoopQueue() {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
        }
        // 初始化队列数据
        System.out.println("原始队列: " + loopQueue);
        // 元素0出队
        loopQueue.dequeue();
        System.out.println("元素0出队: " + loopQueue);
        loopQueue.dequeue();
        System.out.println("元素1出队: " + loopQueue);
        loopQueue.dequeue();
        System.out.println("元素2出队: " + loopQueue);
        loopQueue.dequeue();
        System.out.println("元素3出队: " + loopQueue);
        loopQueue.dequeue();
        System.out.println("元素4出队,发生缩容: " + loopQueue);
        // 队首元素
        System.out.println("队首元素：" + loopQueue.getFront());


        ArrayList list = new ArrayList();
        System.arraycopy(list, 0, list, 4, list.size());







    }
}
