package lyhoangvinh.com.mymvp.base.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import lyhoangvinh.com.mymvp.base.api.github.GithubClient;
import lyhoangvinh.com.mymvp.base.api.github.GithubService;
import lyhoangvinh.com.mymvp.base.api.retrofit.ApiClient;
import lyhoangvinh.com.mymvp.base.api.retrofit.ApiService;
import lyhoangvinh.com.mymvp.base.api.rx.RxClient;
import lyhoangvinh.com.mymvp.base.api.rx.RxService;
import lyhoangvinh.com.mymvp.base.api.utils.ApiUtils;
import lyhoangvinh.com.mymvp.base.api.utils.GithubApiUtils;
import lyhoangvinh.com.mymvp.base.api.volley.BaseApi;
import lyhoangvinh.com.mymvp.base.response.BaseResponse;
import lyhoangvinh.com.mymvp.base.response.ResponseTest;
import lyhoangvinh.com.mymvp.base.view.BaseView;
import lyhoangvinh.com.mymvp.base.view.ErrorEntity;
import lyhoangvinh.com.mymvp.callback.AddressCallback;
import lyhoangvinh.com.mymvp.callback.TankRunnable;
import lyhoangvinh.com.mymvp.interfaces.Lifecycle;
import lyhoangvinh.com.mymvp.listener.OnResponseListener;
import lyhoangvinh.com.mymvp.listener.OnResponseListenerTest;
import lyhoangvinh.com.mymvp.listener.PlainConsumer;
import lyhoangvinh.com.mymvp.model.Address;
import lyhoangvinh.com.mymvp.thread.BackgroundThreadExecutor;
import lyhoangvinh.com.mymvp.thread.UIThreadExecutor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */
public class BasePresenter<V extends BaseView> implements Lifecycle{

    @Nullable
    private V mView;

