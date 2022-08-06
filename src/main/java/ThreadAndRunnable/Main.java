package ThreadAndRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        int t = 0;
        List<String> list = new ArrayList<>();
        List<Future<String>> listFuture = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        listFuture.add(executorService.submit(new LogicFuture()));
        listFuture.add(executorService.submit(new LogicFuture()));
        listFuture.add(executorService.submit(new LogicFuture()));
        listFuture.add(executorService.submit(new LogicFuture()));
        for (Future<String> f : listFuture) {
            try {
                list.add(f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }finally {
                executorService.shutdown();
            }
        }
    }
}
class LogicFuture implements Callable<String>{
    @Override
    public String call() throws Exception {

        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }
        long startTs = System.currentTimeMillis(); // start time
        for (String text : texts) {
            int maxSize = 0;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < text.length(); j++) {
                    if (i >= j) {
                        continue;
                    }
                    boolean bFound = false;
                    for (int k = i; k < j; k++) {
                        if (text.charAt(k) == 'b') {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound && maxSize < j - i) {
                        maxSize = j - i;
                    }
                }
            }
            System.out.println(text.substring(0, 100) + " -> " + maxSize);
        }

        long endTs = System.currentTimeMillis(); // end time
        System.out.println("Time: " + (endTs - startTs) + "ms");
        return null;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
