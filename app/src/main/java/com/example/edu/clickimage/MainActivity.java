package com.example.edu.clickimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener
{
    PhotoViewAttacher photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZoomView zoom = (ZoomView)findViewById(R.id.zoomView);

        RelativeLayout lo = (RelativeLayout) findViewById(R.id.layo);
        ImageView iv =  findViewById (R.id.image);

        if(iv != null) {
            iv.setOnTouchListener(this);
        }


    }

    public boolean onTouch (View v, MotionEvent ev)
    {
        ImageView iv2 = findViewById(R.id.image_areas);

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        int touchColor = getHotspotColor (R.id.image_areas, evX, evY, iv2);
        Log.i ("RGB calculado", String.valueOf(Color.red(touchColor)) +" "+String.valueOf(Color.green(touchColor)) + " " + String.valueOf(Color.blue(touchColor)));
        // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
        // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
        // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
        // varying pixel density.
        ColorTool ct = new ColorTool ();
        int tolerance = 20;

        if (ct.closeMatch (Color.BLACK, touchColor, tolerance)) {
            //toast("le diste al negro");
            ImageView viewnegro = (ImageView) findViewById(R.id.fotonegro);

            viewnegro.setVisibility(View.VISIBLE);
        }
        else if (ct.closeMatch (Color.parseColor("#ED1C24"), touchColor, tolerance)) {
            ImageView viewrojo = (ImageView) findViewById(R.id.fotorojo);

            viewrojo.setVisibility(View.VISIBLE);
            //toast("le diste al rojo");
        }
        else if (ct.closeMatch (Color.parseColor("#FFF200"), touchColor, tolerance)) {
            //toast("le diste al amarillo");
            ImageView viewamarillo = (ImageView) findViewById(R.id.fotoamarillo);

            viewamarillo.setVisibility(View.VISIBLE);
        }

        Log.i ("posicion", String.valueOf(evX) + " " + String.valueOf(evY));

        return true;
    }
    public int getHotspotColor (int hotspotId, int x, int y, ImageView iv) {

        if (iv == null) {
            Log.d ("ImageAreasActivity", "Hot spot image not found");
            return 0;
        } else {

            iv.setDrawingCacheEnabled(true);

            Bitmap hotspots = Bitmap.createBitmap(iv.getDrawingCache());
            if (hotspots == null) {
                Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                iv.setDrawingCacheEnabled(false);


                return hotspots.getPixel(x, y);
            }
        }
    }


    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    } // end toast
}
