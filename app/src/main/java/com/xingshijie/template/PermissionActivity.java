/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xingshijie.template;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import static com.xingshijie.template.utils.LogUtils.makeLogTag;

public class PermissionActivity extends AppCompatActivity{

    private static final String TAG = makeLogTag(PermissionActivity.class);

    private static final int PERMISSIONS_REQUEST_CODE = 1111;
    private RelativeLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        mContainer = (RelativeLayout) findViewById(R.id.main_activity_container);

        if (!havePermissions()) {
            Log.i(TAG, "Requesting permissions needed for this app.");
            requestPermissions();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (havePermissions()) {
            //TODO do somethings
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    private boolean havePermissions() {
        return ContextCompat.checkSelfPermission(this,   Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_CODE);
    }

    private void showLinkToSettingsSnackbar() {
        if (mContainer == null) {
            return;
        }
        Snackbar.make(mContainer,
                "Permission was denied, but is needed. Go to Settings.",
                Snackbar.LENGTH_INDEFINITE)
                .setAction("settings", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Build intent that displays the App settings screen.
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",
                                BuildConfig.APPLICATION_ID, null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).show();
    }

    private void showRequestPermissionsSnackbar() {
        if (mContainer == null) {
            return;
        }
        Snackbar.make(mContainer, "Location permission is required by Nearby.",
                Snackbar.LENGTH_INDEFINITE)
                .setAction("ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Request permission.
                        ActivityCompat.requestPermissions(PermissionActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[]permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }

        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    Log.i(TAG, "Permission denied without 'NEVER ASK AGAIN': "
                            + permission);
                    showRequestPermissionsSnackbar();
                } else {
                    Log.i(TAG, "Permission denied with 'NEVER ASK AGAIN': "
                            + permission);
                    showLinkToSettingsSnackbar();
                }
            } else {
                Log.i(TAG, "Permission granted, do something");
            }
        }
    }
}
