package com.liusx.juc.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class FutureTaskDemo<T> implements Runnable {
    private Callable<T> callable;

    volatile String state = "NEW";

    T result;

    LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>();

    public FutureTaskDemo(Callable<T> callable) {
        this.callable = callable;
    }

    public T get() {
        if ("END".equals(state)) {
            return result;
        }
        // 开始执行阻塞
        while (!"END".equals(state)) {
            // 准备一个容器存放线程
            queue.offer(Thread.currentThread());
            LockSupport.park();
        }
        return result;
    }


    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            state = "END";
        }
        Thread waiter = queue.poll();
        while (waiter != null) {
            LockSupport.unpark(waiter);
            waiter = queue.poll();
        }
    }
}
