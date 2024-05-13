package ryhor.mudrahel.textconverter.service;

import java.util.concurrent.CompletableFuture;

public interface BusyService {
    CompletableFuture<String> processStringWithDelay(String input);
}
