package com.nicolappli.mynews.Utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
import com.nicolappli.mynews.Controllers.Activities.NotificationsActivity;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import static android.support.constraint.Constraints.TAG;

public class MyAlarmReceiver extends BroadcastReceiver {

    private Util mUtil = new Util();
    private NotificationsActivity mNotificationsActivity =new NotificationsActivity();

    //for data
    public static final String CHANNEL_ID = "channel";
    private Disposable mDisposable;
    private String httpResult = "";
    private int nbrArticles;
    private String[] dataNotification;
    private String begin_date;

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        dataNotification = mNotificationsActivity.loadData(context);
        begin_date=mUtil.setCurrentDate();

        executeNotificationHttpRequest(context);
    }

    //http request for the notifications
    private void executeNotificationHttpRequest(final Context context){
        this.mDisposable=NYTStreams.streamFetchNotification(dataNotification[0],"news_desk:(" + dataNotification[1] + ")", begin_date).subscribeWith(new DisposableObserver<NewYorkTimesAPI>(){
            @Override
            public void onNext(NewYorkTimesAPI newYorkTimesAPI) {
                if(newYorkTimesAPI.getResponse().getDocs().size()!=0){
                    nbrArticles=newYorkTimesAPI.getResponse().getDocs().size();
                    httpResult=newYorkTimesAPI.getResponse().getDocs().get(0).getPubDate();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("SearchResult Tag", "On Error" + Log.getStackTraceString(e).toUpperCase());
            }

            @Override
            public void onComplete() {
                if(!httpResult.equals("") && mUtil.parseDateForNotifications(httpResult).equals(mUtil.setCurrentDate())){
                    sendNotification(context, nbrArticles);
                }
            }
        });
    }

    private void sendNotification(Context context, int nbrArticles){
        //create the builder for notification
        Notification mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("My News")
                .setContentText(nbrArticles + " new available article(s).")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //create notification channel but only for API 26+ (not in the support library
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Message";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        assert notificationManager != null;
        notificationManager.notify(1, mBuilder);
    }

    private void disposeWhenDestroy(){
        if (this.mDisposable!=null && !this.mDisposable.isDisposed()){
            this.mDisposable.dispose();
        }
        this.onDestroy();
    }

    //called for better performances
    public void onDestroy(){
        this.disposeWhenDestroy();
    }
}