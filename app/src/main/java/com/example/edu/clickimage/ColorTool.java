package com.example.edu.clickimage;

import android.graphics.Color;
import android.util.Log;

/**
 * A class with methods to help with colors.
 * (Only one method so far.)
 * 
 */

public class ColorTool {

    /**
     * Return true if the two colors are a pretty good match.
     * To be a good match, all three color values (RGB) must be within the tolerance value given.
     *
     * @param c1    Color
     * @param c2    Color
     * @param tolerance int - the max difference that is allowed for any of the RGB components
     * @return boolean
     */

    public boolean closeMatch(int c1, int c2, int tolerance) {

        double distance = Math.pow((Color.red(c2) - Color.red(c1)),2) + Math.pow((Color.green(c2) - Color.green(c1)),2) + Math.pow((Color.blue(c2) - Color.blue(c1)),2);
        //Log.i("tono rojo", String.valueOf(Color.red(c1)));
        //Log.i(" rojodelotro", String.valueOf(Color.red(c2)));
        double sqrtdis = Math.sqrt(distance);
        //Log.i("Disntancia", String.valueOf(sqrtdis));
        if (sqrtdis < tolerance) {
            return true;
        }
        else {
            return false;
        }
    } // end class
}