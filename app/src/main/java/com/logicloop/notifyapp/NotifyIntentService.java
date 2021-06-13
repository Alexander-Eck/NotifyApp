package com.logicloop.notifyapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class NotifyIntentService extends IntentService {

    public static final String ACTION_DISMISS = "com.logicloop.notifyapp.action.DISMISS";
    public static final String ACTION_SNOOZE = "com.logicloop.notifyapp.action.SNOOZE";

    private static final long SNOOZE_TIME = TimeUnit.SECONDS.toMillis(5);

    public NotifyIntentService() {
        super("NotifyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DISMISS.equals(action)) {
                handleActionDismiss();
            } else if (ACTION_SNOOZE.equals(action)) {
                handleActionSnooze();
            }
        }
    }

    private void handleActionDismiss() {
        // Dismiss the notification
        // Not meant for productive use - e.g., MainActivity.NOTIFICATION2_ID is hard-coded
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.NOTIFICATION2_ID);
    }

    private void handleActionSnooze() {
        // Snooze the notification for SNOOZE_TIME seconds.
        // This method is not for productive use, since it will always fetch the latest notification
        // Also, MainActivity.NOTIFICATION2_ID is hard-coded
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification =
                Arrays.stream(notificationManager.getActiveNotifications())
                        .findFirst()
                        .get()
                        .getNotification()
                        .clone();
        notificationManager.cancel(MainActivity.NOTIFICATION2_ID);
        try {
            Thread.sleep(SNOOZE_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notificationManager.notify(MainActivity.NOTIFICATION2_ID, notification);
    }

}