#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ZendeskMessaging, NSObject)
RCT_EXTERN_METHOD(initialize: (NSString *)channelKey)
RCT_EXTERN_METHOD(showMessaging)
@end
