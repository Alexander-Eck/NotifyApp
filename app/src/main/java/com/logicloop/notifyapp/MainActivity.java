package com.logicloop.notifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION1_ID = 755;
    public static final int NOTIFICATION2_ID = 777;

    private NotificationManager notificationManager;
    private Button btn_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create or retrieve notification channel (for API Level 26+)
        String channelId = getString(R.string.order_channel_id);
        CharSequence channelName = getString(R.string.order_channel_name);
        int channelImportance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel notificationChannel =
                new NotificationChannel(channelId, channelName, channelImportance);
        notificationChannel.setDescription(getString(R.string.order_channel_description));
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        // Set up first notification button
        findViewById(R.id.btn_ntfcn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set up main intent for notification (action when user taps notification)
                Intent intent =
                        new Intent(MainActivity.this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                ////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////
                /////////////////////////// BUILDER PATTERN DEMO ///////////////////////////
                // BUTTON 1: Configure and create notification
                Notification notification =
                        new Notification.Builder(
                                MainActivity.this, getString(R.string.order_channel_id))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle(getString(R.string.ntfcn1_title))
                        .setContentText(getString(R.string.ntfcn1_text))

                        .build();
                /////////////////////////// BUILDER PATTERN DEMO ///////////////////////////
                ////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////

                // Send notification to OS
                notificationManager.notify(NOTIFICATION1_ID, notification);
            }
        });

        // Set up second notification button
        findViewById(R.id.btn_ntfcn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set up main intent for notification (action when user taps notification)
                Intent intent =
                        new Intent(MainActivity.this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                // Set up secondary intent - DISMISS
                Intent dismissIntent =
                        new Intent(MainActivity.this, NotifyIntentService.class);
                dismissIntent.setAction(NotifyIntentService.ACTION_DISMISS);
                PendingIntent dismissPendingIntent = PendingIntent.getService(
                        MainActivity.this,
                        0,
                        dismissIntent,
                        0
                );
                Notification.Action dismissAction = new Notification.Action.Builder(
                        Icon.createWithResource(
                                MainActivity.this, R.drawable.ic_baseline_clear_24),
                        getString(R.string.ntfcn2_action_dismiss), dismissPendingIntent)
                        .build();

                // Set up secondary intent - SNOOZE
                Intent snoozeIntent =
                        new Intent(MainActivity.this, NotifyIntentService.class);
                snoozeIntent.setAction(NotifyIntentService.ACTION_SNOOZE);
                PendingIntent snoozePendingIntent = PendingIntent.getService(
                        MainActivity.this,
                        0,
                        snoozeIntent,
                        0
                );
                Notification.Action snoozeAction = new Notification.Action.Builder(
                        Icon.createWithResource(
                                MainActivity.this, R.drawable.ic_baseline_snooze_24),
                        getString(R.string.ntfcn2_action_snooze), snoozePendingIntent)
                        .build();

                ////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////
                /////////////////////////// BUILDER PATTERN DEMO ///////////////////////////
                // BUTTON 2: Configure and create notification
                Notification notification2 =
                        new Notification.Builder(
                                MainActivity.this, getString(R.string.order_channel_id))
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true)
                                .setSmallIcon(R.drawable.ic_baseline_alarm_24)
                                .setLargeIcon(Icon.createWithResource(
                                        MainActivity.this, R.drawable.ic_stat_name)
                                        .setTint(getColor(R.color.secondary_500)))
                                .setColor(getColor(R.color.secondary_500))
                                .setShowWhen(true)
                                .setSubText(getString(R.string.ntfcn2_sub_text))
                                .setContentTitle(getString(R.string.ntfcn2_title))
                                .setContentText(getString(R.string.ntfcn2_text))
                                .setStyle(new Notification.BigTextStyle().
                                        bigText(getString(R.string.ntfcn2_big_text)))
                                .setActions(dismissAction, snoozeAction)

                                .build();
                /////////////////////////// BUILDER PATTERN DEMO ///////////////////////////
                ////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////

                // Send notification to OS
                notificationManager.notify(NOTIFICATION2_ID, notification2);
            }
        });
    }
}