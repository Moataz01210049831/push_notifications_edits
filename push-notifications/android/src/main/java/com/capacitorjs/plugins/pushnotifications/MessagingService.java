package com.capacitorjs.plugins.pushnotifications;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.media.RingtoneManager;
import android.util.Log;
import android.content.SharedPreferences;
import android.content.Context;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.getcapacitor.JSObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.File;
import java.util.Locale;

public class MessagingService extends FirebaseMessagingService {

  @Override
  public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

    super.onMessageReceived(remoteMessage);
    SharedPreferences sharedPref = getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE);

    String keyLang = sharedPref.getString("keyLang", "ar");

    Log.d("myMessage: ", String.valueOf(sharedPref.getAll()));
    Log.d("keyLang: ", String.valueOf(keyLang));


    if (keyLang.equals("ar")) {
//      Log.d("myMessage: ", String.valueOf(remoteMessage.getData()));

      String bodyAr = remoteMessage.getData().get("bodyAr");
      String titleAr = remoteMessage.getData().get("titleAr");

      sendNotification(bodyAr,titleAr,remoteMessage);
    }else{
      String bodyEn = remoteMessage.getData().get("bodyEn");
      String titleEn = remoteMessage.getData().get("titleEn");

      sendNotification(bodyEn,titleEn,remoteMessage);
    }
//        PushNotificationsPlugin.sendRemoteMessage(remoteMessage);
  }

  @Override
  public void onNewToken(@NonNull String s) {
    super.onNewToken(s);
    FirebaseMessaging.getInstance().subscribeToTopic("all_users_android_TEST").addOnCompleteListener(new OnCompleteListener<Void>() {
      @Override
      public void onComplete(@NonNull Task<Void> task) {
        String msg = "Subscribed";
        if (!task.isSuccessful()) {
          msg = "Subscribe failed";
        }
        Log.d("Subscribe Success", msg);
      }
    });
    PushNotificationsPlugin.onNewToken(s);
  }

  private void sendNotification(String messageBody,String messageTitle,RemoteMessage remoteMessage) {
    JSObject remoteMessageData = new JSObject();
    JSObject dataHoldingTheRemoteMessageKeys = new JSObject();
    Log.d("myMessage51: ", String.valueOf(remoteMessage.getMessageId()));
    remoteMessageData.put("id",  remoteMessage.getMessageId());
    for (String key : remoteMessage.getData().keySet()) {
      Object value = remoteMessage.getData().get(key);
      dataHoldingTheRemoteMessageKeys.put(key, value);
    }
    remoteMessageData.put("data", dataHoldingTheRemoteMessageKeys);


    Intent intent = null;
    PendingIntent pendingIntent = null;
    Bundle data = new Bundle();
    data.putString("notification" , remoteMessageData.toString());

    try {
      intent = new Intent(this,
        Class.forName("com.splonline.services.MainActivity"));
      Log.d("myMessage50: ", String.valueOf(data));
      intent.putExtra("notification" , data);
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent,PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
//      PendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

//      startActivity(intent);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


    Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//    Log.d("myMessage2: ", String.valueOf(defaultSoundUri));
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "spl.sa.com").setSmallIcon(R.drawable.ic_launcher)
      .setContentTitle(messageTitle)
      .setContentText(messageBody)
      .setAutoCancel(true)
      .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
      .setContentIntent(pendingIntent);




    NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


// === Removed some obsoletes
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
    {
      String channelId = "spl.com.sa";
      NotificationChannel channel = new NotificationChannel(
        channelId,
        "spl_channel",
        NotificationManager.IMPORTANCE_HIGH);
      mNotificationManager.createNotificationChannel(channel);
      mBuilder.setChannelId(channelId);
    }

    mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
//    Log.d("myMessage3: ", String.valueOf(mNotificationManager));
//    mNotificationManager.notify(342342423 /* ID of notification */, mBuilder.build());
  }
}
