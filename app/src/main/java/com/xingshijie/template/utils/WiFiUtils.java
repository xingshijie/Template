/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xingshijie.template.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;

import static com.xingshijie.template.utils.LogUtils.makeLogTag;

public class WiFiUtils {
    // Preference key and values associated with WiFi AP configuration.
    public static final String PREF_WIFI_AP_CONFIG = "pref_wifi_ap_config";
    public static final String WIFI_CONFIG_DONE = "done";
    public static final String WIFI_CONFIG_REQUESTED = "requested";

    private static final String TAG = makeLogTag(WiFiUtils.class);


    public static boolean isWiFiEnabled(final Context context) {
        final WifiManager wifiManager =
                (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    // Stored settings_prefs associated with WiFi AP configuration.
    public static String getWiFiConfigStatus(final Context context) {
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_WIFI_AP_CONFIG, null);
    }

    public static void setWiFiConfigStatus(final Context context, final String status) {
        if (!WIFI_CONFIG_DONE.equals(status) && !WIFI_CONFIG_REQUESTED.equals(status))
            throw new IllegalArgumentException("Invalid WiFi Config status: " + status);
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_WIFI_AP_CONFIG, status).apply();
    }

}
