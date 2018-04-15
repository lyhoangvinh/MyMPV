package lyhoangvinh.com.mymvp.model.base.view;

/**
 * Created by air on 5/20/17.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String message);
}