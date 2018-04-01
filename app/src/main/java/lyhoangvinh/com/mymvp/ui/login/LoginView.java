package lyhoangvinh.com.mymvp.ui.login;

import lyhoangvinh.com.mymvp.model.base.view.BaseView;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
public interface LoginView extends BaseView {
    void loginSuccess(String message);
    void loginFaild(String message);
}
