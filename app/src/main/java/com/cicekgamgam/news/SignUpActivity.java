package com.cicekgamgam.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText emailId,password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFireBaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mFireBaseAuth =FirebaseAuth.getInstance();
        emailId=findViewById(R.id.etmail);
        password=findViewById(R.id.etpassword);
        btnSignUp=findViewById(R.id.btnsignup);
        tvSignIn=findViewById(R.id.signintext);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();

                if(email.isEmpty())
                {
                    emailId.setError("Please Enter Email");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(email.isEmpty()&&pwd.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this,"Fields are empty!!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()&&pwd.isEmpty()))
                {
                    mFireBaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(SignUpActivity.this,"Sign Up Unsuccessfull!! Please try again.",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                            }
                        }
                    });
                }

                else{
                    Toast.makeText(SignUpActivity.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });


        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(i);

            }
        });
    }



}
