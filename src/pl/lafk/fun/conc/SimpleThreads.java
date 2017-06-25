package pl.lafk.fun.conc;

/**
 * Demonstrates join, interrupts, sleeping; all in 2 threads.
 * @author Oracle Tutorials authors
 * @see <A href="http://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html">Oracle Concurrency: Simple Threads exercise</A>
 */
public class SimpleThreads {

    void threadMessage(String message) {
        String name = Thread.currentThread().getName();
        System.out.format("%s: %s%n", name, message);
    }


    public static void main(String[] args) throws InterruptedException {
        long patience = 10000;
        SimpleThreads simpleThread = new SimpleThreads();
        simpleThread.threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(() -> {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };
            try {
                for (int i = 0;i < importantInfo.length;i++) {
                    Thread.sleep(4000);
                    simpleThread.threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                simpleThread.threadMessage("I wasn't done!");
            }
        });
        t.start();

        simpleThread.threadMessage("message loop ongoing, waiting for it's finish");
        while(t.isAlive()) {
            simpleThread.threadMessage("still waiting...");
            t.join(1000);
            if(System.currentTimeMillis() - startTime > patience && t.isAlive()) {
                simpleThread.threadMessage("enough waiting!");
                t.interrupt();
                t.join();
            }
        }
        simpleThread.threadMessage("end?");
    }
}
