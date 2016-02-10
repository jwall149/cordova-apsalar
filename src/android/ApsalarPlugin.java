package me.tonny.cordova.plugins.apsalar;

import android.app.Activity;

import com.apsalar.sdk.Apsalar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class ApsalarPlugin extends CordovaPlugin {
    public static final String ACTIONS_SEND_EVENT = "sendEvent";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals(ACTIONS_SEND_EVENT)) {
            this.sendEvent(args.getString(0), args.getString(1), callbackContext);
            return true;
        }

        return false;
      }

    private void sendEvent(final String eventName, final String key, final String value, final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Apsalar.event(eventName, key, value);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            }
        });
    }

    @Override
    public void onPause(boolean multitasking) {
        Apsalar.unregisterApsalarReceiver();
    }

    @Override
    public void onResume(boolean multitasking) {
        Apsalar.registerReceiver(this.cordova.getActivity().getApplicationContext());
    }
}
