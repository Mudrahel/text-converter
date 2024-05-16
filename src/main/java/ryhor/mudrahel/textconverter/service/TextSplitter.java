package ryhor.mudrahel.textconverter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TextSplitter implements SplitterService {
    private static final Logger logger = LoggerFactory.getLogger(TextSplitter.class);

    @Override
    public char[] split(String text) {
        //super fancy logic of splitting String into chars
        char[] result = text.toCharArray();

        logger.info("Message '{}' was split into '{}'",text, Arrays.toString(result));
        return result;
    }
}