package com.itsniv.attendenceapplication.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.itsniv.attendenceapplication.R;
import com.itsniv.attendenceapplication.model.UserModelInfo;

import java.util.ArrayList;

/**
 * Created by it's niv on 17-11-2016.
 */

public class Register extends AppCompatActivity  {

    EditText vfirstname,vlastname,vemail,vpassword,vmobileno;
    Button vregister;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        init();
    }
    private void init()
    {
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        vfirstname=(EditText)findViewById(R.id.editText_UserFirstName);
        vlastname=(EditText)findViewById(R.id.editText_UserLastName);
        vpassword=(EditText)findViewById(R.id.editText_Password);
        vemail=(EditText)findViewById(R.id.editText_EmailId);
        vmobileno=(EditText)findViewById(R.id.editText_MobileNumber);

        final Spinner spinnerlist=(Spinner)findViewById(R.id.spinnerCourse);
        ArrayList<String> list=new ArrayList<String>();
        list.add("COURSE");
        list.add("Android");
        list.add("iOS");
        list.add("UI/UX");

        ArrayAdapter<String> adp=new ArrayAdapter <String> (Register.this,android.R.layout.simple_spinner_item,list);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerlist.setAdapter(adp);

        final Spinner time=(Spinner)findViewById(R.id.spinnerBatchTime);
        ArrayList<String> timelist=new ArrayList<String>();
        timelist.add("TIMING");
        timelist.add("08-10am");
        timelist.add("09-12pm");
        timelist.add("10-01pm");
        timelist.add("11-01pm");
        timelist.add("02-04pm");
        timelist.add("03-05pm");

        ArrayAdapter<String> adpp=new ArrayAdapter<String>(Register.this,android.R.layout.simple_spinner_item,timelist);
        adpp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        time.setAdapter(adpp);

        vregister=(Button)findViewById(R.id.register_button);

        vregister.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alartdialog=new AlertDialog.Builder(Register.this);
                alartdialog.setTitle("Confirm Registration");
                alartdialog.setMessage("Please Select ")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String firstname=vfirstname.getText().toString();
                                String lastname=vlastname.getText().toString();
                                String mobile=vmobileno.getText().toString();
                                String email=vemail.getText().toString();
                                String password=vpassword.getText().toString();



                                if(TextUtils.isEmpty(firstname)){
                                    Toast.makeText(Register.this,"Enter First Name",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(TextUtils.isEmpty(lastname)){
                                    Toast.makeText(Register.this,"Enter Last Name",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(TextUtils.isEmpty(mobile)){
                                    Toast.makeText(Register.this,"Enter First Name",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(TextUtils.isEmpty(email)){
                                    Toast.makeText(Register.this,"Enter First Name",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(TextUtils.isEmpty(password)){
                                    Toast.makeText(Register.this,"Enter First Name",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                /*if(firstname.isEmpty()){
                                    vfirstname.setError("Please enter First Name");
                                    valid=false;
                                }else {vfirstname.setError(null);}

                                if (email.isEmpty()){
                                    vemail.setError("Please enter Email Address");
                                    valid=false;
                                }else {vemail.setError(null);}

                                if (lastname.isEmpty()){
                                    vlastname.setError("Please Enter Last Name");
                                    valid=false;
                                }else {vlastname.setError(null);}

                                if(mobile.isEmpty()){
                                    vmobileno.setError("Please Enter Mobile Number");
                                    valid=false;
                                }else{vmobileno.setError(null);}

                                if (password.isEmpty()){
                                    vpassword.setError("Plese Enter password");
                                    valid=false;
                                }else{vpassword.setError(null);}

                                if (vpassword.equals(vcnfpassword)){
                                }else {vcnfpassword.setError("Password not matching");}
*/
                                Intent intent=new Intent(Register.this, Menu.class);
                                startActivity(intent);

                                UserModelInfo userModelInfo=new UserModelInfo();

                                userModelInfo.setVfirstname(vfirstname.getText().toString());
                                userModelInfo.setVlastname(vlastname.getText().toString());
                                userModelInfo.setVemail(vemail.getText().toString());
                                userModelInfo.setVmobileno(vmobileno.getText().toString());
                                userModelInfo.setVpassword(vpassword.getText().toString());

                                mDatabase.child("USER").child("Students").push().setValue(userModelInfo);



                                Toast.makeText(Register.this,"successfully Registered",Toast.LENGTH_SHORT).show();
                                firebaseAuth.createUserWithEmailAndPassword(vemail.getText().toString(),vpassword.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                            }
                                        });
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=alartdialog.create();
                dialog.show();
            }
        });
    }
}