package lyhoangvinh.com.mymvp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.model.base.fragment.BaseFragment;
import lyhoangvinh.com.mymvp.ui.address.AddressActivity;

/**
 * Created by LyHoangVinh on 15/04/2018.
 */
public class LoginFragment extends BaseFragment implements LoginView {


    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtPass)
    EditText edtPass;

    private LoginPresenter presenter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void bind() {
        presenter = new LoginPresenter(getActivity(), this);
    }

    public static LoginFragment createInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        if (email.length() == 0) {
            showError("Please Enter Email!");
        } else if (pass.length() == 0) {
            showError("Please Enter Password!");
        } else {
            presenter.userLoginRx(email, pass);
        }
    }

    @Override
    public void loginSuccess(String message) {
        if (getActivity() != null && message != null) {
            showToastOk(message);
            startActivity(new Intent(getActivity(), AddressActivity.class));
            getActivity().finish();
        }
    }

    @Override
    public void loginFaild(String message) {

    }
}
