package lyhoangvinh.com.mymvp.manager;

import lyhoangvinh.com.mymvp.model.Address;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */

public class AddressManager {
    private static Address address;

    private AddressManager() {
    }

    public static Address getInstance() {
        if (address == null) {
            address = new Address();
        }
        return address;
    }
}
