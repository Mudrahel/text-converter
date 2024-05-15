package ryhor.mudrahel.textconverter.entity;

public interface Worker {
    void process(String message);
    void cancelProcessing();
}
