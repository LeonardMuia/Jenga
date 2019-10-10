package com.example.android.jengaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Registration extends AppCompatActivity {

    @BindView(R.id.customer_button)
    Button customer_register;
    @BindView(R.id.vendor_button)
    Button vendor_register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        customer_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCustomerReg = new Intent(getApplicationContext(),CustomerReg.class);
                startActivity(goToCustomerReg);
            }
        });
        vendor_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToVendorReg = new Intent(getApplicationContext(),VendorReg.class);
                startActivity(goToVendorReg);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /*
     *  Update User Interface
     *  If user is logged in, redirect to main activity
     *  Else show login activity
     */

    private void updateUI(FirebaseUser user){
        //hideProgressDialog();
        if(user != null){
            setContentView(R.layout.products);
        }
    }
}

