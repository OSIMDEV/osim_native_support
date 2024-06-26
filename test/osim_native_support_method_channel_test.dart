import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:osim_native_support/osim_native_support_method_channel.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  MethodChannelOsimNativeSupport platform = MethodChannelOsimNativeSupport();
  const MethodChannel channel = MethodChannel('osim_native_support');

  setUp(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger
        .setMockMethodCallHandler(
      channel,
      (MethodCall methodCall) async {
        return '42';
      },
    );
  });

  tearDown(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger
        .setMockMethodCallHandler(channel, null);
  });

  test('sleepSaveUri', () async {
    expect(await platform.sleepSaveUri('https://www.baidu.com'), true);
  });
}
