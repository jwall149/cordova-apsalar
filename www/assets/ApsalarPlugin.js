var apsalarPlugin = {
  sendEvent: function(eventName, key, value, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'ApsalarPlugin',
      'sendEvent', [eventName, key, value]
    );
  },
};

module.exports = apsalarPlugin;
