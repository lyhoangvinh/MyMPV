package lyhoangvinh.com.mymvp.utils;

import android.text.TextUtils;
import android.util.Base64;

/**
 * Created by LyHoangVinh on 30/04/2018.
 */
public class StringUtils {
    public StringUtils() {
    }

    public static String getBasicAuth(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.encodeToString(credentials.getBytes(), 2);
    }

    public static boolean isNotEmpty(String... strings) {
        String[] var1 = strings;
        int var2 = strings.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            if (TextUtils.isEmpty(s)) {
                return false;
            }
        }

        return true;
    }
}
