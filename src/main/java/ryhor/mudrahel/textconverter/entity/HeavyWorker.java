package ryhor.mudrahel.textconverter.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class HeavyWorker extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(HeavyWorker.class);

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
            //TODO uncomment later as original delay
//            long delay = (long) (Math.random() * 5000) + 1000;
            long delay = (long) 500;

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
            messagingTemplate.convertAndSend("/topic/conversion", "" + message[i]);
        }
    }

    public void setContinueConversion(boolean continueConversion) {
        this.continueConversion = continueConversion;
    }

    public void setMessage(char[] message) {
        this.message = message;
    }
}