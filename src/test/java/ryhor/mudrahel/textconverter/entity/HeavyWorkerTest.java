package ryhor.mudrahel.textconverter.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeavyWorkerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private HeavyWorker heavyWorker;

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
    public void testSentNotificationUponCompletion() throws InterruptedException {
        // Prepare test data
        char[] message = new char[0];
        heavyWorker.setMessage(message);

        // Call the method under test
        heavyWorker.run();

        // Verify that convertAndSend is called for each character in the message (though doesn't verify order of chars)
        verify(messagingTemplate, times(1)).convertAndSend("/topic/conversion-complete", "done");
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
        verify(messagingTemplate, never()).convertAndSend(eq("/topic/conversion"), anyString());

    }

}