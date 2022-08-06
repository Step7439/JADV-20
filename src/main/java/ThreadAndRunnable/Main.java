package ThreadAndRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
int y;
        String[] texts = new String[25];
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }
        long startTs = System.currentTimeMillis(); // start time
        for (String text : texts) {
            Runnable logic = () -> {
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
            };
            Thread threads = new Thread(logic);
            list.add(threads);
            threads.start();
        }
            for (Thread thread : list) {
                thread.join(); // зависаем, ждём когда поток объект которого лежит в thread завершится
            }
            long endTs = System.currentTimeMillis(); // end time
            System.out.println("Time: " + (endTs - startTs) + "ms");
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
