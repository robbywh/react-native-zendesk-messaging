import ZendeskSDKMessaging
import ZendeskSDK
import ZendeskUser

@objc(ZendeskMessaging)
class ZendeskMessaging: NSObject {
  @objc
  static func requiresMainQueueSetup() -> Bool {
    return true
  }

  @objc
  func initialize(_ channelKey:String, resolver resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock) {
    Zendesk.initialize(withChannelKey: channelKey,
                      messagingFactory: DefaultMessagingFactory()) { result in
      if case let .failure(error) = result {
        reject("error","\(error)",nil)
        print("Messaging did not initialize.\nError: \(error.localizedDescription)")
      } else {
        resolve("success")
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

  @objc
  func loginUser(_ token:String, resolver resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock) {
    Zendesk.instance?.loginUser(with: token) { result in
      switch result {
      case .success(let user):
          print(user)
          let serializableUser = RNZendeskUser(user)
          resolve(serializableUser.asDictionary())
      case .failure(let error):
          reject("error","\(error)",nil)
      }
    }
  }

  @objc
  func logoutUser(_ resolve: @escaping RCTPromiseResolveBlock,
                        rejecter reject: @escaping RCTPromiseRejectBlock) {
    Zendesk.instance?.logoutUser { result in
      switch result {
      case .success:
          resolve("success")
      case .failure(let error):
          reject("error","\(error)",nil)
      }
    }
  }

  @objc
  func updatePushNotificationToken(_ deviceToken:String,
    resolver resolve: @escaping RCTPromiseResolveBlock,
    rejecter reject: @escaping RCTPromiseRejectBlock
  ){
    do{
      try PushNotifications.updatePushNotificationToken(Data(deviceToken.utf8))
      resolve("success");
    } catch {
      reject("error","\(error)",nil)
   }
  }
}
