package lyhoangvinh.com.mymvp.dagger;

import android.app.Activity;

import lyhoangvinh.com.mymvp.MyApplication;
import lyhoangvinh.com.mymvp.dagger.component.AppComponent;

/**
 * Created by LyHoangVinh on 26/03/2018.
 */

public class InjectionHelper {
    public static AppComponent getAppComponent(Activity activity) {
        return MyApplication.get(activity).getAppComponent();
    }
}
