package lyhoangvinh.com.mymvp.utils.crop;

import lyhoangvinh.com.mymvp.model.User;
import lyhoangvinh.com.mymvp.utils.dbhelper.SharedPrefs;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
public class Functions {

    private static final String USER_NAME = "USERNAME";

    public static void saveUser(User user) {
        SharedPrefs.getInstance().put(USER_NAME, user);
    }

    public static User getUser() {
        return SharedPrefs.getInstance().get(USER_NAME, User.class);
    }

    public static void clearData() {
        if (getUser() != null) {
            SharedPrefs.getInstance().clear();
        }
    }

    public static boolean checkUser() {
        if (getUser() == null) {
            return false;
        } else {
            return true;
        }
    }
}
