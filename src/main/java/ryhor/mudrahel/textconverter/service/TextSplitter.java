package ryhor.mudrahel.textconverter.service;

import org.springframework.stereotype.Service;

@Service
public class TextSplitter implements SplitterService {
    @Override
    public char[] split(String text) {
        return text.toCharArray();
    }
}