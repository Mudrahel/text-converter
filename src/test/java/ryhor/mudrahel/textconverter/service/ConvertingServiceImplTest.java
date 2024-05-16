package ryhor.mudrahel.textconverter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConvertingServiceImplTest {

    @Mock
    private TextSplitter textSplitter;

    @Mock
    private EncoderService encoderService;

    @InjectMocks
    private ConvertingServiceImpl convertingService;

    @Test
    public void testTextConvertedAndSplitCorrectly() {
        // Prepare test data
        String inputMessage = "Hello, World!";
        String encodedMessage = "SGVsbG8sIFdvcmxkIQ==";
        char[] splitMessage = {
                'S', 'G', 'V', 's', 'b', 'G', '8', 's', 'I', 'F', 'd', 'v', 'c', 'm', 'x', 'k', 'I', 'Q', '=', '='};

        // Stub the behavior of encoderService
        when(encoderService.encode(inputMessage)).thenReturn(encodedMessage);

        // Stub the behavior of textSplitter
        when(textSplitter.split(encodedMessage)).thenReturn(splitMessage);

        // Call the method under test
        char[] result = convertingService.convert(inputMessage);

        // Verify that encoderService.encode() is called with the input message
        verify(encoderService).encode(inputMessage);

        // Verify that textSplitter.split() is called with the encoded message
        verify(textSplitter).split(encodedMessage);

        // Assert the result
        assertArrayEquals(splitMessage, result);
    }
}