# NotifyApp
Android App that showcases Notification.Builder() - requires API Level 26+

> This app was created with :heart: for a lecture at HWR Berlin, 22 June 2021.
> It's only purpose is to demonstrate the use of Notification.Builder().
> This is not production code.

The most important lines of code are
[MainActivity.java#L65](https://github.com/Alexander-Eck/NotifyApp/blob/08a501aa2913e10a19c3a649f490683c4ad48da0/app/src/main/java/com/logicloop/notifyapp/MainActivity.java#L65)
:
```java
Notification notification =
  new Notification.Builder(
    MainActivity.this, getString(R.string.order_channel_id))
  .setContentIntent(pendingIntent)
  .setAutoCancel(true)
  .setSmallIcon(R.drawable.ic_baseline_message_24)
  .setContentTitle(getString(R.string.ntfcn1_title))
  .setContentText(getString(R.string.ntfcn1_text))
.build();
```

...and
[MainActivity.java#L135](https://github.com/Alexander-Eck/NotifyApp/blob/08a501aa2913e10a19c3a649f490683c4ad48da0/app/src/main/java/com/logicloop/notifyapp/MainActivity.java#L135)
:
```java
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
```

## How to use

>Optional: If not done already, link Android Studio with your GitHub account:
>* File ->
>* Settings ->
>* Version Control ->
>* GitHub ->
>* + ->
>* Log in with Token ->
>* Generate ->
>* A Browser opens up; type in your GitHub credentials to proceed ->
>* Generate token ->
>* Copy token (shows only once!) ->
>* Paste into Android Studio Window ->
>* Add Account

Import this repositoriy into Android Studio:
* File ->
* New ->
* Project from Version Control

Tested with:
- **IDE:** Android Studio 4.2.1
- **Emulator:** Pixel_3a_API_30_x86
