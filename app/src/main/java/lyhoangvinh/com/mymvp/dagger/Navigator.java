package lyhoangvinh.com.mymvp.dagger;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import lyhoangvinh.com.mymvp.listener.OnResponseListener;

/**
 * Created by LyHoangVinh on 26/03/2018.
 */

public interface Navigator {
    String EXTRA_ARG = "_args";

    void finishActivity();

    void startActivity(@NonNull Intent var1);

    void startActivity(@NonNull String var1);

    void startActivity(@NonNull String var1, @NonNull Uri var2);

    void startActivity(@NonNull Class<? extends Activity> var1);

    void startActivity(@NonNull Class<? extends Activity> var1, @NonNull OnResponseListener<Intent> var2);

    void startActivity(@NonNull Class<? extends Activity> var1, Bundle var2);

    void startActivity(@NonNull Class<? extends Activity> var1, Parcelable var2);

    void startActivity(@NonNull Class<? extends Activity> var1, @NonNull String var2);

    void startActivity(@NonNull Class<? extends Activity> var1, int var2);

    void startActivityWithTransition(@NonNull Class<? extends Activity> var1, @NonNull OnResponseListener<Intent> var2, boolean var3, boolean var4, View... var5);

    void startActivityForResult(@NonNull Class<? extends Activity> var1, int var2);

    void startActivityForResult(@NonNull Class<? extends Activity> var1, @NonNull OnResponseListener<Intent> var2, int var3);

    void startActivityForResult(@NonNull Class<? extends Activity> var1, @NonNull Parcelable var2, int var3);

    void startActivityForResult(@NonNull Class<? extends Activity> var1, @NonNull String var2, int var3);

    void startActivityForResult(@NonNull Class<? extends Activity> var1, int var2, int var3);

    void replaceFragment(@IdRes int var1, @NonNull Fragment var2, View... var3);

    void replaceFragment(@IdRes int var1, @NonNull Fragment var2, Bundle var3, View... var4);

    void replaceFragment(@IdRes int var1, @NonNull Fragment var2, @NonNull String var3, Bundle var4, View... var5);

    void replaceFragmentAndAddToBackStack(@IdRes int var1, @NonNull Fragment var2, Bundle var3, String var4, View... var5);

    void replaceFragmentAndAddToBackStack(@IdRes int var1, @NonNull Fragment var2, @NonNull String var3, Bundle var4, String var5, View... var6);

    @Nullable
    <T extends Fragment> T findFragmentByTag(@NonNull String var1);

    @Nullable
    <T extends Fragment> T findFragmentById(@IdRes int var1);
}
