package com.example.appprote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void irMapa(View view) {
        Intent intent = new Intent(InfoActivity.this, MapaActivity.class);
        startActivity(intent);
    }

}