package ryhor.mudrahel.textconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ryhor.mudrahel.textconverter.entity.HeavyWorker;
import ryhor.mudrahel.textconverter.entity.Message;
import ryhor.mudrahel.textconverter.service.ConvertingService;

@Controller
public class ConverterController {
    private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

    private final ConvertingService convertingService;
    private SimpMessagingTemplate messagingTemplate;

    private HeavyWorker worker;

    @Autowired
    public ConverterController(ConvertingService convertingService,SimpMessagingTemplate messagingTemplate) {
        this.convertingService = convertingService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/convert")
    public void convert(Message message) throws Exception {
        logger.info("got '{}' for conversion", message.getContent());
        char[] convertedChars = convertingService.convert(message.getContent());

        worker = new HeavyWorker(messagingTemplate,convertedChars);
        worker.start();

    }

    @MessageMapping("/cancelConversion")
    public void cancelConversion() {
        logger.info("In cancel conversion");
        worker.setContinueConversion(false);
        worker.interrupt();
    }

}