package com.itsniv.attendenceapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.itsniv.attendenceapplication.R;

/**
 * Created by it's niv on 17-11-2016.
 */

public class Login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase;

    EditText vEmail,vPassword;
    Button vlogin;
    TextView vforgotpsw,vcreteone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        init();
    }
    private void init() {
        firebaseAuth=FirebaseAuth.getInstance();

        vEmail=(EditText)findViewById(R.id.editText_UserName);
        vPassword=(EditText)findViewById(R.id.editText_UserPassword);

        vlogin=(Button)findViewById(R.id.login_button);
        vlogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(vEmail.getText().toString())) {
                    Toast.makeText(Login.this, "Please Enter Email Address", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(vPassword.getText().toString())){
                    Toast.makeText(Login.this,"Please Enter Correct Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(vEmail.getText().toString(),vPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent=new Intent(Login.this,Menu.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Login.this,"Error",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        vforgotpsw=(TextView)findViewById(R.id.txtForgotPassword);
        vcreteone=(TextView)findViewById(R.id.textViewRegisterTextClick);
        firebaseAuth=FirebaseAuth.getInstance();
        vcreteone.setOnClickListener(new TextView.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        vforgotpsw.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}