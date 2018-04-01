package lyhoangvinh.com.mymvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import lombok.Setter;
import lyhoangvinh.com.mymvp.dagger.component.AppComponent;
import lyhoangvinh.com.mymvp.dagger.component.DaggerAppComponent;

/**
 * Created by LyHoangVinh on 23/03/2018.
 */

public class MyApplication extends Application {

    @Setter
    protected AppComponent appComponent;

    private Gson mGSon;

    private static MyApplication mSelf;

    public static MyApplication self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGSon = new Gson();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);

    }

    public Gson getGSon() {
        return mGSon;
    }

    public static MyApplication get(Activity activity) {
        return (MyApplication)activity.getApplication();
    }

    public static MyApplication get(Fragment fragment) {
        return get(fragment.getActivity());
    }

    public static MyApplication get(Context context) {
        return (MyApplication)context.getApplicationContext();
    }

    // component
    protected void setupAppComponent() {
        appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            setupAppComponent();
        }
        return appComponent;
    }
}
