package ryhor.mudrahel.textconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertingServiceImpl implements ConvertingService {

    private EncoderService encoderService;
    private TextSplitter textSplitter;

    @Autowired
    public ConvertingServiceImpl(TextSplitter textSplitter, EncoderService encoderService) {
        this.encoderService = encoderService;
        this.textSplitter = textSplitter;
    }

    @Override
    public char[] convert(String message) {
        return textSplitter.split(encoderService.encode(message));
    }
}