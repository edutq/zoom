package com.example.edu.clickimage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class UsuarioActivity extends AppCompatActivity {

    ImageView im;

    SharedPreferences sharedpreferences;
    public static final String PREFERENCES = "USERPREFERENCES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        sharedpreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);


        im = findViewById(R.id.settings);
        im.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final PopupMenu popup = new PopupMenu(UsuarioActivity.this, im);
                popup.getMenuInflater().inflate(R.menu.popup_settings, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String[] id = getResources().getResourceName(item.getItemId()).split("\\/");
                        if(id[1].equals("logout")) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.clear();
                            editor.commit();
                            gotologin();

                        }
                        return true;
                    }
                });

                popup.show();
            }
        });


        if( sharedpreferences.contains("id") ) {
            // do nothing
        }
        else {
            gotologin();
        }
    }

    public void comprar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void gotologin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void terrenos(View view){

        Intent intent = new Intent(this, Terrenos_usuarios.class);
        startActivity(intent);
    }

    public void perfil(View view) {

        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);

    }
}
