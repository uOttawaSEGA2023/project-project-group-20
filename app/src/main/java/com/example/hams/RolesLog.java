package com.example.hams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RolesLog extends AppCompatActivity {

    CardView patientCard, doctorCard, adminCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_roles_log);
        //defining the cards (from dashboards.xml)
        patientCard = findViewById(R.id.patient);
        doctorCard = findViewById(R.id.doctor);
        adminCard = findViewById(R.id.admin);

        patientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RolesLog.this, LoginPat.class));
            }
        });
        doctorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RolesLog.this, LoginDoc.class));
            }
        });
        adminCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RolesLog.this, LoginAdmin.class));
            }
        });
    }
}