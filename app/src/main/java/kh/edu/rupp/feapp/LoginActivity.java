package kh.edu.rupp.feapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * FEApp
 * Created by leapkh on 3/5/18.
 */

public class LoginActivity extends Activity  implements View.OnClickListener {

    private EditText etxtUsername;
    private EditText etxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Reference to Not Now TextView
        TextView txtNotNow = findViewById(R.id.txt_not_now);

        // Reference to Login Button
        Button btnLogin = findViewById(R.id.btn_login);

        // Set onlick listener to Not Now TextView
        txtNotNow.setOnClickListener(this);

        // Set onlick listener to Login Button
        btnLogin.setOnClickListener(this);

        // Reference to username and password EditText
        etxtUsername = findViewById(R.id.etxt_username);
        etxtPassword = findViewById(R.id.etxt_password);
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.txt_not_now){
            startMainActivity();
            finish();
        } else if (view.getId() == R.id.btn_login){
            String username = etxtUsername.getText().toString();
            String password = etxtPassword.getText().toString();
            String message = "This function is under construction.\nUsername: " + username + "\nPassword: " + password;
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }



}
