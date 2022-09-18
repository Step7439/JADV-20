package synchronization;

import static synchronization.Main.list;

public class Buyer implements Runnable {
    protected final int buyer = 10;
    protected final String separator = "=========================";

    public void run() {
        for (int i = 1; i < buyer; i++) {
            synchronized (list) {
                System.out.println("Покупатель № " + i + " зашел в автосалон");

                if (list.isEmpty()) {
                    System.out.println("Нет машины!");
                    try {
                        list.wait();
                    } catch (InterruptedException ignored) {
                    }
                } else {
                    System.out.println(list.remove(0));
                    System.out.println("Покупатель № " + i + " уехал на новеньком авто");
                    System.out.println(separator);
                }
            }
        }
    }
}

