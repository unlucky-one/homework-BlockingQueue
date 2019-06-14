package com.raiden.homework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: Raiden
 * Date: 2019/6/14
 */
public class Test {
    static BlockingQueue queue = new LinkedBlockingQueue();

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(new Producer("消息:" + i)).start();
        }

        new Thread(new Consumer()).start();
        new Thread(new Producer("exit")).start();
    }

    static class Producer implements Runnable {
        String msg = "";

        public Producer(String msg) {
            this.msg = msg;
        }

        public void run() {
            queue.add(msg);
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            try {
                String msg = "";
                while (true) {
                    msg = queue.take().toString();
                    if ("exit".equals(msg))
                        return;
                    Thread.sleep(10);
                    System.out.println("Consumed " + msg);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
