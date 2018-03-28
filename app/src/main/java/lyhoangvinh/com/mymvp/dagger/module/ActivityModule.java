package lyhoangvinh.com.mymvp.dagger.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import lyhoangvinh.com.mymvp.dagger.qualifier.ActivityContext;

/**
 * Created by LyHoangVinh on 26/03/2018.
 */
@Module
public class ActivityModule {
    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }
    @Provides
    protected AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    protected Context provideContext() {
        return mActivity;
    }
}
