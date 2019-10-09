package com.example.android.jengaapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
        loginView();
    }

    @OnClick(R.id.loginText)
    public void loginView(){
        setContentView(R.layout.login);
    }
}
