package CSD207.Assignment4.Storage;

/**
 * Created by gautam on 05/10/17.
 */

public abstract class Storage {

    // boolean present
    int capacity; // in GB

    abstract void persistent_save();
    abstract void capacity();

}
