package lyhoangvinh.com.mymvp.ui.profile;

/**
 * Created by LyHoangVinh on 16/04/2018.
 */
public interface IPresenterProfile {
    void getProfile();
    void update(String firstName, String lastName, String email);
}
