package CSD207.Assignment4.Computer;

import CSD207.Assignment4.Storage.External;
import CSD207.Assignment4.Storage.Internal;

/**
 * Created by gautam on 05/10/17.
 */

public class Mobiles extends Computer implements Charger {

    int screenDensity;

    Internal internal;
    External external;

    public Mobiles() {
    }

    @Override
    public void charge() {
        System.out.println("charging mobile now");

    }
}
