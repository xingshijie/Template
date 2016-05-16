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
package com.xingshijie.template.gcm;

import android.content.Context;
import android.content.Intent;

import com.xingshijie.template.gcm.command.AnnouncementCommand;
import com.xingshijie.template.gcm.command.NotificationCommand;
import com.xingshijie.template.gcm.command.SyncCommand;
import com.xingshijie.template.gcm.command.SyncUserCommand;
import com.xingshijie.template.gcm.command.TestCommand;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.xingshijie.template.utils.LogUtils.*;


/**
 * {@link android.app.IntentService} responsible for handling GCM messages.
 */
public class GCMClient {

    private static final String TAG = makeLogTag("GCM");

    private static final Map<String, GCMCommand> MESSAGE_RECEIVERS;
    static {
        // Known messages and their GCM message receivers
        Map <String, GCMCommand> receivers = new HashMap<String, GCMCommand>();
        receivers.put("test", new TestCommand());
        receivers.put("announcement", new AnnouncementCommand());
        receivers.put("sync_schedule", new SyncCommand());
        receivers.put("sync_user", new SyncUserCommand());
        receivers.put("notification", new NotificationCommand());
        MESSAGE_RECEIVERS = Collections.unmodifiableMap(receivers);
    }

    protected void onRegistered(Context context, String regId) {
        LOGI(TAG, "Device registered: regId=" + regId);
    }

    protected void onUnregistered(Context context, String regId) {
        LOGI(TAG, "Device unregistered");
        ServerUtilities.unregister(context, regId);
    }
   
    protected void onMessage(Context context, Intent intent) {
        String action = intent.getStringExtra("action");
        String extraData = intent.getStringExtra("extraData");
        LOGD(TAG, "Got GCM message, action=" + action + ", extraData=" + extraData);

        if (action == null) {
            LOGE(TAG, "Message received without command action");
            return;
        }

        action = action.toLowerCase();
        GCMCommand command = MESSAGE_RECEIVERS.get(action);
        if (command == null) {
            LOGE(TAG, "Unknown command received: " + action);
        } else {
            command.execute(context, action, extraData);
        }

    }

    public void onError(Context context, String errorId) {
        LOGE(TAG, "Received error: " + errorId);
    }

    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        LOGW(TAG, "Received recoverable error: " + errorId);
        return false;
    }
}
