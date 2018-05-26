package com.company.batman.firebasetest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Batman on 3/1/2018.
 */

public class FirebaseService extends FirebaseMessagingService {

public static String cntent="";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage.getNotification().getBody());
    }

public void sendNotification(String message)
{
    Intent intent=new Intent(FirebaseService.this,Main2Activity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
    Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
    builder.setSmallIcon(R.mipmap.ic_launcher_notification);
    builder.setContentTitle("Leaf");
    builder.setContentText(message);
    builder.setAutoCancel(true);
    builder.setSound(uri);
    builder.setContentIntent(pendingIntent);
    NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    manager.notify(0,builder.build());

    cntent=message;

}



}
