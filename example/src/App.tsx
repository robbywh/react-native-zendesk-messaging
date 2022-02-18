import * as React from 'react';
import { Text, View, Button, Platform } from 'react-native';
import Config from 'react-native-config';
import {
  initialize,
  showMessaging,
  loginUser,
  logoutUser,
} from '@flashcoffee/react-native-zendesk-messaging';

const App = () => {
  const [loadingInit, setLoadingInit] = React.useState(true);
  const [isLogin, setIsLogin] = React.useState(false);
  React.useEffect(() => {
    const channelKey =
      Platform.OS === 'android'
        ? Config.CHANNEL_KEY_ANDROID
        : Config.CHANNEL_KEY_IOS;
    initialize(
      channelKey,
      () => {
        setLoadingInit(false);
      },
      (err: any) => {
        setLoadingInit(false);
        console.log(err);
      }
    );
  }, []);

  const login = () => {
    loginUser(
      Config.JWT_TOKEN,
      (user) => {
        setIsLogin(true);
        console.log('isLoading', user);
      },
      (err) => {
        console.log('error', err);
      }
    );
  };

  const logout = () => {
    logoutUser(() => {
      setIsLogin(false);
    });
  };

  return (
    <View style={{ padding: 100 }}>
      <Text
        style={{
          marginBottom: 50,
          textAlign: 'center',
          fontWeight: 'bold',
          fontSize: 20,
        }}
      >
        Zendesk Messaging
      </Text>
      {loadingInit ? (
        <Text>Loading...</Text>
      ) : isLogin ? (
        <>
          <Text style={{ marginBottom: 10, textAlign: 'center' }}>
            Press The "CHAT" button to test
          </Text>
          <Button onPress={showMessaging} title="CHAT" />
          <View style={{ marginBottom: 100 }} />
          <Button onPress={logout} title="LOGOUT" />
        </>
      ) : (
        <Button onPress={login} title="LOGIN" />
      )}
    </View>
  );
};

export default App;
