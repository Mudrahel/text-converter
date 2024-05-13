package ryhor.mudrahel.textconverter.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ryhor.mudrahel.textconverter.service.BusyService;

import java.util.concurrent.CompletableFuture;

@Service
public class BusyServiceImpl implements BusyService {

    @Async
    @Override
    public CompletableFuture<String> processStringWithDelay(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            try {
                // Generate a random delay between 1 to 5 seconds
                long delay = (long) (Math.random() * 5000) + 1000;
                Thread.sleep(delay);
                result.append(c);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted while sleeping", e);
            }
        }
        return CompletableFuture.completedFuture(result.toString());
    }
}
