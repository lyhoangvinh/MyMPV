package lyhoangvinh.com.mymvp.utils.crop;

import lyhoangvinh.com.mymvp.model.User;
import lyhoangvinh.com.mymvp.model.UserGithub;
import lyhoangvinh.com.mymvp.utils.dbhelper.SharedPrefs;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
public class Functions {

    private static final String USER_NAME = "USERNAME";
    private static final String GITHUB_USER = "GITHUB_USER";

    public static void saveUser(User user) {
        SharedPrefs.getInstance().put(USER_NAME, user);
    }

    public static User getUser() {
        return SharedPrefs.getInstance().get(USER_NAME, User.class);
    }

    public static void clearData() {
        SharedPrefs.getInstance().clear();
    }

    public static boolean checkUser() {
        if (getUser() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void saveGithus(UserGithub userGithub) {
        SharedPrefs.getInstance().put(GITHUB_USER, userGithub);
    }

    public static UserGithub getUserGithub() {
        return SharedPrefs.getInstance().get(GITHUB_USER, UserGithub.class);
    }
}
