package ryhor.mudrahel.textconverter.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class HeavyWorker extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(HeavyWorker.class);

    private static String conversionDestination="/topic/conversion";

    private char[] message;

    private boolean continueConversion = true;

    private SimpMessagingTemplate messagingTemplate;

    public HeavyWorker(){
    }

    public HeavyWorker(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void run() {
        logger.info("Worker work started");

        for (int i = 0; i < message.length; i++) {
            if (!continueConversion) {
                // Interrupt processing cycle
                break;
            }

            long delay = calculateDelay();

            try {
                logger.info("imitation of heavy work");
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                if (!continueConversion) {
                    // Interrupt cycle of processing only if it interruption caused by user
                    logger.info("worker processing interrupted by user");
                    break;
                }else{
                    logger.error("Worker was interrupted unexpectedly",e);
                }
            }
            logger.info("sending '{}' to outside",message[i]);
            messagingTemplate.convertAndSend(conversionDestination, "" + message[i]);
        }
        logger.info("Worker work ended");
    }

    private long calculateDelay(){
        return (long) (Math.random() * 5000) + 1000;
    }

    public void setContinueConversion(boolean continueConversion) {
        this.continueConversion = continueConversion;
    }

    public void setMessage(char[] message) {
        this.message = message;
    }
}