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
package com.xingshijie.template.gcm.command;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.xingshijie.template.R;
import com.xingshijie.template.gcm.GCMCommand;

import java.io.Serializable;

import static com.xingshijie.template.utils.LogUtils.LOGD;
import static com.xingshijie.template.utils.LogUtils.LOGE;
import static com.xingshijie.template.utils.LogUtils.LOGI;
import static com.xingshijie.template.utils.LogUtils.makeLogTag;


public class NotificationCommand extends GCMCommand {
    private static final String TAG = makeLogTag("NotificationCommand");

    private static class NotificationCommandModel implements Serializable{
        String format;
        String audience;
        String minVersion;
        String maxVersion;
        String title;
        String message;
        String expiry;
        String dialogTitle;
        String dialogText;
        String dialogYes;
        String dialogNo;
        String url;
    }

    @Override
    public void execute(Context context, String type, String payload) {
        LOGI(TAG, "Received GCM message: " + type);
        LOGI(TAG, "Parsing GCM notification command: " + payload);
        Gson gson = new Gson();
        NotificationCommandModel command;
        try {
            command = gson.fromJson(payload, NotificationCommandModel.class);
            if (command == null) {
                LOGE(TAG, "Failed to parse command (gson returned null).");
                return;
            }
            LOGD(TAG, "Format: " + command.format);
            LOGD(TAG, "Audience: " + command.audience);
            LOGD(TAG, "Title: " + command.title);
            LOGD(TAG, "Message: " + command.message);
            LOGD(TAG, "Expiry: " + command.expiry);
            LOGD(TAG, "URL: " + command.url);
            LOGD(TAG, "Dialog title: " + command.dialogTitle);
            LOGD(TAG, "Dialog text: " + command.dialogText);
            LOGD(TAG, "Dialog yes: " + command.dialogYes);
            LOGD(TAG, "Dialog no: " + command.dialogNo);
            LOGD(TAG, "Min version code: " + command.minVersion);
            LOGD(TAG, "Max version code: " + command.maxVersion);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGE(TAG, "Failed to parse GCM notification command.");
            return;
        }

        LOGD(TAG, "Processing notification command.");
        processCommand(context, command);
    }

    private void processCommand(Context context, NotificationCommandModel command) {
        // Check format

        final String title = TextUtils.isEmpty(command.title) ?
                context.getString(R.string.app_name) : command.title;
        final String message = TextUtils.isEmpty(command.message) ? "" : command.message;

        // fire the notification
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(0, new NotificationCompat.Builder(context)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.notification_template_icon_low_bg)
                        .setTicker(command.message)
                        .setContentTitle(title)
                        .setContentText(message)
                        //.setColor(context.getResources().getColor(R.color.theme_primary))
                            // Note: setColor() is available in the support lib v21+.
                            // We commented it out because we want the source to compile 
                            // against support lib v20. If you are using support lib
                            // v21 or above on Android L, uncomment this line.
                        .setContentIntent(PendingIntent.getActivity(context, 0, null,
                                PendingIntent.FLAG_CANCEL_CURRENT))
                        .setAutoCancel(true)
                        .build());
    }

}
