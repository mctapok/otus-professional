package ru.gavrilov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gavrilov.threadpool.MyThreadPool;

public class Main {
    private static final Logger logger = LogManager.getLogger("logger");

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(3);


        for (int i = 0; i < 10; i++) {
            int runnableTaskNumber = i + 1;
            myThreadPool.execute(() -> {
                String message = Thread.currentThread().getName() + ": task #" + runnableTaskNumber;
                logger.info("log thread work: {}", message);
            });
        }

        myThreadPool.waitUntilAllTaskComplete();

        myThreadPool.shutdown();
    }
}
