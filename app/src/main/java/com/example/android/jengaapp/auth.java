package com.example.android.jengaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class auth extends AppCompatActivity {

    private FirebaseAuth mAuth;

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

    private void signIn(String email, String password){
        if(!validateForm()){
            return;
        }
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
     */

        private void updateUI(FirebaseUser user){
            //hideProgressDialog();
            if(user != null){
                user
            }
        }



    /*
     *  If a user is not registered
     *  Redirect them to the registration form
     */

    @OnClick(R.id.customerRegister)
    public void registerView(){
        setContentView(R.layout.register);
    }

}
