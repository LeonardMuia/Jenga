package com.example.android.jengaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerReg extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.loginText)
    TextView already_registered;
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
    @BindView(R.id.password_confirm)
    EditText password_confirmation;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_registration);
        ButterKnife.bind(this);
        create_account.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        already_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(getApplicationContext(), Login.class);
                startActivity(goToLogin);
            }
        });
    }

    /*
     *  Registration
     * @Param username, email, phone, password
     */

    public void createAccount(String email,String password){
        if (!validateForm()){
            return;
        }
        // showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent goToMain = new Intent(getApplicationContext(), Main.class);
                            startActivity(goToMain);
                        } else {
                            Toast.makeText(CustomerReg.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
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

        String password = password_registration.getText().toString();
        if(TextUtils.isEmpty(password)){
            password_registration.setError("Required");
            valid = false;
        } else {
            password_registration.setError(null);
        }

        String confirm_pass = password_confirmation.getText().toString();
        if(TextUtils.isEmpty(confirm_pass)){
            password_confirmation.setError("Required");
        } else {
            password_confirmation.setError(null);
        }

        if(!password.equals(confirm_pass)){
            password_confirmation.setError("Passwords don\'t match");
        } else {
            password_confirmation.setError(null);
        }
        return valid;
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if(i == R.id.registerBtn){
            createAccount(email_registration.getText().toString(),password_registration.getText().toString());
        }
    }
}
