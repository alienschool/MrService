package com.example.news.mrservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocalService extends Service {
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    private Handler handler;

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
        handler=new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllMechanics();
            }
        },1000);
        return mGenerator.nextInt(100);
    }

    public Runnable runnable=new Runnable() {
        @Override
        public void run() {
            mGenerator.nextInt(100);

        }
    };
    private void AllMechanics(){
        APIMyInterface apiInterface= APIClient.getApiClient().create(APIMyInterface.class);
        Call<String> call=apiInterface.MechanicsNearBy("73.23","30.32");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String c=response.body();
                //Toast.makeText(mContext, "Server response: "+c, Toast.LENGTH_LONG).show();

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Toast.makeText(mContext, "Fail "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}