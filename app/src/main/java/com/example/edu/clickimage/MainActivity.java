package com.example.edu.clickimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv =  findViewById (R.id.image);
        final ImageView iv2 = findViewById(R.id.image_areas);

        PhotoViewAttacher photoView = new PhotoViewAttacher(iv);
        photoView.update();

        if (iv != null) {
            photoView.setOnViewTapListener(new OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    int touchColor = getHotspotColor (R.id.image_areas, (int) x, (int) y, iv2);
                    Log.i ("RGB calculado", String.valueOf(Color.red(touchColor)) +" "+String.valueOf(Color.green(touchColor)) + " " + String.valueOf(Color.blue(touchColor)));
                    // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
                    // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
                    // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
                    // varying pixel density.
                    ColorTool ct = new ColorTool ();
                    int tolerance = 20;
                    //Log.i ("color encontrado", String.valueOf(touchColor));
                    //Log.i ("color calculado", String.valueOf(Color.parseColor("#FFFFFF")));
                    if (ct.closeMatch (Color.BLACK, touchColor, tolerance)) toast("le diste al negro");
                    else if (ct.closeMatch (Color.parseColor("#ED1C24"), touchColor, tolerance)) toast("le diste al rojo");
                    else if (ct.closeMatch (Color.parseColor("#FFF200"), touchColor, tolerance)) toast("le diste al amarillo");

                    Log.i ("posicion", String.valueOf(x) + " " + String.valueOf(y));
                }
            });
        }

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
