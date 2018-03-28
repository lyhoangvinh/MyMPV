package lyhoangvinh.com.mymvp.interfaces;

/**
 * Created by duypham on 9/10/17.
 * Indicate refreshable ui objects, eg. activity, fragment...
 */

public interface UiRefreshable extends Refreshable{
    void doneRefresh();
    void refreshWithUi();
    void refreshWithUi(int delay);
    void setRefreshEnabled(boolean enabled);
}
