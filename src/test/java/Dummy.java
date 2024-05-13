import ryhor.mudrahel.textconverter.service.BusyService;
import ryhor.mudrahel.textconverter.service.impl.BusyServiceImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Dummy {
    public static void main(String[] args) {
        BusyService characterDelayService = new BusyServiceImpl();

        // Input string
        String input = "abcd";

        // Call the service method
        CompletableFuture<String> resultFuture = characterDelayService.processStringWithDelay(input);

        // Observe the result character by character
        resultFuture.thenAccept(result -> {
            System.out.println("Result: " + result);
        });

        // Wait for the result (You can do other tasks here while waiting)
        try {
            resultFuture.get(); // This will block until the result is available
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
