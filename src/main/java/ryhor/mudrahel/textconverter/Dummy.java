package ryhor.mudrahel.textconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ryhor.mudrahel.textconverter.controller.ConverterController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Dummy {
    private static final Logger logger = LoggerFactory.getLogger(Dummy.class);

    public static void main(String[] args) {

        MyThread thread = new MyThread();
        thread.start(); // Start the thread

        // Sleep for 2 seconds
        try {
            logger.info("tt");
            Thread.sleep(2000);
            logger.info("ttt");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt the sleep of the thread
        thread.interrupt();
    }


}

class MyThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(MyThread.class);

    @Override
    public void run() {
        try {
            logger.info("Thread started");
            Thread.sleep(5000); // Put the thread to sleep for 5 seconds
            logger.info("Thread woke up");
        } catch (InterruptedException e) {
            logger.info("Thread interrupted while sleeping");
            // Handle the interruption
            return; // Exit the thread
        }
    }
}