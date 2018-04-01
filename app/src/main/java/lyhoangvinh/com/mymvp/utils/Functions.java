package lyhoangvinh.com.mymvp.utils;

import lyhoangvinh.com.mymvp.model.object.User;
import lyhoangvinh.com.mymvp.utils.crop.SharedPrefs;

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
}
