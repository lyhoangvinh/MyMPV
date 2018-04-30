package lyhoangvinh.com.mymvp.base.api.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import lyhoangvinh.com.mymvp.base.view.ErrorEntity;
import lyhoangvinh.com.mymvp.listener.OnResponseListener;
import retrofit2.Response;

/**
 * Created by LyHoangVinh on 30/04/2018.
 */
public class GithubApiUtils {
    /**
     * Create new retrofit api request
     * @param request observable request
     * @param shouldUpdateUi true if should update UI after response returned
     * @param responseConsumer consume response data
     * @param errorConsumer consume errors
     * @param <T> Type of response body
     */
    public static <T> Disposable makeRequest(
            Single<Response<T>> request, boolean shouldUpdateUi,
            @NonNull OnResponseListener<T> responseConsumer,
            @Nullable OnResponseListener<ErrorEntity> errorConsumer,
            @Nullable Action onComplete) {

        Single<Response<T>> single = request.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (shouldUpdateUi) {
            single = single.observeOn(AndroidSchedulers.mainThread());
        }

        return single.subscribe(response -> {
            if (response.isSuccessful()) {
                responseConsumer.onRespond(response.body());
            } else if (errorConsumer != null) {
                errorConsumer.onRespond(ErrorEntity.getError(response.message()));
            }
            if (onComplete != null) {
                onComplete.run();
            }
        }, throwable -> {
            if (throwable instanceof RuntimeException) {
                // must be fixed while developing
                throw new Exception(throwable);
            }
            // handle error
            if (errorConsumer != null) {
                errorConsumer.onRespond(ErrorEntity.getError(throwable));
            }
            if (onComplete != null) {
                onComplete.run();
            }
        });
    }

    public static <T> Disposable makeRequest(Single<Response<T>> request, boolean shouldUpdateUi, @NonNull OnResponseListener<T> responseConsumer) {
        return makeRequest(request, shouldUpdateUi, responseConsumer, null, null);
    }

    public static <T> Disposable makeRequest(Single<Response<T>> request, boolean shouldUpdateUi,
                                             @NonNull OnResponseListener<T> responseConsumer,
                                             @Nullable OnResponseListener<ErrorEntity> errorConsumer) {
        return makeRequest(request, shouldUpdateUi, responseConsumer, errorConsumer, null);
    }

}
