package com.cicekgamgam.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import lombok.SneakyThrows;

public class SignInActivity extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;

    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFireBaseAuth = FirebaseAuth.getInstance();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
                if (mFireBaseUser != null) {
                    Intent i = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };

        setContentView(R.layout.activity_sign_in);
        emailId = findViewById(R.id.etmail);
        password = findViewById(R.id.etpassword);
        btnSignIn = findViewById(R.id.btnsignup);
        tvSignUp = findViewById(R.id.signintext);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if (email.isEmpty() || pwd.isEmpty()) {
                    emailId.setError("Check your email and password");
                } else {
                    mFireBaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @SneakyThrows
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "Login error ,Please login again!", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intToMain = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intToMain);

                            }
                        }
                    });
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SignInActivity.this, "Please sign in", Toast.LENGTH_SHORT).show();
    }
}
