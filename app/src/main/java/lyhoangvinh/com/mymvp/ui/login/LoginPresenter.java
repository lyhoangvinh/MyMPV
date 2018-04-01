package lyhoangvinh.com.mymvp.ui.login;

import android.content.Context;
import android.support.annotation.NonNull;

import lyhoangvinh.com.mymvp.model.base.presenter.BasePresenter;
import lyhoangvinh.com.mymvp.model.base.request.LoginRequest;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
public class LoginPresenter extends BasePresenter<LoginView> implements IPresenterLogin {

    public LoginPresenter(Context context, @NonNull LoginView view) {
        super(context, view);
    }

    @Override
    public void userLoginRx(String email, String password) {
        addRequestRx(getRxService().login(new LoginRequest(email, password)), true, user -> {
            if (getView() != null && user != null) {
                getView().loginSuccess(user.getFullName());
            }
        });
    }

    @Override
    public void userLoginTest(String email, String password) {
        addRequestTest(getRxService().loginTest(new LoginRequest(email, password)), true, t -> {
            if (getView() != null && t.getStatus() != null) {
                getView().loginSuccess(t.getStatus());
            }
        });
    }
}
