package ryhor.mudrahel.textconverter.service;

import org.junit.jupiter.api.Test;
import ryhor.mudrahel.textconverter.service.TextSplitter;

import static org.junit.jupiter.api.Assertions.*;

class TextSplitterTest {

    @Test
    void testSplit() {
        // Given
        TextSplitter splitter = new TextSplitter();
        String inputString = "SGVsbG8sIFdvcmxkIQ==";
        char[] expectedCharacters = {
                'S', 'G', 'V', 's', 'b', 'G', '8', 's', 'I', 'F', 'd', 'v', 'c', 'm', 'x', 'k', 'I', 'Q', '=', '='};
        // When
        char[] actualCharacters = splitter.split(inputString);

        // Then
        assertArrayEquals(expectedCharacters, actualCharacters);
    }

}