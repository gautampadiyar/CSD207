package CSD207.Assignment4.Computer;

import CSD207.Assignment4.Storage.External;
import CSD207.Assignment4.Storage.Internal;
import CSD207.Assignment4.Storage.Removable;

/**
 * Created by gautam on 05/10/17.
 */

public class Desktop extends Computer implements Charger {

    String Monitor;
    int PSU_Rating;

    Internal internal;  // make this an array
    Removable removable;
    External external;



    public Desktop() {

    }

    @Override
    public void charge() {

        System.out.println("Not charging desktop");
        System.out.println("PSU rating is " + PSU_Rating + "Watts");

    }
}
