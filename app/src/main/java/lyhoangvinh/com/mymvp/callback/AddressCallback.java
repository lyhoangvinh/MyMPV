package lyhoangvinh.com.mymvp.callback;

import java.util.List;

import lyhoangvinh.com.mymvp.model.object.Address;

/**
 * Created by ADMIN on 11/29/2017.
 */

public interface AddressCallback {
    void OnComplete(List<Address> list);
    void OnError(Exception ex);
    void OnNullAddress();
}
