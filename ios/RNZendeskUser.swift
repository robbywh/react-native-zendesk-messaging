import ZendeskSDK

@objc(RNZendeskUser)
class RNZendeskUser: NSObject {
    var id: String
    var externalId: String

  init(_ user: ZendeskUser) {
      self.id = user.id
      self.externalId = user.externalId
  }

  func asDictionary() -> NSDictionary {
    return [
      "id" : self.id,
      "externalId" : self.externalId,
    ]
  }
}
