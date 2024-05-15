package ryhor.mudrahel.textconverter.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ryhor.mudrahel.textconverter.service.ConvertingService;

@Component
public class WorkerImpl implements Worker {
    private static final Logger logger = LoggerFactory.getLogger(WorkerImpl.class);

    private HeavyWorker heavyWorker;
    private final ConvertingService convertingService;
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WorkerImpl(ConvertingService convertingService, SimpMessagingTemplate messagingTemplate) {
        this.convertingService = convertingService;
        this.messagingTemplate = messagingTemplate;
        this.heavyWorker = new HeavyWorker(messagingTemplate);
    }

    @Override
    public void process(String message) {
        if(Thread.State.TERMINATED == heavyWorker.getState()){
            logger.info("Worker already gone for the day, getting a new one");
            heavyWorker = new HeavyWorker(messagingTemplate);
        }
        if (Thread.State.NEW == heavyWorker.getState()) {
            char[] convertedChars = convertingService.convert(message);
            heavyWorker.setMessage(convertedChars);
            heavyWorker.start();
        } else if (Thread.State.RUNNABLE == heavyWorker.getState()
                || Thread.State.TIMED_WAITING == heavyWorker.getState()) {
            logger.info("Worker already busy and can't start another job");
        }
    }

    @Override
    public void cancelProcessing() {
        heavyWorker.setContinueConversion(false);
        heavyWorker.interrupt();
    }

}