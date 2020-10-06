package com.example.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText edtLoginUsername,edtLoginPassword;
    Button btnLogin2,btnSignup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        edtLoginPassword=findViewById(R.id.edtLoginpassword);
        edtLoginUsername=findViewById(R.id.edtLoginusername);
        btnLogin2=findViewById(R.id.btnLogin2);
        btnSignup2=findViewById(R.id.btnSignup2);
        btnLogin2.setOnClickListener(LogIn.this);
        btnSignup2.setOnClickListener(LogIn.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin2:
                final ProgressDialog progressDialog=new ProgressDialog(this);
                progressDialog.setMessage("Loading");
                progressDialog.show();
                progressDialog.setCancelable(false);
                ParseUser.logInInBackground(edtLoginUsername.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e==null&&user!=null)
                        {
                            Toast.makeText(LogIn.this,"Welcome "+ParseUser.getCurrentUser().getUsername(),Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LogIn.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(LogIn.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
                break;
            case R.id.btnSignup2:
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}