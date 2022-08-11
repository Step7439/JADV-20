package Synchronization;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (list) {
                    list.add("Производитель Toyota выпустил 1 авто");
                    list.notify();
                }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (list) {
                    System.out.println("Покупатель" + i + " зашел в автосалон");

                    if (list.isEmpty()) {
                        System.out.println("Нет машины!");

                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(list.remove(0));
                    System.out.println("Покупатель" + i + " уехал на новеньком авто");
                }
            }
        }).start();
    }
}
