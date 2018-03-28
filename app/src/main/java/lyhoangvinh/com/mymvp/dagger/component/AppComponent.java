package lyhoangvinh.com.mymvp.dagger.component;

import dagger.Component;
import lyhoangvinh.com.mymvp.MyApplication;

/**
 * Created by LyHoangVinh on 26/03/2018.
 */

@Component
public interface AppComponent {
    void inject(MyApplication application);
}
