package lyhoangvinh.com.mymvp.ui.address;


import lyhoangvinh.com.mymvp.base.activity.BaseSingleFragmentActivity;

/**
 * Created by LyHoangVinh on 15/04/2018.
 */
public class AddressActivity extends BaseSingleFragmentActivity<AddressFragment> {
    @Override
    protected AddressFragment createFragment() {
        return new AddressFragment();
    }
}
