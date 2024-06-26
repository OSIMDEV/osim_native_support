import 'osim_native_support_platform_interface.dart';

enum OsimNativeSupport {
  instance;

  Future<bool> sleepSaveUri(String uri) {
    return OsimNativeSupportPlatform.instance.sleepSaveUri(uri);
  }
}
