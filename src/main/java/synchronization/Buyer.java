package synchronization;

import static synchronization.Main.list;

public class Buyer {
    protected final int userBuyer = 8;
    protected final String separator = "=========================";

    public void buyer() {
        new Thread(() -> {
            for (int i = 1; i < userBuyer + 1; i++) {
                synchronized (list) {
                    System.out.println("Покупатель № " + i + " зашел в автосалон");

                    if (list.isEmpty()) {
                        System.out.println("Нет машины!");

                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(list.remove(0));
                    System.out.println("Покупатель № " + i + " уехал на новеньком авто");
                    System.out.println(separator);
                }
            }
        }).start();
    }
}

