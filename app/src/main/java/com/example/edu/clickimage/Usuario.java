package com.example.edu.clickimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
    }

    public void comprar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void terrenos(View view){

        Intent intent = new Intent(this, Terrenos_usuarios.class);
        startActivity(intent);
    }
}
