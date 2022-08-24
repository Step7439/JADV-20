package synchronization;

import java.util.ArrayList;
import java.util.List;
public class Main {
    protected static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Buyer buyers = new Buyer();
        Avto avtos = new Avto();

        buyers.buyer();
        avtos.avto();
    }

}
