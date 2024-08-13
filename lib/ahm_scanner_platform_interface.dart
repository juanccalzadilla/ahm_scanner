import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'ahm_scanner_method_channel.dart';

abstract class AhmScannerPlatform extends PlatformInterface {
  /// Constructs a AhmScannerPlatform.
  AhmScannerPlatform() : super(token: _token);

  static final Object _token = Object();

  static AhmScannerPlatform _instance = MethodChannelAhmScanner();

  /// The default instance of [AhmScannerPlatform] to use.
  ///
  /// Defaults to [MethodChannelAhmScanner].
  static AhmScannerPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AhmScannerPlatform] when
  /// they register themselves.
  static set instance(AhmScannerPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

   Stream<String> get onScan {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
