package lyhoangvinh.com.mymvp.model.base.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import butterknife.ButterKnife;
import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.model.base.view.BaseView;
import lyhoangvinh.com.mymvp.thread.UIThreadExecutor;
import lyhoangvinh.com.mymvp.ui.customview.SimpleToast;

/**
 * Created by LyHoangVinh on 15/04/2018.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    private Dialog progress_dialog = null;
    public UIThreadExecutor uiThreadExecutor;

    private String TAG = "Place";

    protected abstract int getLayout();

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    protected abstract void bind();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        uiThreadExecutor = UIThreadExecutor.getInstance();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bind();
    }

    public void showProgressDialog(boolean cancelable) {
        if (getActivity() != null && progress_dialog == null) {
            progress_dialog = new Dialog(getActivity());
            progress_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progress_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress_dialog.setContentView(R.layout.progress_dialog);
        }

        if (!progress_dialog.isShowing()) {
            progress_dialog.setCanceledOnTouchOutside(cancelable);
            progress_dialog.setCancelable(cancelable);
            progress_dialog.show();
        }

        if (!isNetworkAvailable(getActivity())) {
            progress_dialog.dismiss();
        }
    }

    public void showProgressDialogNew(boolean cancelable) {
        if (getActivity() != null && progress_dialog == null) {
            progress_dialog = new Dialog(getActivity());
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
        if (getActivity() != null && message != null) {
            SimpleToast.error(getActivity(), message);
        }
    }

    public void showToastInfo(String message) {
        if (getActivity() != null && message != null) {
            SimpleToast.info(getActivity(), message);
        }
    }

    public void showToastOk(String message) {
        if (getActivity() != null && message != null) {
            SimpleToast.ok(getActivity(), message);
        }
    }

    public void showPopupPrompt(String message) {
        // custom dialog
        if (getActivity() != null) {
            final Dialog dialog = new Dialog(getActivity());

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

            txtOk.setOnClickListener(v -> dialog.dismiss());

            dialog.show();
        }
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

    public boolean onBackPressed() {
        return false;
    }
}
