package lyhoangvinh.com.mymvp.model.base.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import lyhoangvinh.com.mymvp.callback.AddressCallback;
import lyhoangvinh.com.mymvp.callback.TankRunnable;
import lyhoangvinh.com.mymvp.listener.OnResponseListener;
import lyhoangvinh.com.mymvp.model.base.view.BaseView;
import lyhoangvinh.com.mymvp.model.base.api.retrofit.ApiClient;
import lyhoangvinh.com.mymvp.model.base.api.retrofit.ApiService;
import lyhoangvinh.com.mymvp.model.base.api.volley.BaseApi;
import lyhoangvinh.com.mymvp.model.base.response.BaseResponse;
import lyhoangvinh.com.mymvp.model.object.Address;
import lyhoangvinh.com.mymvp.thread.BackgroundThreadExecutor;
import lyhoangvinh.com.mymvp.thread.UIThreadExecutor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */
public class BasePresenter<V extends BaseView> {

    @Nullable
    private V mView;

    protected Context context;

    public BasePresenter(Context context, @NonNull V view) {
        this.context = context;
        this.mView = view;
    }

    public V getView() {
        return mView;
    }

    public void bindView(V view) {
        this.mView = view;
    }

    public ApiService getApiService() {
        return ApiClient.getInstance().create(ApiService.class);
    }

    public BaseApi getBaseApiVolley(){
        return BaseApi.getInstance(context);
    }

    protected <T> void addRequest(boolean showLoading, Call<BaseResponse<T>> response, OnResponseListener<T> listener) {
        if (showLoading && getView() != null) {
            getView().showLoading();
        }

        response.enqueue(new Callback<BaseResponse<T>>() {
            @Override
            public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                if (showLoading && getView() !=null){
                    getView().hideLoading();
                }
                if (response.isSuccessful()){
                    listener.onRespond(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
                if (showLoading && getView() !=null){
                    getView().hideLoading();
                }
            }
        });
    }

    protected void addVolleyRequest(boolean showLoading, OnResponseListener<List<Address>> t) {
        if (showLoading && getView() != null) {
            getView().showLoading();
        }
        getBaseApiVolley().loadData(response -> {
            TankRunnable runnable = new TankRunnable(response, new AddressCallback() {
                @Override
                public void OnComplete(final List<Address> list) {
                    UIThreadExecutor.getInstance().runOnUIThread(() -> {
                        if (showLoading && getView() != null) {
                            getView().hideLoading();
                            t.onRespond(list);
                        }
                    });
                }

                @Override
                public void OnError(Exception ex) {
                    UIThreadExecutor.getInstance().runOnUIThread(() -> {
                        if (showLoading && getView() != null) {
                            getView().hideLoading();
                        }
                    });
                }

                @Override
                public void OnNullAddress() {
                    UIThreadExecutor.getInstance().runOnUIThread(() -> {
                        if (showLoading && getView() != null) {
                            getView().hideLoading();
                        }
                    });
                }
            });
            BackgroundThreadExecutor.getInstance().runOnBackground(runnable);
        }, error -> {
            if (showLoading && getView() != null) {
                getView().hideLoading();
            }
        });
    }
}
