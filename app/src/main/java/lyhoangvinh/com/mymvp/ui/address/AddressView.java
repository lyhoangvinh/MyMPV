package lyhoangvinh.com.mymvp.ui.address;

import java.util.List;

import lyhoangvinh.com.mymvp.model.base.view.BaseView;
import lyhoangvinh.com.mymvp.model.object.Address;

/**
 * Created by LyHoangVinh on 22/03/2018.
 */

public interface AddressView extends BaseView {
    void loadAddressManager(List<Address> list);
}
