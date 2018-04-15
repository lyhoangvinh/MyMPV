package lyhoangvinh.com.mymvp.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.base.fragment.BaseFragment;

public abstract class BaseSingleFragmentActivity<T extends BaseFragment> extends lyhoangvinh.com.mymvp.model.base.activity.BaseActivity {

    @BindView(R.id.toolbar)
    @Nullable
    Toolbar mToolbar;

    @BindView(R.id.btnAdd)
    @Nullable
    Button btnAdd;

    @BindView(R.id.btnReplace)
    @Nullable
    Button btnReplace;

    @BindView(R.id.btnRemove)
    @Nullable
    Button btnRemove;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateBind(savedInstanceState);
        btnAdd.setOnClickListener(view -> {

        });

        btnReplace.setOnClickListener(view -> {
//            onCreateReplace(savedInstanceState);
        });

        btnRemove.setOnClickListener(view -> onCreateRemove(getFragment()));
    }


    private void onCreateBind(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(getContainerId(), createFragment(), null);
        }
    }

//    private void onCreateReplace(Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            replaceFragment(getContainerId(), createFragmentTest(), null);
//        }
//    }

    private void onCreateRemove(Fragment f) {
        removeFragment(f);
    }

    @Override
    public int getLayout() {
        return R.layout.container;
    }

    protected abstract T createFragment();

//    protected abstract T1 createFragmentTest();

    @Nullable
    public T getFragment() {
        // noinspection unchecked
        return (T) getSupportFragmentManager().findFragmentById(getContainerId());
    }

//    @Nullable
//    public T1 getFragmentT1() {
//        // noinspection unchecked
//        return (T1) getSupportFragmentManager().findFragmentById(getContainerId());
//    }

    @IdRes
    protected int getContainerId() {
        return R.id.container;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        T fragment = getFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }

//        T1 f = getFragmentT1();
//        if (f != null) {
//            f.onActivityResult(requestCode, resultCode, data);
//        }
    }

    @Override
    public void onBackPressed() {
        T fragment = getFragment();
        if (fragment != null) {
            if (!fragment.onBackPressed()) {
                finishWithAnimation();
            }
        }

    }

    protected void hideToolbar() {
        if (mToolbar != null)
            mToolbar.setVisibility(View.GONE);
    }
}
