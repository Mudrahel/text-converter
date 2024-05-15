package ryhor.mudrahel.textconverter.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ryhor.mudrahel.textconverter.service.ConvertingService;

@Component
public class WorkerImpl implements Worker {
    private static final Logger logger = LoggerFactory.getLogger(WorkerImpl.class);

    private HeavyWorker heavyWorker;
    private final ConvertingService convertingService;

    @Autowired
    public WorkerImpl(ConvertingService convertingService,HeavyWorker heavyWorker) {
        this.convertingService = convertingService;
        this.heavyWorker = heavyWorker;
    }

    @Override
    public void process(String message) {
        char[] convertedChars = convertingService.convert(message);

        heavyWorker.setMessage(convertedChars);
        heavyWorker.start();
    }

    @Override
    public void cancelProcessing() {
        heavyWorker.setContinueConversion(false);
        heavyWorker.interrupt();
    }

}