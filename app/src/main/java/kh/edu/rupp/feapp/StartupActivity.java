package kh.edu.rupp.feapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * FEApp
 * Created by leapkh on 3/5/18.
 */

public class StartupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_startup);
        delayToStartLoginActivity();

        // Initialize Fresco library
        Fresco.initialize(this);
    }

    private void delayToStartLoginActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLoginActivity();
                finishStartupActivity();
            }
        }, 3000);
    }

    private void startLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void finishStartupActivity(){
        finish();
    }

}
