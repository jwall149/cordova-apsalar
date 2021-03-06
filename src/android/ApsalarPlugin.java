package me.tonny.cordova.plugins.apsalar;

import com.apsalar.sdk.Apsalar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ApsalarPlugin extends CordovaPlugin {
    public static final String ACTIONS_SEND_EVENT = "sendEvent";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {

        int aplKeyId = cordova.getActivity().getResources().getIdentifier("asl_key", "string", cordova.getActivity().getPackageName());
        String aplKey = cordova.getActivity().getString(aplKeyId);

        int aplSecretId = cordova.getActivity().getResources().getIdentifier("asl_secret", "string", cordova.getActivity().getPackageName());
        String aplSecret = cordova.getActivity().getString(aplSecretId);

        if (aplKey != null && aplSecret != null) {
            Apsalar.startSession(cordova.getActivity(), aplKey, aplSecret);
        }
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (this.ACTIONS_SEND_EVENT.equals(action)) {
            String eventName = (String) args.get(0);
            JSONObject eventArgs = null;

            if (args.get(1) instanceof JSONObject) {
              eventArgs = (JSONObject) args.get(1);
            }

            this.sendEvent(eventName, eventArgs, callbackContext);
            return true;
        }
        return false;
    }

    private void sendEvent(final String eventName, final JSONObject args, final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                if (args == null) {
                    Apsalar.event(eventName);
                } else {
                    Apsalar.eventJSON(eventName, args);
                }
                callbackContext.success();
            }
        });
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        Apsalar.unregisterApsalarReceiver();
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        Apsalar.registerReceiver(this.cordova.getActivity().getApplicationContext());
    }
}
