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

import android.content.Context;

import com.xingshijie.template.gcm.GCMCommand;

import static com.xingshijie.template.utils.LogUtils.*;

public class TestCommand extends GCMCommand {
    private static final String TAG = makeLogTag("TestCommand");

    @Override
    public void execute(Context context, String type, String extraData) {
        LOGI(TAG, "Received GCM message: type=" + type + ", extraData=" + extraData);
    }
}
