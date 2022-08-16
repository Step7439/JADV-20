package MyQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static int CALL = 60;
    public static void main(String[] args) {
        Queue<Integer> ATC = new ConcurrentLinkedQueue<>();


        new Thread(() -> {
            for (int i = 0; i < CALL; i++){
                ATC.add(i);
                System.out.println("Поступил звонок !" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < CALL; i++) {
                System.out.println("Оператор 1 обработал звонок !" + " " + i);
                ATC.poll();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ).start();

        new Thread(() -> {
            for (int i = 0; i < CALL; i++) {
                System.out.println("Оператор 2 обработал звонок !" + " " + i);
                ATC.poll();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ).start();
    }
}
