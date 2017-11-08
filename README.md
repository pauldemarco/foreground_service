# ForegroundService for Flutter

This plugin allows Flutter apps to launch a generic foreground service when the platform
is Android. If the plugin is invoked on iOS, it will crash your app. In checked
mode, we assert that the platform should be Android.

Use it by calling the start and stop methods.

Start service with:
```
ForegroundService.start(
  title: 'Title',
  text: 'Text',
  subText: 'Subtext',
  ticker: 'Ticker',
);
```

Stop service with:
```
ForegroundService.stop();
```

## Requirements

Add the Service to your `AndroidManifest.xml`:
```
<service android:name="com.pauldemarco.foregroundservice.ForegroundService" >
</service>
```

## Getting Started

For help getting started with Flutter, view our online
[documentation](http://flutter.io/).

For help on editing plugin code, view the [documentation](https://flutter.io/platform-plugins/#edit-code).
