import 'package:flutter_test/flutter_test.dart';
import 'package:osim_native_support/osim_native_support.dart';
import 'package:osim_native_support/osim_native_support_method_channel.dart';
import 'package:osim_native_support/osim_native_support_platform_interface.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockOsimNativeSupportPlatform
    with MockPlatformInterfaceMixin
    implements OsimNativeSupportPlatform {
  @override
  Future<bool> sleepSaveUri(String uri) => Future.value(true);
}

void main() {
  final OsimNativeSupportPlatform initialPlatform =
      OsimNativeSupportPlatform.instance;

  test('$MethodChannelOsimNativeSupport is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelOsimNativeSupport>());
  });

  test('getPlatformVersion', () async {
    MockOsimNativeSupportPlatform fakePlatform =
        MockOsimNativeSupportPlatform();
    OsimNativeSupportPlatform.instance = fakePlatform;

    expect(await sleepSaveUri('https://www.baidu.com'), true);
  });
}
