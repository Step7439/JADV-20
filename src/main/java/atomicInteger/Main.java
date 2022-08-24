package atomicInteger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger count3 = new AtomicInteger();
    static AtomicInteger count4 = new AtomicInteger();
    static AtomicInteger count5 = new AtomicInteger();

    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        new Thread(() -> {
            for (String str3 : texts) {
                if (str3.length() == 3) {
                    if (str3.equals(new StringBuilder(str3).reverse().toString())) {
                        count3.incrementAndGet();
                    }
                    if (str3.charAt(0) == str3.charAt(1) && str3.charAt(1) == str3.charAt(2)) {
                        count3.incrementAndGet();
                    }
                }
            }
        }).start();
        new Thread(() -> {
            for (String str4 : texts) {
                if (str4.length() == 4) {
                    if (str4.equals(new StringBuilder(str4).reverse().toString())) {
                        count4.incrementAndGet();
                    }
                    if (str4.charAt(0) == str4.charAt(1) && str4.charAt(1) == str4.charAt(2)) {
                        count4.incrementAndGet();
                    }
                }
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            for (String str5 : texts) {
                if (str5.length() == 5) {
                    if (str5.equals(new StringBuilder(str5).reverse().toString())) {
                        count5.incrementAndGet();
                    }
                    if (str5.charAt(0) == str5.charAt(1) && str5.charAt(1) == str5.charAt(2)) {
                        count5.incrementAndGet();
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        System.out.println("Красивых слов с длиной 3: " + count3);
        System.out.println("Красивых слов с длиной 4: " + count4);
        System.out.println("Красивых слов с длиной 5: " + count5);
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
