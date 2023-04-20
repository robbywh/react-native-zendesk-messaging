# @robbywh/react-native-zendesk-messaging

Zendesk Messaging for React Native

## Installation

```sh
yarn add @robbywh/react-native-zendesk-messaging
```

For the Android platform, don't forget to add this script inside `android/build.gradle`
```
allprojects {
    repositories {
        maven {
            url "https://zendesk.jfrog.io/artifactory/repo"
        }
    }
}
```

## Usage

```js
import * as React from 'react';
import { Text, View, Button, Platform } from 'react-native';
import Config from 'react-native-config';
import {
  initialize,
  showMessaging,
} from '@flashcoffee/react-native-zendesk-messaging';
const App = () => {
  React.useEffect(() => {
    initialize(
      Platform.OS === 'android'
        ? Config.CHANNEL_KEY_ANDROID
        : Config.CHANNEL_KEY_IOS
    );
  }, []);
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
      <Text style={{ marginBottom: 10, textAlign: 'center' }}>
        Press The "CHAT" button to test
      </Text>
      <Button onPress={() => showMessaging()} title="CHAT" />
    </View>
  );
};
export default App;
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)