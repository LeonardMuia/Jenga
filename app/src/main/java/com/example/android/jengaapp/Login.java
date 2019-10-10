package com.example.android.jengaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;


import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    @BindView(R.id.customerRegister)
    TextView toRegister;
    @BindView(R.id.inputEmail)
    EditText userEmail;
    @BindView(R.id.passwordInput)
    EditText userPassword;
    @BindView(R.id.loginBtn)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        // Initializing Auth
        mAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(this);

        // Intent to CustomerReg Activity
        toRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registrationView = new Intent(getApplicationContext(), Registration.class);
                startActivity(registrationView);
            }
        });
    }

    /*
    *  Check if a user is signed in
    *  If Signed In, Redirect to Main Page with Session
    */
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /*
     *  Sign in a registered user
     *  Start Session
     */
    private void signIn(String emailEntered, String passwordEntered){
         if(!validateForm()){
           return;
         }

        // showProgressDialog();

        mAuth.signInWithEmailAndPassword(emailEntered,passwordEntered)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(Login.this,"Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                       // if(!task.isSuccessful()){
                       //    mStatusTextView.setText(R.string.auth_failed);
                       // }
                       // hideProgressDialog();
                    }
                });

    }

    /*
     *  Validate User Input
     */

    private boolean validateForm(){
        boolean valid = true;
        String email = userEmail.getText().toString();
        if (TextUtils.isEmpty(email)){
            userEmail.setError("Required");
            valid = false;
        } else {
            userEmail.setError(null);
        }

        String password = userPassword.getText().toString();
        if(TextUtils.isEmpty(password)){
            userPassword.setError("Required");
            valid = false;
        } else {
            userPassword.setError(null);
        }
        return valid;
    }


    /*
     *  Update User Interface
     *  If user is logged in, redirect to main activity
     *  Else show login activity
     */

        private void updateUI(FirebaseUser user){
            //hideProgressDialog();
            if(user != null){
               setContentView(R.layout.main);
            }
        }

    @Override
    public void onClick(View v){
         int i = v.getId();
         if(i == R.id.loginBtn){
             signIn(userEmail.getText().toString(),userPassword.getText().toString());
         }
    }
}
