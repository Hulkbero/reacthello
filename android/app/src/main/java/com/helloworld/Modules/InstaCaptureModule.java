package com.helloworld;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;

import android.os.Environment;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.app.Activity;

import com.tarek360.instacapture.Instacapture;
import com.tarek360.instacapture.listener.SimpleScreenCapturingListener;
import com.tarek360.instacapture.utility.Logger;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class InstaCaptureModule extends ReactContextBaseJavaModule{
    private final ReactApplicationContext reactContext;
    public InstaCaptureModule(ReactApplicationContext reactContext){
        super(reactContext);
        this.reactContext=reactContext;
    }
    @Override
    public String getName(){
        return "InstaCapture";
    }

    @ReactMethod
    public void capturescreen(){

        
       
        Instacapture.capture(getCurrentActivity(), new SimpleScreenCapturingListener() {
            @Override
            public void onCaptureComplete(Bitmap bitmap) {
                String filename = "tt.png";
                File sd = new File(Environment.getExternalStorageDirectory()
                + "/Pictures/InstaBug/"
                ); 
                try{
                    sd.mkdirs();
                }catch (Exception e) {
                    Log.v("ReactNative", e.toString());
                }
               
                File dest = new File(sd, filename);
                Log.v("ReactNative", dest.getAbsolutePath());
                //Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                try {
                     FileOutputStream out = new FileOutputStream(dest);
                     bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                     out.flush();
                     out.close();
                } catch (Exception e) {
                    Log.v("ReactNative", e.toString());
                }
                //saveBitmapToFile(getReactApplicationContext(),bitmap).getAbsolutePath();
            }
        });
    }

    private static File getScreenshotFile(@NonNull final Context applicationContext) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss.SS", Locale.getDefault());

        String fileName = "screenshot-" + dateFormat.format(new Date()) + ".jpg";

        final File screenshotsDir =
                new File(applicationContext.getDir("imageDir", Context.MODE_PRIVATE), "Instabug");
        screenshotsDir.mkdirs();
        return new File(screenshotsDir, fileName);
    }


    public static File saveBitmapToFile(@NonNull final Context context,
    @NonNull final Bitmap bitmap) {

        OutputStream outputStream = null;
        File screenshotFile = getScreenshotFile(context);

        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(screenshotFile));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, outputStream);

            outputStream.flush();
            Log.v("ReactNative", "e.getMessage()");
            Log.v("ReactNative","Screenshot saved to " + screenshotFile.getAbsolutePath());
        } catch (final IOException e) {
            Log.v("ReactNative", e.getMessage());
            
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (final IOException ignored) {
                    Log.v("ReactNative","Failed to close OutputStream.");
                }
            }
        }
    return screenshotFile;
    }

    
}