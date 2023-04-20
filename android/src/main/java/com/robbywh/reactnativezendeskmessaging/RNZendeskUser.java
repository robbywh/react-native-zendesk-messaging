package com.robbywh.reactnativezendeskmessaging;
import zendesk.android.ZendeskUser;
import com.facebook.react.bridge.WritableNativeMap;

public class RNZendeskUser {
  public String id;
  public String externalId;

  public RNZendeskUser(ZendeskUser user) {
    this.id = user.getId();
    this.externalId = user.getExternalId();
  }

  public WritableNativeMap asWritableMap() {
    WritableNativeMap userMap = new WritableNativeMap();
    userMap.putString("id", this.id);
    userMap.putString("externalId", this.externalId);
    return userMap;

  }
}
