package ryhor.mudrahel.textconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ryhor.mudrahel.textconverter.Message;
import ryhor.mudrahel.textconverter.service.EncoderService;

@RestController
@RequestMapping("/encode")
public class ConverterController {

    private final EncoderService encoderService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ConverterController(EncoderService encoderService) {
        this.encoderService = encoderService;
    }

    @PostMapping
    public String encode(@RequestParam String textToEncode) {
        return encoderService.encode(textToEncode);
    }

    @MessageMapping("/convert")
    public void convert(Message message) throws Exception {
        char[] chars = message.getContent().toCharArray();
        for (int i=0; i<chars.length;i++){
            //TODO uncomment later as original delay
            //			long delay = (long) (Math.random() * 5000) + 1000;
            long delay = (long)500;

            Thread.sleep(delay);
            messagingTemplate.convertAndSend("/topic/conversion",""+chars[i]);
        }

    }

//    @MessageMapping("/convert")
//    public void convert(HelloMessage message) throws Exception {
//        char[] chars = message.getName().toCharArray();
//        for (int i=0; i<chars.length;i++){
//            //TODO uncomment later as original delay
//            //			long delay = (long) (Math.random() * 5000) + 1000;
//            long delay = (long)500;
//
//            Thread.sleep(delay);
////            messagingTemplate.convertAndSend("/topic/conversion",
////                    new Greeting(HtmlUtils.htmlEscape(""+chars[i])));
//            messagingTemplate.convertAndSend("/topic/conversion",chars[i]);
//        }
//
//    }
}