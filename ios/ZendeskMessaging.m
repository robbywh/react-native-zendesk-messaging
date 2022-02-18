#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ZendeskMessaging, NSObject)
RCT_EXTERN_METHOD(initialize: (NSString *)channelKey resolver:(RCTPromiseResolveBlock *)resolve rejecter: (RCTPromiseRejectBlock *)reject)
RCT_EXTERN_METHOD(showMessaging)
RCT_EXTERN_METHOD(loginUser: (NSString *)token resolver:(RCTPromiseResolveBlock *)resolve rejecter: (RCTPromiseRejectBlock *)reject)
RCT_EXTERN_METHOD(logoutUser: (RCTPromiseResolveBlock *)resolve rejecter: (RCTPromiseRejectBlock *)reject)
@end
