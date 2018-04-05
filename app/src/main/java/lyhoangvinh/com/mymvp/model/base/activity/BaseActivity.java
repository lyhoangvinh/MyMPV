package lyhoangvinh.com.mymvp.model.base.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.ButterKnife;
import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.dagger.component.ActivityComponent;
import lyhoangvinh.com.mymvp.model.base.view.BaseView;
import lyhoangvinh.com.mymvp.thread.UIThreadExecutor;
import lyhoangvinh.com.mymvp.ui.customview.SimpleToast;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private Dialog progress_dialog = null;

    public UIThreadExecutor uiThreadExecutor;

    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        uiThreadExecutor = UIThreadExecutor.getInstance();
        bind();
    }

    protected abstract int getLayout();

    protected abstract void bind();

    public ActivityComponent getComponent(){
//        if (component == null){
//            component = DaggerActivityComponent.builder()
//                    .activityModule(new ActivityModule(this))
//                    .appComponent(InjectionHelper.getAppComponent(this))
//                    .build();
//        }
        return null;
    }

    public void showProgressDialog(boolean cancelable) {
        if (progress_dialog == null) {
            progress_dialog = new Dialog(BaseActivity.this);
            progress_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progress_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress_dialog.setContentView(R.layout.progress_dialog);
        }

        if (!progress_dialog.isShowing()) {
            progress_dialog.setCanceledOnTouchOutside(cancelable);
            progress_dialog.setCancelable(cancelable);
            progress_dialog.show();
        }

        if (!isNetworkAvailable(this)) {
            progress_dialog.dismiss();
        }
    }

    public void showProgressDialogNew(boolean cancelable) {
        if (progress_dialog == null) {
            progress_dialog = new Dialog(BaseActivity.this);
            progress_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progress_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress_dialog.setContentView(R.layout.progress_dialog);
        }

        if (!progress_dialog.isShowing()) {
            progress_dialog.setCanceledOnTouchOutside(cancelable);
            progress_dialog.setCancelable(cancelable);
            progress_dialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progress_dialog != null && progress_dialog.isShowing()) {
            progress_dialog.dismiss();
        }
    }

    public void showToastError(String message) {
        if (message != null) {
            SimpleToast.error(BaseActivity.this, message);
        }
    }

    public void showToastInfo(String message) {
        if (message != null) {
            SimpleToast.info(BaseActivity.this, message);
        }
    }

    public void showToastOk(String message) {
        if (message != null) {
            SimpleToast.ok(BaseActivity.this, message);
        }
    }

    public void showPopupPrompt(String message) {
        // custom dialog
        final Dialog dialog = new Dialog(this);

//        dialog.getWindow().clearFlags(
//                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.popup_prompt);

        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);
        TextView txtOk = (TextView) dialog.findViewById(R.id.txtOk);
        txtMessage.setText(message);

//        Font font = new Font(this);
//        font.overrideFontsLight(txtMessage);
//        font.overrideFontsBold(txtOk);

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }


    @Override
    public void showLoading() {
        showProgressDialog(false);
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void showError(String message) {
        if (message != null) {
            showToastError(message);
        }
    }
}
