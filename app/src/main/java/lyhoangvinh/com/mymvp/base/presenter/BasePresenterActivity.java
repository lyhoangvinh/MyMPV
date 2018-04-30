package lyhoangvinh.com.mymvp.base.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;

import lombok.Getter;
import lyhoangvinh.com.mymvp.base.activity.BaseActivity;
import lyhoangvinh.com.mymvp.base.view.BaseView;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */

public abstract class BasePresenterActivity<P extends BasePresenter, V extends BaseView> extends BaseActivity {

    @Getter
    protected P presenter;

    protected Context context;

    protected V view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @CallSuper
    protected void initialize() {
        if (presenter != null) {
            presenter.bindView(getViewLayer());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter != null) {
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (presenter != null) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    private V getViewLayer() {
        // noinspection unchecked
        return ((V) this);
    }
}
