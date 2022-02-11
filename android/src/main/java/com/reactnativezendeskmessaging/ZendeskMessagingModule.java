package com.reactnativezendeskmessaging;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import zendesk.android.Zendesk;
import zendesk.messaging.android.DefaultMessagingFactory;

@ReactModule(name = ZendeskMessagingModule.NAME)
public class ZendeskMessagingModule extends ReactContextBaseJavaModule {
    public static final String NAME = "ZendeskMessaging";
    private final ReactApplicationContext reactContext;

    public ZendeskMessagingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void initialize(String channelKey) {
        Zendesk.initialize(
            this.reactContext,
            channelKey,
            zendesk -> Log.i("IntegrationApplication", "Initialization successful"),
            error -> Log.e("IntegrationApplication", "Messaging failed to initialize", error),
            new DefaultMessagingFactory());
    }

    @ReactMethod
    public void showMessaging() {
        Zendesk.getInstance().getMessaging().showMessaging(this.reactContext.getCurrentActivity());
    }
}
