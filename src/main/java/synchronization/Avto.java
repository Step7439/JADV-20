package synchronization;

import static synchronization.Main.list;

public class Avto {
    protected final int AVTO = 10;
    protected final int avt = 1;

    public void avto() {
        new Thread(() -> {
            for (int i = 1; i < AVTO + 1; i++) {
                synchronized (list) {
                    list.add("Производитель Toyota выпустил " + avt + " авто");
                    list.notify();
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }
}