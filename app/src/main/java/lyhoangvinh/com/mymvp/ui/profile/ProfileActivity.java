package lyhoangvinh.com.mymvp.ui.profile;

import lyhoangvinh.com.mymvp.base.activity.BaseSingleFragmentActivity;

/**
 * Created by LyHoangVinh on 16/04/2018.
 */
public class ProfileActivity extends BaseSingleFragmentActivity<ProfileFragment>{
    @Override
    protected ProfileFragment createFragment() {
        return new ProfileFragment();
    }
}
