package lyhoangvinh.com.mymvp.ui.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import lyhoangvinh.com.mymvp.R;


public class SimpleToast {

    private static LayoutInflater mInflater;
    private static Toast mToast;
    private static View mView;

    public static void ok(Context activitycontext, String msg) {
        Context context = activitycontext.getApplicationContext();
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.toast_ok, null);
        initSetButtonMsg(msg, context);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setView(mView);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void error(Context activitycontext, String msg) {
        Context context = activitycontext.getApplicationContext();
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.toast_error, null);
        initSetButtonMsg(msg, context);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setView(mView);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void info(Context activitycontext, String msg) {
        Context context = activitycontext.getApplicationContext();
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.toast_info, null);
        initSetButtonMsg(msg, context);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setView(mView);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void muted(Context activitycontext, String msg) {
        Context context = activitycontext.getApplicationContext();

        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.toast_muted, null);
        initSetButtonMsg(msg, context);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setView(mView);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void warning(Context activitycontext, String msg) {
        Context context = activitycontext.getApplicationContext();

        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.toast_warning, null);
        initSetButtonMsg(msg, context);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setView(mView);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    private static TextView initSetButtonMsg(String msg, Context activitycontext) {
//        Context context = activitycontext.getApplicationContext();

        TextView txtMsg = (TextView) mView.findViewById(R.id.txtMsg);
        txtMsg.setText(msg);
//        Font font = new Font(context);
//        txtMsg.setTypeface(font.light);
        return txtMsg;
    }
}
