package ryhor.mudrahel.textconverter.service.impl;

import org.springframework.stereotype.Service;
import ryhor.mudrahel.textconverter.service.EncoderService;

import java.util.Base64;

@Service
public class EncoderBase64 implements EncoderService {
    @Override
    public String encode(String text) {
        // Encode the input string to Base64
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}