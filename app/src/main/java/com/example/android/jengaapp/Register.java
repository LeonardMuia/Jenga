package com.example.android.jengaapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends auth implements View.OnClickListener {

    @BindView(R.id.loginText)
    TextView already_registered;
    @BindView(R.id.customerLogin)
    RadioButton customer_login;
    @BindView(R.id.username_reg)
    EditText username_registration;
    @BindView(R.id.email_reg)
    EditText email_registration;
    @BindView(R.id.phone_reg)
    EditText phone_registration;
    @BindView(R.id.password_reg)
    EditText password_registration;
    @BindView(R.id.registerBtn)
    Button create_account;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
        create_account.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);


    }

    /*
     *  Registration
     * @Param username, email, phone, password
     */
    public void createAccount(String email, String password){
        if (!validateForm()){
            return;
        }
        // showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // hideProgressDialog();
                    }
                });
    }

    /*
     *  Validate User Input
     */

    private boolean validateForm(){
        boolean valid = true;
        String username = username_registration.getText().toString();
        if (TextUtils.isEmpty(username)){
            username_registration.setError("Required");
            valid = false;
        } else {
            username_registration.setError(null);
        }

        String email = email_registration.getText().toString();
        if (TextUtils.isEmpty(email)){
            email_registration.setError("Required");
            valid = false;
        } else {
            email_registration.setError(null);
        }

        String phone = phone_registration.getText().toString();
        if (TextUtils.isEmpty(phone)){
            phone_registration.setError("Required");
        } else {
            phone_registration.setError(null);
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


}
