package com.example.altasmontaas;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "MyChannel";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            String title = remoteMessage.getData().get("title");
            String description = remoteMessage.getData().get("description");
            String imageUrl = remoteMessage.getData().get("imageUrl");

            if (title != null && description != null && imageUrl != null) {
                showNotification(title, description, imageUrl);
            }
        }
    }

    private void showNotification(String title, String description, String imageUrl) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Crea un canal de notificación para versiones de Android Oreo y superiores
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, SitiosCosco.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = getBitmapFromUrl(imageUrl);

        // Construye la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.feriacafe)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);

        // Muestra la notificación
        notificationManager.notify(0, builder.build());
    }

    private Bitmap getBitmapFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
