package com.example.edu.clickimage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.api.client.json.JsonObjectParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class Registar extends AppCompatActivity {


    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    SharedPreferences sharedpreferences;
    public static final String PREFERENCES = "USERPREFERENCES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        mDisplayDate = findViewById(R.id.fecha_nacimiento);
        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Registar.this,
                        android.R.style.Theme_Holo_Dialog_NoActionBar,
                        mDateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }

    public void access(){
        Intent intent = new Intent(this, UsuarioActivity.class);
        startActivity(intent);
        finish();
    }

    public void registrar(View view) {

        AutoCompleteTextView nombre = findViewById(R.id.nombre);
        AutoCompleteTextView apellidop = findViewById(R.id.apellido);
        AutoCompleteTextView apellidom = findViewById(R.id.apellido_m);
        AutoCompleteTextView correo = findViewById(R.id.correo);
        AutoCompleteTextView password = findViewById(R.id.password);
        AutoCompleteTextView passver = findViewById(R.id.verify_password);
        TextView fecha = findViewById(R.id.fecha_nacimiento);

        boolean flag = true;

        if(nombre.getText().equals("")){
            flag = false;
        }
        if(apellidop.getText().equals("")){
            flag = false;
        }
        if(apellidom.getText().equals("")){
            flag = false;
        }
        if(correo.getText().equals("")){
            flag = false;
        }
        if(password.getText().equals("")){
            flag = false;
        }
        if(passver.getText().equals("")){
            flag = false;
        }
        if(fecha.getText().equals("")){
            flag = false;
        }

        if(flag) {

            //send request
            final RequestQueue requestQueue = Volley.newRequestQueue(this);

            JSONObject usuario = new JSONObject();
            try {
                usuario.put("nombre", nombre.getText());
                usuario.put("apellido_p", apellidop.getText());
                usuario.put("apellido_m", apellidom.getText());
                usuario.put("correo", correo.getText());
                usuario.put("password", password.getText());
            }
            catch (Exception e) {

            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            String URL = "http://192.168.0.27:3000/user/create";

            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    URL,
                    usuario,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {

                            try{
                                final Boolean error = response.getBoolean("error");
                                builder.setMessage(response.get("mensaje").toString());
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        if (error){
                                            // do nothing
                                        }
                                        else {
                                            sharedpreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            try {
                                                editor.putInt("id", response.getInt("id"));
                                                editor.commit();
                                                access();
                                            }catch (Exception e){

                                            }


                                        }
                                    }
                                });

                            }
                            catch (Exception e){

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            builder.setMessage(error.toString());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
            );

            requestQueue.add(objectRequest);


        }

    }
}
