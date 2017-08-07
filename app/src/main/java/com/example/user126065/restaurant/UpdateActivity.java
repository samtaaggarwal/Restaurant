package com.example.user126065.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private Button up;
    private TextView tvBack,etEmail;
    private EditText etPass;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        String email = intent.getStringExtra("us");
        db = new DbHelper(this);
        up = (Button) findViewById(R.id.btnUp);
        tvBack = (TextView) findViewById(R.id.tvMenu);
        etEmail = (TextView) findViewById(R.id.etUpEmail);
        etEmail.setText(email);
        etPass = (EditText) findViewById(R.id.etUpPass);
        String pass = intent.getStringExtra("ps");
        etPass.setText(pass);
        up.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUp:
                UpdateVal();
                break;
            case R.id.tvMenu:
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                finish();
                break;
            default:

        }
    }

    private void UpdateVal()
    {
        Intent intent = getIntent();
        String email = intent.getStringExtra("us");
        String pass = intent.getStringExtra("ps");
        String pass1 = etPass.getText().toString();
        if ( pass1.isEmpty())
        {
            displayToast("password field empty");
        }
        else
         {
            //db.addUser(email, pass);
            db.updateUser(email,pass,pass1);
            displayToast("User Updated");
            finish();
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}