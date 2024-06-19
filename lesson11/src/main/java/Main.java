public class Main {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int runnableTaskNumber = i + 1;
            myThreadPool.execute(() -> {
                String message = Thread.currentThread().getName() + ": task #" + runnableTaskNumber;
                System.out.println(message);
            });
        }

        myThreadPool.waitUntilAllTaskComplete();

        myThreadPool.shutdown();
    }
}
