import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-zendesk-messaging' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const ZendeskMessaging = NativeModules.ZendeskMessaging
  ? NativeModules.ZendeskMessaging
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export const initialize = async (
  channelKey: string,
  onSuccess?: () => void,
  onError?: (err: any) => void
) => {
  try {
    await ZendeskMessaging.initialize(channelKey);
    onSuccess && onSuccess();
  } catch (err: any) {
    onError && onError(err);
  }
};

export const showMessaging = () => {
  return ZendeskMessaging.showMessaging();
};

export const loginUser = async (
  token: string,
  onSuccess?: (user: any) => void,
  onError?: (err: any) => void
) => {
  try {
    const user = await ZendeskMessaging.loginUser(token);
    onSuccess && onSuccess(user);
  } catch (err: any) {
    onError && onError(err);
  }
};

export const logoutUser = async (
  onSuccess?: () => void,
  onError?: (err: any) => void
) => {
  try {
    await ZendeskMessaging.logoutUser();
    onSuccess && onSuccess();
  } catch (err: any) {
    onError && onError(err);
  }
};

export const updatePushNotificationToken = async (
  token: string,
  onSuccess?: () => void,
  onError?: (err: any) => void
) => {
  try {
    await ZendeskMessaging.updatePushNotificationToken(token);
    onSuccess && onSuccess();
  } catch (err: any) {
    onError && onError(err);
  }
};