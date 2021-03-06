/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View,PermissionsAndroid } from 'react-native';
import { Button } from 'react-native';
import InstaCaptureModule from './Dependinces/InstaCaptureModule';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

async function requestCameraPermission() {
  try {
    const granted = await PermissionsAndroid.request(
      PermissionsAndroid.PERMISSIONS.WRITE_EXTERNAL_STORAGE,
      {
        'title': 'Instacapture App Storage Permission',
        'message': 'Instacapture App needs access to your Storage ' +
                   'so it can Save ScreenShots.'
      }
    )
    if (granted === PermissionsAndroid.RESULTS.GRANTED) {
      console.log("You can use the camera")
    } else {
      console.log("Camera permission denied")
    }
  } catch (err) {
    console.warn(err)
  }
}



type Props = {};
export default class App extends Component<Props> {
  async componentWillMount() {
    await requestCameraPermission()
    }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Press The Button To Take ScreenShot!</Text>
        <Text style={styles.instructions}>The Image Is Saved in The External Memory</Text>
        <Text style={styles.instructions}>in Pictures/InstaBug</Text>
          <Button
          onPress={() => {
           InstaCaptureModule.capturescreen();
          }}
          title="Take ScreenShot"
          color="#841584"
        
          />

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
