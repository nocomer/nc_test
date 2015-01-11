LOCAL_PATH :=$(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := nc_lib_test
LOCAL_SRC_FILES := nc_lib_testActivity.c

include $(BUILD_SHARED_LIBRARY)
