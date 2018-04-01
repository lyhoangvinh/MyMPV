package lyhoangvinh.com.mymvp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.ui.address.AddressActivity;
import lyhoangvinh.com.mymvp.ui.login.LoginActivity;
import lyhoangvinh.com.mymvp.utils.Functions;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            if (Functions.checkUser()){
                startActivity(new Intent(SplashActivity.this, AddressActivity.class));
                finish();
            }else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 500);
    }
}
