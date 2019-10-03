package com.example.android.jengaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.jengaapp.ui.auth.AuthFragment;

public class auth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AuthFragment.newInstance())
                    .commitNow();
        }
    }
}
