package CSD207.Assignment4.Computer;

import CSD207.Assignment4.Storage.External;
import CSD207.Assignment4.Storage.Internal;
import CSD207.Assignment4.Storage.Removable;

/**
 * Created by gautam on 05/10/17.
 */

public class Laptop extends Computer implements Charger {

    int screenDensity;
    double screenSize;  // in inches

    Internal internal;  // make this an array
    External external;
    Removable removable;

    public Laptop() {

    }

    @Override
    public void charge() {
        System.out.println("charging laptop now");

    }
}

