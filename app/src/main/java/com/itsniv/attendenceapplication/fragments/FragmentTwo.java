package com.itsniv.attendenceapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itsniv.attendenceapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by it's niv on 07-12-2016.
 */

public class FragmentTwo extends Fragment implements View.OnClickListener {
    EditText name, email, feedback;
    Button submitFeedback;

    Pattern pattern;
    Matcher matcher;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feedback,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        name = (EditText)view.findViewById(R.id.yourname);
        email = (EditText)view.findViewById(R.id.email);
        feedback = (EditText)view.findViewById(R.id.yourfeeback);

        submitFeedback = (Button)view.findViewById(R.id.submitfeedbackbutton);
        setinit();
        emailvalidation("");
        submitFeedback.setOnClickListener(this);

    }

    private void setinit() {
    }

    private boolean emailvalidation(String email) {
        final String expression = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        CharSequence inputStr = email;
        pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    @Override
    public void onClick(View v) {

        String yourname = name.getText().toString();
        String emailaddress = email.getText().toString();
        String feedbacktext = feedback.getText().toString();

        if (yourname.matches("")){

            Toast.makeText(getActivity(),"Enter Name",Toast.LENGTH_LONG).show();
            return;
        } else  if(!emailvalidation(emailaddress)){


            //isvalid = true;
            Toast.makeText(getActivity(),"Enter valid Email",Toast.LENGTH_LONG).show();
            return;


        } else if (feedbacktext.matches("")){
            Toast.makeText(getActivity(),"Enter Feedback",Toast.LENGTH_LONG).show();
            return;

        }

        //  email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        Intent emailsend = new Intent(Intent.ACTION_SEND);

        emailsend.putExtra(Intent.EXTRA_EMAIL, new String[]{emailaddress});
        emailsend.putExtra(Intent.EXTRA_SUBJECT,yourname);
        emailsend.putExtra(Intent.EXTRA_TEXT,feedbacktext);


        emailsend.setType("message/rfc822");

        startActivity(Intent.createChooser(emailsend, "Choose an Email client :"));

    }


}

