package com.example.android.jengaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Registration extends AppCompatActivity {

    @BindView(R.id.customer_button)
    Button customer_register;
    @BindView(R.id.vendor_button)
    Button vendor_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        ButterKnife.bind(this);

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
}

