package kh.edu.rupp.feapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import kh.edu.rupp.feapp.model.LoginResponse;

/**
 * FEApp
 * Created by leapkh on 3/5/18.
 */

public class LoginActivity extends Activity implements View.OnClickListener {

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

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txt_not_now) {
            startMainActivity();
            finish();
        } else if (view.getId() == R.id.btn_login) {
            String username = etxtUsername.getText().toString();
            String password = etxtPassword.getText().toString();
            processLogin(username, password);
        }
    }

    private void processLogin(final String username, final String password) {
        String loginUrl = "http://test.js-cambodia.com/rupp/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                if (loginResponse.getCode() == 0) {
                    Toast.makeText(getApplicationContext(), "Login success: " + loginResponse.getUser().getFullName(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while trying to login.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> data = new HashMap<>();
                data.put("username", username);
                data.put("password", password);
                return data;

            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}
