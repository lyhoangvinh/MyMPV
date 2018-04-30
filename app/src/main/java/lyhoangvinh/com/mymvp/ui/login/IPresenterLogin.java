package lyhoangvinh.com.mymvp.ui.login;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
public interface IPresenterLogin {
    void userLoginRx(String email, String password);
    void userLoginTest(String email, String password);
    void githubLogin(String email, String password);
}
