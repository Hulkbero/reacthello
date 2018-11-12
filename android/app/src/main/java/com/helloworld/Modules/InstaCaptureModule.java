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
                
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm", Locale.getDefault());
                String fileName = "InstaCapture-" + dateFormat.format(new Date()) + ".png";
              
                File sd = new File(Environment.getExternalStorageDirectory()
                + "/Pictures/InstaBug/"
                ); 
                try{
                    sd.mkdirs();
                }catch (Exception e) {
                    Log.v("ReactNative", e.toString());
                }
               
                File dest = new File(sd, fileName);
                Log.v("ReactNative", dest.getAbsolutePath());
             
                try {
                     FileOutputStream out = new FileOutputStream(dest);
                     bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                     out.flush();
                     out.close();
                } catch (Exception e) {
                    Log.v("ReactNative", e.toString());
                }
           
            }
        });
    }

   

    
}