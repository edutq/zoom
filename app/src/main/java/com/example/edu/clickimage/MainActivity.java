package com.example.edu.clickimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.Toast;
import android.content.Intent;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;


public class MainActivity extends AppCompatActivity implements OnClickableAreaClickedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView macroterreno =  (ImageView) findViewById (R.id.image);
        macroterreno.setImageResource(R.drawable.foto);

        ClickableAreasImage clickableImage = new ClickableAreasImage(new PhotoViewAttacher(macroterreno), this);

        List<ClickableArea> areas = new ArrayList<>();

        //areas.add(new ClickableArea(0, 0, 500, 500, new Character('H')));

        areas.add(new ClickableArea(144, 123, 188-144, 176-123, new Character('C')));
        areas.add(new ClickableArea(136, 180, 180-136, 200-180, new Character('C')));
        areas.add(new ClickableArea(133, 201, 178-133, 222-201, new Character('C')));
        areas.add(new ClickableArea(129, 223, 176-129, 245-223, new Character('C')));
        areas.add(new ClickableArea(125, 249, 172-125, 268-249, new Character('C')));


        clickableImage.setClickableAreas(areas);
    }


    public void verTerreno() {
        Intent intent = new Intent(this, Terreno.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onClickableAreaTouched(Object item) {

        Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show();
        verTerreno();
    }

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    } // end toast
}
