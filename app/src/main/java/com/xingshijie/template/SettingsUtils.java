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

package com.xingshijie.template;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.xingshijie.template.utils.LogUtils.makeLogTag;

/**
 * Utilities and constants related to app settings_prefs.
 */
public class SettingsUtils {

    private static final String TAG = makeLogTag(SettingsUtils.class);

    /**
     * Boolean preference indicating the user would like to see times in their local timezone
     * throughout the app.
     */
    public static final String PREF_LOCAL_TIMES = "pref_local_times";


    /**
     * Boolean preference indicating whether the app has
     * {@code com.google.samples.apps.iosched.ui.BaseActivity.performDataBootstrap installed} the
     * {@code R.raw.bootstrap_data bootstrap data}.
     */
    public static final String PREF_DATA_BOOTSTRAP_DONE = "pref_data_bootstrap_done";

    /**
     * Mark that the app has finished loading the {@code R.raw.bootstrap_data bootstrap data}.
     *
     * @param context Context to be used to edit the {@link SharedPreferences}.
     */
    public static void markDataBootstrapDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_DATA_BOOTSTRAP_DONE, true).apply();
    }

    /**
     * Return true when the {@code R.raw.bootstrap_data_json bootstrap data} has been marked loaded.
     *
     * @param context Context to be used to lookup the {@link SharedPreferences}.
     */
    public static boolean isDataBootstrapDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_DATA_BOOTSTRAP_DONE, false);
    }

}
