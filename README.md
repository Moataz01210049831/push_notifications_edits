# push_notifications_edits


code should add in app/manifest

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  package="sa.com.SP">
  <uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />


    <application
        tools:replace="android:allowBackup"
        android:requestLegacyExternalStorage="true"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <activity
            android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale|smallestScreenSize|screenLayout|uiMode"
            android:name="com.splonline.services.MainActivity"
            android:label="@string/title_activity_main"
            android:theme="@style/AppTheme.NoActionBar"
            android:launchMode="singleTask"
            android:screenOrientation="portrait"
            android:exported="true"

             >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

          <intent-filter>
            <data android:scheme="splonline" />
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT"/>
            <category android:name="android.intent.category.BROWSABLE" />
             <!-- or dev.localhost.ionic -->
          </intent-filter>
        </activity>

        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths"></meta-data>
          <meta-data android:name="com.google.firebase.messaging.default_notification_channel_id" android:value="@string/default_notification_channel_id" />
          <meta-data
            android:name="com.google.firebase.messaging.default_notification_channel_id"
            android:value="@string/default_notification_channel_id" />
        </provider>
    </application>
  <queries>
    <intent>
      <action android:name=
        "android.support.customtabs.action.CustomTabsService" />
    </intent>
  </queries>
    <!-- Permissions -->

    <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.ACCESS_DOWNLOAD_MANAGER"/>
  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
  <uses-feature android:name="android.hardware.location.gps" />
</manifest>
and code add for plugins manifest

<?xml version='1.0' encoding='utf-8'?>
<manifest package="capacitor.android.plugins"
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:amazon="http://schemas.amazon.com/apk/res/android">
<application  >
<activity android:name="com.adobe.phonegap.push.PushHandlerActivity" android:exported="true" android:permission="${applicationId}.permission.PushHandlerActivity"/>
<activity android:name="com.adobe.phonegap.push.BackgroundHandlerActivity" android:exported="true" android:permission="${applicationId}.permission.BackgroundHandlerActivity">
  <intent-filter>
    <action android:name="com.adobe.phonegap.push.background.MESSAGING_EVENT"/>
    <category android:name="android.intent.category.DEFAULT"/>
  </intent-filter>
</activity>
<receiver android:name="com.adobe.phonegap.push.BackgroundActionButtonHandler"/>
<receiver android:name="com.adobe.phonegap.push.PushDismissedHandler"/>
<service android:name="com.adobe.phonegap.push.FCMService" android:exported="true">
  <intent-filter>
    <action android:name="com.google.firebase.MESSAGING_EVENT"/>
  </intent-filter>
</service>
<service android:name="com.adobe.phonegap.push.PushInstanceIDListenerService" android:exported="true">
  <intent-filter>
    <action android:name="com.google.firebase.INSTANCE_ID_EVENT"/>
  </intent-filter>
</service>
<receiver android:name="com.google.android.gms.analytics.AnalyticsReceiver" android:enabled="true" android:exported="false">
  <intent-filter>
    <action android:name="com.google.android.gms.analytics.ANALYTICS_DISPATCH"/>
  </intent-filter>
</receiver>
<service android:name="com.google.android.gms.analytics.AnalyticsService" android:enabled="true" android:exported="false"/>
<meta-data android:name="launchnavigator.GOOGLE_API_KEY" android:value="undefined"/>
</application>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.WAKE_LOCK"/>
<uses-permission android:name="android.permission.VIBRATE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<queries>
  <intent>
    <action android:name="android.intent.action.VIEW"/>
    <data android:scheme="geo"/>
  </intent>
</queries>
</manifest>
and this in strings


 <string name="default_notification_channel_id">spl.com.sa</string>




