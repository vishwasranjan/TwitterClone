package com.example.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText edtSignupUsername,edtSignupEmail,edtSignupPassword;
    Button btnSignup,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtSignupEmail=findViewById(R.id.edtSignupEmail);
        edtSignupPassword=findViewById(R.id.edtSignupPassword);
        edtSignupUsername=findViewById(R.id.edtSignupUsername);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(SignUp.this);
        btnLogin.setOnClickListener(SignUp.this);
        if (ParseUser.getCurrentUser()!=null)
        {
            Intent intent=new Intent(SignUp.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnSignup:
                final ProgressDialog progressDialog=new ProgressDialog(this);
                progressDialog.setMessage("Loading");
                progressDialog.show();
                progressDialog.setCancelable(false);
                final ParseUser parseUser=new ParseUser();
                parseUser.setEmail(edtSignupEmail.getText().toString());
                parseUser.setUsername(edtSignupUsername.getText().toString());
                parseUser.setPassword(edtSignupPassword.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null)
                        {
                            Toast.makeText(SignUp.this,"Welcome "+ParseUser.getCurrentUser().getUsername(),Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUp.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();

                    }
                });
                break;
            case R.id.btnLogin:
                Intent intent=new Intent(SignUp.this,LogIn.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}