package com.example.demo.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例线程池
 *
 * @author stream
 */
public class ThreadExecutor {

    private ExecutorService threadPool;

    private ThreadExecutor() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = 100;
        int keepAliveTime = 60 * 1000;

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5000);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadFactory factory = new ThreadFactory() {
            private final AtomicInteger integer = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "TheadPool-Thread - " + integer.getAndIncrement());
            }
        };
        threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, factory, handler);
    }

    public static ThreadExecutor getInstance() {
        return SingleTon.SINGLE_TON.executor;
    }

    public void execute(Runnable command) {
        this.threadPool.execute(command);
    }

    public void submit(Runnable command) {
        this.threadPool.submit(command);
    }

    private enum SingleTon {

        /**
         * 线程池
         */
        SINGLE_TON(new ThreadExecutor());

        ThreadExecutor executor;

        SingleTon(ThreadExecutor executor) {
            this.executor = executor;
        }
    }
}
