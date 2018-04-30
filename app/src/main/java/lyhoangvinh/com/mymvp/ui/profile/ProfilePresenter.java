package lyhoangvinh.com.mymvp.ui.profile;

import android.content.Context;
import android.support.annotation.NonNull;

import lyhoangvinh.com.mymvp.constant.ConstantsApi;
import lyhoangvinh.com.mymvp.base.presenter.BasePresenter;
import lyhoangvinh.com.mymvp.base.request.BaseRequest;
import lyhoangvinh.com.mymvp.base.request.ProfileRequest;
import lyhoangvinh.com.mymvp.utils.crop.Functions;

/**
 * Created by LyHoangVinh on 16/04/2018.
 */
public class ProfilePresenter extends BasePresenter<ProfileView> implements IPresenterProfile {

    public ProfilePresenter(Context context, @NonNull ProfileView view) {
        super(context, view);
    }


    @Override
    public void getProfile() {
        addRequestRx(getRxService()
                .getAddress(new BaseRequest(Functions.getUser().getToken())), true, data -> {
            if (getView() != null && data != null) {
                getView().intData(data);
                Functions.saveUser(data.getUser());
            }
        });
    }

    @Override
    public void update(String firstName, String lastName, String email) {
        addRequestTest(getRxService().update(new ProfileRequest(email, firstName, lastName)), true, t -> {
            if (t.getStatus().equals(ConstantsApi.STATUS_SUCCESS)){
                getProfile();
            }
        });
    }

}
