javah -classpath bin/classes -d jni nc.lib.testActivity
rm -r libs/armeabi/libnc_lib_test.so
ndk-build
