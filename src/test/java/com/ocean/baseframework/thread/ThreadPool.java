package com.ocean.baseframework.thread;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
        new ThreadPoolExecutor(1, 10, 1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}
