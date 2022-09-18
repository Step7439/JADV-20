package synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    protected static final int AVTO = 10;
    protected static final List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Avto avto = new Avto();
        Buyer buyer = new Buyer();

        executorService.execute(avto);
        executorService.execute(buyer);

        executorService.shutdown();
    }
}
