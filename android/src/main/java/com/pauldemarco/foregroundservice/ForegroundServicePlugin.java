package com.pauldemarco.foregroundservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * ForegroundServicePlugin
 */
public class ForegroundServicePlugin implements MethodCallHandler {
  private static final String TAG = "ForegroundServicePlugin";
  public static String STARTFOREGROUND_ACTION = "com.pauldemarco.foregroundservice.action.startforeground";
  public static String STOPFOREGROUND_ACTION = "com.pauldemarco.foregroundservice.action.stopforeground";

  private final Activity activity;

  public ForegroundServicePlugin(Activity activity) {
    this.activity = activity;
  }

  /**
   * Plugin registration.
   */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.pauldemarco.foreground_service");
    channel.setMethodCallHandler(new ForegroundServicePlugin(registrar.activity()));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("start")) {
      Intent intent = new Intent(activity, ForegroundService.class);
      intent.setAction(STARTFOREGROUND_ACTION);
      intent.putExtra("title", (String)call.argument("title"));
      intent.putExtra("text", (String)call.argument("text"));
      intent.putExtra("subText", (String)call.argument("subText"));
      intent.putExtra("ticker", (String)call.argument("ticker"));
      activity.startService(intent);
      result.success(null);
    } else if (call.method.equals("stop")) {
      Intent intent = new Intent(activity, ForegroundService.class);
      intent.setAction(STOPFOREGROUND_ACTION);
      activity.stopService(intent);
      result.success(null);
    } else {
      result.notImplemented();
    }
  }
}
