package com.plugin.mbtileserver;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;

public class MbtileServer extends CordovaPlugin {

    public WebServer webserver;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("start")) {
            this.start(args, callbackContext);
            return true;
        }
        if (action.equals("stop")) {
            this.stop(args, callbackContext);
            return true;
        }
        return false;
    }

    /**
     * Starts the server
     * @param args
     * @param callbackContext
     */
    private void start(JSONArray args, CallbackContext callbackContext) throws JSONException {
        int port = 8080;
        String filePath = null;

        if (args.length() == 1) {
            filePath = args.getString(0);
        }
        if (args.length() == 2) {
            filePath = args.getString(0);
            port = args.getInt(1);
        }

        File file = new File(filePath);
        if (!file.exists()) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Cannot get file"));
            return;
        }

        if (this.webserver != null){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Server already running"));
            return;
        }

        try {
            this.webserver = new WebServer(port, filePath);
            this.webserver.start();
        }catch (Exception e){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            return;
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        return;
    }

    /**
     * Stops the server
     * @param args
     * @param callbackContext
     */
    private void stop(JSONArray args, CallbackContext callbackContext) {
        if (this.webserver != null) {
            this.webserver.stop();
            this.webserver = null;
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Server is not running"));
    }
}
