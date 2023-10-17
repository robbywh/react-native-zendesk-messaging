package com.robbywh.reactnativezendeskmessaging;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import kotlin.Unit;
import zendesk.android.FailureCallback;
import zendesk.android.SuccessCallback;
import zendesk.android.Zendesk;
import zendesk.android.ZendeskUser;
import zendesk.messaging.android.DefaultMessagingFactory;
import zendesk.messaging.android.push.PushNotifications;

@ReactModule(name = ReactNativeZendeskMessagingModule.NAME)
public class ReactNativeZendeskMessagingModule extends ReactContextBaseJavaModule {
  public static final String NAME = "ZendeskMessaging";
  private final ReactApplicationContext reactContext;

  public ReactNativeZendeskMessagingModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void initialize(String channelKey, Promise promise) {
    Zendesk.initialize(
      this.reactContext,
      channelKey,
      zendesk -> promise.resolve("success"),
      error -> promise.reject(error),
      new DefaultMessagingFactory());
  }

  @ReactMethod
  public void showMessaging() {
    Zendesk.getInstance().getMessaging().showMessaging(this.reactContext.getCurrentActivity());
  }

  @ReactMethod
  public void getUnreadMessageCount(Promise promise) {
    try {
      int unreadMsgCount = Zendesk.getInstance().getMessaging().getUnreadMessageCount();
      promise.resolve(unreadMsgCount);
    } catch (Exception e) {
      promise.reject("Zendesk Error", e.getMessage());
    }
  }

  @ReactMethod
  public void loginUser(String token, Promise promise) {
    Zendesk.getInstance().loginUser(token, new SuccessCallback<ZendeskUser>() {
      @Override
      public void onSuccess(ZendeskUser value) {
        RNZendeskUser serializableUser = new RNZendeskUser(value);
        try {
          promise.resolve(serializableUser.asWritableMap());
        } catch (Exception e) {
          e.printStackTrace();
          promise.reject(e);
        }
      }
    }, new FailureCallback<Throwable>() {
      @Override
      public void onFailure(@NonNull Throwable error) {
        promise.reject(error);
      }
    });
  }

  @ReactMethod
  public void logoutUser(Promise promise) {
    Zendesk.getInstance().logoutUser(new SuccessCallback<Unit>() {
      @Override
      public void onSuccess(Unit value) {
        promise.resolve("success");
      }
    }, new FailureCallback<Throwable>() {
      @Override
      public void onFailure(@NonNull Throwable error) {
        promise.reject(error);
      }
    });
  }

  @ReactMethod
  public void updatePushNotificationToken(String deviceToken,Promise promise) {
    try {
      PushNotifications.updatePushNotificationToken(deviceToken);
      promise.resolve("success");
    } catch(Exception error){
      promise.reject(error);
    }
  }
}
