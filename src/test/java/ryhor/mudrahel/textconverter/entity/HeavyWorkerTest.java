package ryhor.mudrahel.textconverter.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

public class HeavyWorkerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private HeavyWorker heavyWorker;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCharArraySentAsExpected() throws InterruptedException {
        // Prepare test data
        char[] message = {'a', 'b', 'c'};
        heavyWorker.setMessage(message);

        // Call the method under test
        heavyWorker.run();

        // Verify that convertAndSend is called for each character in the message (though doesn't verify order of chars)
        verify(messagingTemplate, times(1)).convertAndSend("/topic/conversion", "a");
        verify(messagingTemplate, times(1)).convertAndSend("/topic/conversion", "b");
        verify(messagingTemplate, times(1)).convertAndSend("/topic/conversion", "c");
    }

    @Test
    public void testNoMessageSentWithFlagOff() throws InterruptedException {
        // Prepare test data
        char[] message = {'a', 'b', 'c'};
        heavyWorker.setMessage(message);
        heavyWorker.setContinueConversion(false);
        // Call the method under test
        heavyWorker.run();

        // Verify no characters sent, as conversion flag set to false
        verify(messagingTemplate, never()).convertAndSend(anyString(), anyString());

    }

}