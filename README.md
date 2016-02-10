# Apsalar plugins for cordova

### Meteor

### Install

    meteor add cordova:cordova-apsalar@https://github.com/jwall149/cordova-apsalar.git#b52c143b43349d160852524b5f9f1b4e587e816f

### How to use

- Add to mobile-config.js

    App.configurePlugin('cordova-apsalar', {
      APSALAR_KEY: 'Your key'
      APSALAR_SECRET: 'Your secret'
    })

- Send event:

    cordova.plugins.Apsalar.sendEvent('Start application')
    cordova.plugins.Apsalar.sendEvent('Click', {foo: bar});



