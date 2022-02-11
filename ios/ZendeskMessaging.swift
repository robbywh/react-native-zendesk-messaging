import ZendeskSDKMessaging
import ZendeskSDK

@objc(ZendeskMessaging)
class ZendeskMessaging: NSObject { 
  @objc
  static func requiresMainQueueSetup() -> Bool {
    return true
  }

  @objc
  func initialize(_ channelKey:String) {
    Zendesk.initialize(withChannelKey: channelKey,
                       messagingFactory: DefaultMessagingFactory()) { result in
      if case let .failure(error) = result {
        print("Messaging did not initialize.\nError: \(error.localizedDescription)")
      }
    }
  }
  
  @objc
  func showMessaging() {
    DispatchQueue.main.async {
      guard let zendeskController = Zendesk.instance?.messaging?.messagingViewController() else {
        return }
      let viewController = RCTPresentedViewController();
      viewController?.present(zendeskController, animated: true) {
        print("Messaging have shown")
      }
    }
  }
}
