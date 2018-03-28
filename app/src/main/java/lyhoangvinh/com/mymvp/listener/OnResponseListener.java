package lyhoangvinh.com.mymvp.listener;

import android.support.annotation.NonNull;

/**
 * Created by ADMIN on 10/2/2017.
 */

public interface OnResponseListener<T> {
    void onRespond(@NonNull T t);
}
