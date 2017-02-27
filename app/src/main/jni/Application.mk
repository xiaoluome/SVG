APP_PROJECT_PATH := $(call my-dir)
APP_BUILD_SCRIPT := $(call my-dir)/Android.mk
#APP_MODULES      := libsvgandroid
APP_STL := gnustl_static
APP_CPPFLAGS += -fexceptions
APP_CPPFLAGS += -frtti
