package com.liusx.juc.futuretask;

import java.util.concurrent.*;

public class FutureTaskTest {
    static ExecutorService executorService = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {

                return "数据";
            }
        };
        // 利用线程池获取数据
        Future<String> submit = executorService.submit(callable);
        System.out.println(submit.get());

        // 利用futureTask 获取数据
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        // futureTask 核心思想
        FutureTaskDemo<String> futureTaskDemo = new FutureTaskDemo<>(callable);
        new Thread(futureTaskDemo).start();
        System.out.println(futureTaskDemo.get());

    }
}
