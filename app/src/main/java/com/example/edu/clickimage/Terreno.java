package com.example.edu.clickimage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import io.conekta.conektasdk.Conekta;
import io.conekta.conektasdk.Card;
import io.conekta.conektasdk.Token;
import org.json.JSONObject;

public class Terreno extends AppCompatActivity {
    private Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terreno);

    }

    /*public void comprar(View view){
        Conekta.setPublicKey("key_eYvWV7gSDkNYXsmr");
        //Conekta.setApiVersion("1.0.0");                       //optional
        Conekta.collectDevice(activity);

        Card card = new Card("Eduardo Tavarez", "5579099007038591", "264", "08", "2019");
        Token token = new Token(activity);

        token.onCreateTokenListener( new Token.CreateToken(){
            @Override
            public void onCreateTokenReady(JSONObject data) {
                try {
                    //Send the id to the webservice.
                } catch (Exception err) {
                    //Do something on error
                }
            }
        });

        token.create(card);
    }*/
}
