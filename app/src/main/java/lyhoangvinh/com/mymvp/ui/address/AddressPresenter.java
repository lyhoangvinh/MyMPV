package lyhoangvinh.com.mymvp.ui.address;

import android.content.Context;
import android.support.annotation.NonNull;

import lyhoangvinh.com.mymvp.Constant.ConstantsApi;
import lyhoangvinh.com.mymvp.model.base.presenter.BasePresenter;
import lyhoangvinh.com.mymvp.model.base.request.BaseRequest;
import lyhoangvinh.com.mymvp.model.base.response.BaseResponse;
import lyhoangvinh.com.mymvp.model.object.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LyHoangVinh on 22/03/2018.
 */

public class AddressPresenter extends BasePresenter<AddressView> implements IPresenterAdderss {

    public AddressPresenter(Context context, @NonNull AddressView view) {
        super(context, view);
    }

    @Override
    public void loadDataVolley() {
        addVolleyRequest(true, addresses -> {
            if (getView() != null && addresses != null) {
                getView().loadAddressManager(addresses);
            }
        });
    }

    @Override
    public void loadDataRetrofit() {
        showLoading();
        BaseRequest request = new BaseRequest(ConstantsApi.TOKEN);
        getApiService().getAddressTest(request).enqueue(new Callback<BaseResponse<Data>>() {
            @Override
            public void onResponse(Call<BaseResponse<Data>> call, Response<BaseResponse<Data>> response) {
                if (getView() != null && response != null) {
                    getView().loadAddressManager(response.body().getData().getAddress());
                    hideLoading();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Data>> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    public void loadDataTest() {
        addRequest(true, getApiService().getAddressTest(new BaseRequest(ConstantsApi.TOKEN)), data -> {
            if (getView() != null && data != null) {
                getView().loadAddressManager(data.getAddress());
            }
        });
    }

    @Override
    public void loadDataRx() {
        addRequestRx(getRxService().getAddress(new BaseRequest(ConstantsApi.TOKEN)), true, data -> {
            if (getView() != null && data != null){
                getView().loadAddressManager(data.getAddress());
            }
        });
    }

    private void showLoading() {
        if (getView() != null) {
            getView().showLoading();
        }
    }

    private void hideLoading() {
        if (getView() != null) {
            getView().hideLoading();
        }
    }
}
