import 'osim_native_support_platform_interface.dart';

Future<bool> sleepSaveUri(String uri) {
  return OsimNativeSupportPlatform.instance.sleepSaveUri(uri);
}
