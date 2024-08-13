import 'package:flutter/services.dart';

import 'ahm_scanner_platform_interface.dart';

/// An implementation of [AhmScannerPlatform] that uses method channels.
class MethodChannelAhmScanner extends AhmScannerPlatform {
  /// The method channel used to interact with the native platform.
  final _eventChannel =
      const EventChannel('com.ahorramas.plugins/global_scanner');

  @override
  Stream<String> get onScan {
    return _eventChannel
        .receiveBroadcastStream()
        .map((event) => event.toString());
  }
}
