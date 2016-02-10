# Apsalar plugins for cordova

### Meteor

### Install

    meteor add cordova:cordova-apsalar@https://github.com/jwall149/cordova-apsalar.git#4e8beab91de5d6bd069703cb168d06a9e07391df

### How to use

- Add to mobile-config.js

    App.configurePlugin('cordova-apsalar', {
      APSALAR_KEY: 'Your key'
      APSALAR_SECRET: 'Your secret'
    })

- Send event:

    cordova.plugins.Apsalar.sendEvent('Start application')

    cordova.plugins.Apsalar.sendEvent('Click', {foo: bar});



