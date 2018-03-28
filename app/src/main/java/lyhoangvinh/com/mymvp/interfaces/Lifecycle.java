package lyhoangvinh.com.mymvp.interfaces;

import android.os.Bundle;

/**
 * Created by duypham on 9/10/17.
 */

public interface Lifecycle extends Destroyable{
    void onCreate();
    void onStart();
    void onStop();
    void onSaveInstanceState(Bundle outState);
    void onRestoreInstanceState(Bundle savedInstanceState);
}
