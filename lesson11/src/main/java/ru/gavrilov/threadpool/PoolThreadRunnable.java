package ru.gavrilov.threadpool;
import java.util.List;

public class PoolThreadRunnable implements Runnable {
    Thread thread = null;
    List<Runnable> tasks;
    private boolean isStopped = false;

    public PoolThreadRunnable(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped) {
            Runnable runnable;
            synchronized (tasks) {
                while (tasks.isEmpty()) {
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
                        thread.interrupt();
                        return;
                    }
                }
                runnable = tasks.remove(0);
            }
            try {
                runnable.run();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    public void doStop() {
        isStopped = true;
        thread.interrupt();
    }
}