    protected Context context;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(Context context, @NonNull V view) {
        this.context = context;
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    public V getView() {
        return mView;
    }

    public void bindView(V view) {
        this.mView = view;
    }

    protected ApiService getApiService() {
        return ApiClient.getInstance().create(ApiService.class);
    }

    protected GithubService getGitHubService(){
        return GithubClient.getInstance().create(GithubService.class);
    }

    private BaseApi getBaseApiVolley() {
        return BaseApi.getInstance(context);
    }

    protected RxService getRxService() {
        return RxClient.getInstance().create(RxService.class);
    }

    //----------------------------------Githus----------------------------------------------------
    protected <T> void addRequest(
            Single<Response<T>> request, boolean showProgress,
            boolean forceResponseWithoutCheckNullView,
            @Nullable OnResponseListener<T> responseConsumer,
            @Nullable OnResponseListener<ErrorEntity> errorConsumer) {

        boolean shouldUpdateUI = showProgress || responseConsumer != null || errorConsumer != null;

        if (showProgress && getView() != null) {
            getView().showLoading();
        }

        Disposable disposable = GithubApiUtils.makeRequest(request, shouldUpdateUI, response -> {
            if (responseConsumer != null && (forceResponseWithoutCheckNullView || mView != null)) {
                responseConsumer.onRespond(response);
            }
        }, error -> {
            if (errorConsumer != null) {
                errorConsumer.onRespond(error);
            } else if (mView != null) {
                getView().onError(error);
            }
        }, () -> {
            // complete
            if (showProgress && getView() != null) {
                getView().hideLoading();
            }
        });

        if (mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    /**
     * Add a request with success listener
     */
    protected <T> void addRequest(boolean showProgress,Single<Response<T>> request,
                                  @Nullable OnResponseListener<T> responseConsumer) {
        addRequest(request, showProgress, false, responseConsumer, null);
    }
    //----------------------------------End-Githus----------------------------------------------------


    //----------------------------------Retrofit----------------------------------------------------

    protected <T> void addRequest(boolean showLoading, Call<BaseResponse<T>> response, OnResponseListener<T> listener) {
        if (showLoading && getView() != null) {
            getView().showLoading();
        }

        response.enqueue(new Callback<BaseResponse<T>>() {
            @Override
            public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                if (showLoading && getView() != null) {
                    getView().hideLoading();
                }
                if (response.isSuccessful()) {
                    listener.onRespond(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
                if (showLoading && getView() != null) {
                    getView().hideLoading();
                }
            }
        });
    }
    //----------------------------------End-Retrofit----------------------------------------------------

    //----------------------------------rx2---------------------------------------------------------
    private <T> void addRequestRx(
            Single<BaseResponse<T>> request, boolean showProgress,
            boolean forceResponseWithoutCheckNullView,
            @Nullable OnResponseListener<T> listener,
            @Nullable OnResponseListener<ErrorEntity> errorConsumer) {

        boolean shouldUpdateUI = showProgress || listener != null;

        if (showProgress && getView() != null) {
            getView().showLoading();
        }

        Disposable disposable = ApiUtils.makeRequest(request, shouldUpdateUI, response -> {
            if (listener != null && (forceResponseWithoutCheckNullView || getView() != null)) {
                listener.onRespond(response);
                if (showProgress) {
                    getView().hideLoading();
                }
            }
        }, error -> {
            if (getView() != null) {
                if (errorConsumer != null) {
                    errorConsumer.onRespond(error);
                    getView().hideLoading();
                } else {
                    getView().hideLoading();
                    getView().showError(error.getMessage());
                }
            }
        });

        if (mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * Add a request with success listener
     */
    protected <T> void addRequestRx(Single<BaseResponse<T>> request, boolean showProgress,
                                    @Nullable OnResponseListener<T> responseConsumer) {
        addRequestRx(request, showProgress, false, responseConsumer, null);
    }

    //----------------------------------End-rx2---------------------------------------------------------

    //----------------------------------Text--------------------------------------------------------

    protected void addRequestTest(Single<ResponseTest> request, boolean showProgress,
                                  boolean forceResponseWithoutCheckNullView,
                                  @NonNull OnResponseListenerTest listener,
                                  @Nullable OnResponseListener<ErrorEntity> errorConsumer) {
        boolean shouldUpdateUI = showProgress || listener != null;

        if (showProgress && getView() != null) {
            getView().showLoading();
        }

        Disposable disposable = ApiUtils.makeRequestTest(request, shouldUpdateUI, response -> {
            if (listener != null && (forceResponseWithoutCheckNullView || getView() != null)) {
                listener.onRespond(response);
                if (showProgress) {
                    getView().hideLoading();
                }
            }
        }, error -> {
            if (getView() != null) {
                if (errorConsumer != null) {
                    errorConsumer.onRespond(error);
                    getView().hideLoading();
                } else {
                    getView().hideLoading();
                    getView().showError(error.getMessage());
                }
            }
        });

        if (mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);

    }




    protected void addRequestTest(Single<ResponseTest> request, boolean showProgress,
                                  OnResponseListenerTest listener) {
        addRequestTest(request, showProgress, false, listener, null);
    }

    //----------------------------------End-Text--------------------------------------------------------

    //----------------------------------Volley----------------------------------------------------------
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

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.dispose();
    }
    //----------------------------------End-Volley----------------------------------------------------------

    //----------------------------------Zip()----------------------------------------------------------

    protected <T1, T2> void addRequestZip(
            Single<Response<T1>> request1,
            Single<Response<T2>> request2,
            boolean showProgress,
            boolean forceResponseWithoutCheckNullView,
            @Nullable PlainConsumer<T1, T2> responseConsumer,
            @Nullable OnResponseListener<ErrorEntity> errorConsumer) {
        boolean shouldUpdateUI = showProgress || responseConsumer != null || errorConsumer != null;
        if (showProgress && getView() != null) {
            getView().showLoading();
        }

        Disposable disposable = ApiUtils.makeRequestZip(request1, request2, shouldUpdateUI, var1 -> {
            if (responseConsumer != null && (forceResponseWithoutCheckNullView || getView() != null)) {
                responseConsumer.accept(var1);
            }
        }, error -> {
            if (errorConsumer != null) {
                errorConsumer.onRespond(error);
            } else if (getView() != null) {
                getView().onError(error);
            }
        }, () -> {
            // complete
            if (showProgress && getView() != null) {
                getView().hideLoading();
            }
        });

        if (mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected <T1, T2> void addRequestZip(boolean showProgress, Single<Response<T1>> request1,
                                          Single<Response<T2>> request2, @Nullable PlainConsumer<T1, T2> responseConsumer) {
        addRequestZip(request1, request2, showProgress, false, responseConsumer, null);
    }

}
