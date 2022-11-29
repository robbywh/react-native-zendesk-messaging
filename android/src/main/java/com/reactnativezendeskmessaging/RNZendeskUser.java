public class RNZendeskUser {
  public String id;
  public String externalId;

  public RNZendeskUser(ZendeskUser user) {
    this.id = user.getId();
    this.externalId = user.externalId();
  }

  public ReadableMap<String, String> asReadableMap() {
    return new ReadableMap<String, String>() {{
      put("id", this.id);
      put("externalId", this.externalId);
    }};
  }
}
