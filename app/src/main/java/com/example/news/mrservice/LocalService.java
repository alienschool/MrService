package com.example.news.mrservice;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
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
    /*public int getRandomNumber() {
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
    };*/
    public void TestService(){
        APIMyInterface apiInterface= APIClient.getApiClient().create(APIMyInterface.class);
        //calling php file from here. php will return success
        Call<String> call=apiInterface.testingPhp("test");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String c=response.body();
                //Toast.makeText(mContext, "Server response: "+c, Toast.LENGTH_LONG).show();
                Log.d("Serverresponse",c);
                if(c.equalsIgnoreCase("success")){
                    Intent intent = new Intent("intentKey");
                    // You can also include some extra data.
                    intent.putExtra("key", c);
                    LocalBroadcastManager.getInstance(LocalService.this).sendBroadcast(intent);
                    /*NotificationCompat.Builder mBuilder =
                            (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                    //.setSmallIcon(R.drawable.navigate_icon)
                                    .setContentTitle("")
                                    .setContentText("notificationmsg");
                    // Creates an explicit intent for an Activity in your app
                    Intent resultIntent = new Intent(this, MapActivity.class);

                    // The stack builder object will contain an artificial back stack for the
                    // started Activity.
                    // This ensures that navigating backward from the Activity leads out of
                    // your application to the Home screen.
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                    // Adds the back stack for the Intent (but not the Intent itself)
                    stackBuilder.addParentStack(MapActivity.class);
                    // Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    // mId allows you to update the notification later on.
                    mNotificationManager.notify(101, mBuilder.build());*/
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Toast.makeText(mContext, "Fail "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}