package kh.edu.rupp.feapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class SelectGenderActivity extends AppCompatActivity {

    private RadioButton rdoMale;
    private RadioButton rdoFemale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_gender);

        rdoMale = findViewById(R.id.rdoMale);
        rdoFemale = findViewById(R.id.rdoFemale);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        if (gender != null) {
            if (gender.equals("Male")) {
                rdoMale.setChecked(true);
            } else if (gender.equals("Female")) {
                rdoFemale.setChecked(true);
            }
        }
    }

    public void onMaleClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("gender", "Male");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onFemaleClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("gender", "Female");
        setResult(RESULT_OK, intent);
        finish();
    }

}
