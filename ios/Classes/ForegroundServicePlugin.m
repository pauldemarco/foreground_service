#import "ForegroundServicePlugin.h"

@implementation ForegroundServicePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"com.pauldemarco.foreground_service"
            binaryMessenger:[registrar messenger]];
  ForegroundServicePlugin* instance = [[ForegroundServicePlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  result(FlutterMethodNotImplemented);
}

@end
