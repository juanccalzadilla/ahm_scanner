import 'ahm_scanner_platform_interface.dart';

class AhmScanner {
    Stream<String> get onScan {
    return AhmScannerPlatform.instance.onScan;
  }
}
