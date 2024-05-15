package ryhor.mudrahel.textconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ryhor.mudrahel.textconverter.entity.Message;
import ryhor.mudrahel.textconverter.entity.Worker;

@Controller
public class ConverterController {
    private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

    private Worker worker;

    @Autowired
    public ConverterController(Worker worker) {
        this.worker = worker;
    }

    @MessageMapping("/convert")
    public void convert(Message message) throws Exception {
        logger.info("got '{}' for processing", message.getContent());
        worker.process(message.getContent());

    }

    @MessageMapping("/cancelConversion")
    public void cancelConversion() {
        logger.info("got request to cancel processing");
        worker.cancelProcessing();
    }

}