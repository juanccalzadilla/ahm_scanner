import 'package:flutter/material.dart';
import 'package:ahm_scanner/ahm_scanner.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  final _ahmScannerPlugin = AhmScanner();

  @override
  void initState() {
    super.initState();
    _ahmScannerPlugin.onScan.listen((event) {
      print('Scanned: $event');
    });
  }
  
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: const Center(
          child: Text('Running'),
        ),
      ),
    );
  }
}
