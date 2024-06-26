import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'osim_native_support_platform_interface.dart';

/// An implementation of [OsimNativeSupportPlatform] that uses method channels.
class MethodChannelOsimNativeSupport extends OsimNativeSupportPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('osim_native_support');

  @override
  Future<bool> sleepSaveUri(String uri) async {
    return await methodChannel.invokeMethod('sleepSaveUri', uri);
  }
}
