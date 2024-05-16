package ryhor.mudrahel.textconverter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncoderBase64 implements EncoderService {
    private static final Logger logger = LoggerFactory.getLogger(EncoderBase64.class);

    @Override
    public String encode(String text) {
        String encodedMessage = Base64.getEncoder().encodeToString(text.getBytes());
        logger.info("Message '{}' was encoded to '{}'", text, encodedMessage);
        return encodedMessage;
    }
}