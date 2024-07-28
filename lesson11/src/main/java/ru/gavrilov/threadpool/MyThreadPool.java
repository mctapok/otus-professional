package ru.gavrilov.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {
    LinkedList<Runnable> taskQueue = new LinkedList<>();
    private List<PoolThreadRunnable> threadRunnables = new ArrayList<>();
    private boolean isStopped = false;

    public MyThreadPool(int threads) {
        for (int i = 1; i <= threads; i++) {
            PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
            threadRunnables.add(poolThreadRunnable);
        }
        for (PoolThreadRunnable p : threadRunnables) new Thread(p).start();
    }

    public void execute(Runnable task) {
        if(this.isStopped) throw new IllegalStateException("Thread pool is stopped");
        synchronized (taskQueue) {
            this.taskQueue.addLast(task);
            taskQueue.notify();
        }
    }

    public synchronized void waitUntilAllTaskComplete() {
        while (!this.taskQueue.isEmpty()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void shutdown() {
        isStopped = true;
        for (PoolThreadRunnable p : threadRunnables) {
            p.doStop();
        }
    }

}
