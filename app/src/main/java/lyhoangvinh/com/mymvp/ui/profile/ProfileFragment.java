package lyhoangvinh.com.mymvp.ui.profile;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.base.fragment.BaseFragment;
import lyhoangvinh.com.mymvp.model.Data;
import lyhoangvinh.com.mymvp.model.User;
import lyhoangvinh.com.mymvp.utils.crop.Functions;

/**
 * Created by LyHoangVinh on 16/04/2018.
 */
public class ProfileFragment extends BaseFragment implements ProfileView {

    @BindView(R.id.imvAvatarRequesterAct)
    @Nullable
    ImageView imvAvatarRequesterAct;

    @BindView(R.id.edtFirstNameAct)
    @Nullable
    EditText edtFirstName;

    @BindView(R.id.edtLastNameAct)
    @Nullable
    EditText edtLastName;

    @BindView(R.id.edtEmailAct)
    @Nullable
    EditText edtEmail;

    private ProfilePresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void bind(View view) {
        presenter = new ProfilePresenter(getActivity(), this);
        inidDataLocal(Functions.getUser());
    }

    @OnClick(R.id.btnSubmit)
    @Optional
    public void update(){
        String email = edtEmail.getText().toString().trim();
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        if (email.length() == 0){
            showError("Email is not empty");
        }else if (firstName.trim().length() == 0){
            showError("First name is not empty");
        }else if (lastName.trim().length() == 0){
            showError("Last name is not empty");
        }else {
            presenter.update(firstName, lastName, email);
        }

    }

    private void inidDataLocal(User user){
        if (user!=null){
            edtEmail.setText(user.getEmail());
            edtLastName.setText(user.getLastname());
            edtFirstName.setText(user.getFirstname());
        }
    }

    @Override
    public void intData(Data data) {
        if (data != null && data.getUser() != null) {
            edtEmail.setText(data.getUser().getEmail());
            edtLastName.setText(data.getUser().getLastname());
            edtFirstName.setText(data.getUser().getFirstname());
        }
    }
}
