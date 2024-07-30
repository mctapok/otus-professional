import java.util.LinkedList;

public class PoolThreadRunnable implements Runnable {
    Thread thread = null;
    LinkedList<Runnable> tasks;
    private boolean isStopped = false;

    public PoolThreadRunnable(LinkedList<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while(!isStopped){
            Runnable runnable;
            synchronized (tasks){
                while(tasks.isEmpty()){
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        thread.interrupt();
                        return;
                    }
                }
               runnable = tasks.poll();
            }
            try {
                runnable.run();
            }catch (RuntimeException e){
                e.printStackTrace();
            }
        }
    }

    public void doStop(){
        isStopped = true;
        thread.interrupt();
    }
}
