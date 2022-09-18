package synchronization;

import static synchronization.Main.list;

public class Avto implements Runnable{
    protected final int avto = 10;

    public void run() {
        for (int i = 1; i < avto; i++) {
            synchronized (list) {

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                list.add("Производитель Toyota выпустил 1 авто");
                list.notify();
            }
        }
    }
}