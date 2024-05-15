package ryhor.mudrahel.textconverter.service.impl;

import org.junit.jupiter.api.Test;
import ryhor.mudrahel.textconverter.service.EncoderBase64;

import static org.junit.jupiter.api.Assertions.*;

class EncoderBase64Test {

    @Test
    void testEncode() {
        // Given
        EncoderBase64 encoder = new EncoderBase64();
        String input = "Hello, World!";
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";

        // When
        String actualOutput = encoder.encode(input);

        // Then
        assertEquals(expectedOutput, actualOutput);
    }
}