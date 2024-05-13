package ryhor.mudrahel.textconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ryhor.mudrahel.textconverter.service.EncoderService;

@RestController
@RequestMapping("/encode")
public class ConverterController {

    private final EncoderService encoderService;

    @Autowired
    public ConverterController(EncoderService encoderService) {
        this.encoderService = encoderService;
    }

    @PostMapping
    public String encode(@RequestParam String textToEncode) {
        return encoderService.encode(textToEncode);
    }

}