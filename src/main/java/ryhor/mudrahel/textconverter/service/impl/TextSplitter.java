package ryhor.mudrahel.textconverter.service.impl;

import org.springframework.stereotype.Service;
import ryhor.mudrahel.textconverter.service.SplitterService;

@Service
public class TextSplitter implements SplitterService {
    @Override
    public char[] split(String text) {
        return text.toCharArray();
    }
}