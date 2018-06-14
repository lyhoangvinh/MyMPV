package lyhoangvinh.com.mymvp.listener;

import android.support.annotation.NonNull;

import lyhoangvinh.com.mymvp.base.response.ResponseZip;

public interface PlainConsumer<T1, T2> {
    void accept(@NonNull ResponseZip<T1, T2> var1);
}
