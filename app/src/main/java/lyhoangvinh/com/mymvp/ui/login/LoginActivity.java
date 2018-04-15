package lyhoangvinh.com.mymvp.ui.login;

import lyhoangvinh.com.mymvp.base.activity.BaseSingleFragmentActivity;


/**
 * Created by LyHoangVinh on 15/04/2018.
 */
public class LoginActivity extends BaseSingleFragmentActivity<LoginFragment> {

    @Override
    protected LoginFragment createFragment() {
        hideToolbar();
        return new LoginFragment();
    }
}
