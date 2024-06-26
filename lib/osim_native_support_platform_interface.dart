import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'osim_native_support_method_channel.dart';

abstract class OsimNativeSupportPlatform extends PlatformInterface {
  /// Constructs a OsimNativeSupportPlatform.
  OsimNativeSupportPlatform() : super(token: _token);

  static final Object _token = Object();

  static OsimNativeSupportPlatform _instance = MethodChannelOsimNativeSupport();

  /// The default instance of [OsimNativeSupportPlatform] to use.
  ///
  /// Defaults to [MethodChannelOsimNativeSupport].
  static OsimNativeSupportPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [OsimNativeSupportPlatform] when
  /// they register themselves.
  static set instance(OsimNativeSupportPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  /// A group of APIs provided for the outside calling
  Future<bool> sleepSaveUri(String uri);
}
