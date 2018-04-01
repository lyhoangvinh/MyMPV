package lyhoangvinh.com.mymvp.ui.login;

import android.content.Intent;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.model.base.activity.BaseActivity;
import lyhoangvinh.com.mymvp.ui.address.AddressActivity;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtPass)
    EditText edtPass;

    private LoginPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void bind() {
        presenter = new LoginPresenter(this, this);
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
        if (message != null) {
            showToastOk(message);
            startActivity(new Intent(LoginActivity.this, AddressActivity.class));
            finish();
        }
    }

    @Override
    public void loginFaild(String message) {

    }
}
