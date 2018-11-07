package com.helloworld;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.app.Activity;

import com.tarek360.instacapture.Instacapture;
import com.tarek360.instacapture.listener.SimpleScreenCapturingListener;

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
    public void capturescreen(final Callback callback){

        
       
        Instacapture.getInstance(this).capture(getCurrentActivity(), new SimpleScreenCapturingListener() {
            @Override
            public void onCaptureComplete(Bitmap bitmap) {
                //callback.invoke(FileUtility.saveBitmapToFile(getReactApplicationContext(),bitmap).getAbsolutePath());
            }
        });
    }

    
}