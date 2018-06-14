package lyhoangvinh.com.mymvp.base.api.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import lyhoangvinh.com.mymvp.base.response.BaseEntity;
import lyhoangvinh.com.mymvp.base.response.BaseResponse;
import lyhoangvinh.com.mymvp.base.response.ResponseTest;
import lyhoangvinh.com.mymvp.base.response.ResponseZip;
import lyhoangvinh.com.mymvp.base.view.ErrorEntity;
import lyhoangvinh.com.mymvp.constant.ConstantsApi;
import lyhoangvinh.com.mymvp.listener.OnResponseListener;
import lyhoangvinh.com.mymvp.listener.OnResponseListenerTest;
import lyhoangvinh.com.mymvp.listener.PlainConsumer;
import retrofit2.Response;

/**
 * Created by lyhoangvinh on 9/10/17.
 */

public final class ApiUtils {

    /**
     * Create new retrofit api request
     *
     * @param request          observable request
     * @param shouldUpdateUi   true if should update UI after response returned
     * @param responseConsumer consume response data
     * @param errorConsumer    consume errors
     * @param <T>              Type of response body
     */
    public static <T> Disposable makeRequest(
            Single<BaseResponse<T>> request, boolean shouldUpdateUi,
            @NonNull OnResponseListener<T> responseConsumer,
            @Nullable OnResponseListener<ErrorEntity> errorConsumer) {

        Single<BaseResponse<T>> single = request.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (shouldUpdateUi) {
            single = single.observeOn(AndroidSchedulers.mainThread());
        }

        return single.subscribe(response -> {
            if (response.isSuccess()) {
                responseConsumer.onRespond(response.getData());
            } else if (errorConsumer != null) {
                T data = response.getData();
                String message = ErrorEntity.OOPS;
                if (data instanceof BaseEntity) {
                    message = ((BaseEntity) data).getMessage();
                } else if (data instanceof String) {
                    message = (String) data;
                }else if(!android.text.TextUtils.isEmpty(response.getMessage())){
                    message = response.getMessage();
                }
                errorConsumer.onRespond(ErrorEntity.getError(message));
            }
        }, throwable -> {
//            if (throwable instanceof RuntimeException) {
//                // must be fixed while developing
//                throw new Exception(throwable);
//            }
            // handle error
            throwable.printStackTrace();
            if (errorConsumer != null) {
                errorConsumer.onRespond(ErrorEntity.getError(throwable));
            }
        });
    }

    public static Disposable makeRequestTest(
            Single<ResponseTest> request, boolean shouldUpdateUi,
            @NonNull OnResponseListenerTest responseConsumer,
            @Nullable OnResponseListener<ErrorEntity> errorConsumer) {

        Single<ResponseTest> single = request.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (shouldUpdateUi) {
            single = single.observeOn(AndroidSchedulers.mainThread());
        }

        return single.subscribe(response -> {
            if (response.getStatus().equals(ConstantsApi.STATUS_SUCCESS)) {
                responseConsumer.onRespond(response);
            } else if (errorConsumer != null) {
                String message = null;
                if (response.getMessage()!=null){
                    message = response.getMessage();
                }else {
                    message = ErrorEntity.OOPS;
                }
                errorConsumer.onRespond(ErrorEntity.getError(message));
            }
        }, throwable -> {
            throwable.printStackTrace();
            if (errorConsumer != null) {
                errorConsumer.onRespond(ErrorEntity.getError(throwable));
            }
        });
    }

    public static <T1, T2> Disposable makeRequestZip(Single<Response<T1>> request1,
                                                     Single<Response<T2>> request2, boolean shouldUpdateUi,
                                                     @NonNull PlainConsumer<T1, T2> responseConsumer,
                                                     @Nullable OnResponseListener<ErrorEntity> errorConsumer,
                                                     @Nullable Action onComplete) {
        Single<Response<T1>> single1 = request1.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        Single<Response<T2>> single2 = request2.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());

        if (shouldUpdateUi) {
            single1 = single1.observeOn(AndroidSchedulers.mainThread());
            single2 = single2.observeOn(AndroidSchedulers.mainThread());
        }
        return Single.zip(single1, single2, new BiFunction<Response<T1>, Response<T2>, ResponseZip<T1, T2>>() {
            @Override
            public ResponseZip<T1, T2> apply(Response<T1> t1BaseResponse, Response<T2> t2BaseResponse) {
                ResponseZip<T1, T2> responseZip = new ResponseZip<>();
                responseZip.setT1(t1BaseResponse);
                responseZip.setT2(t2BaseResponse);
                return responseZip;
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    responseConsumer.accept(o);
                    if (onComplete != null) {
                        onComplete.run();
                    }
                }, throwable -> {
                    // handle error
                    throwable.printStackTrace();
                    if (errorConsumer != null) {
                        errorConsumer.onRespond(ErrorEntity.getError(throwable));
                    }
                    if (onComplete != null) {
                        onComplete.run();
                    }
                });
    }
}

    public static <T extends BaseEntity> Disposable makeRequest(Single<BaseResponse<T>> request, boolean shouldUpdateUi, @NonNull OnResponseListener<T> responseConsumer) {
        return makeRequest(request, shouldUpdateUi, responseConsumer, null);
    }
}
