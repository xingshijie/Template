package com.xingshijie.common.network.model;


/**
 * Notification command syntax
 * @see <a href="https://github.com/google/iosched/blob/master/doc/GCM.md">
 *     https://github.com/google/iosched/blob/master/doc/GCM.md </a>
 */
public class NotificationCommandModel {
    private String audience;  // required:yes can be "all", "local" (in-person attendees only) or "remote" (remote attendees only)

    //allow you to filter what version of the app will receive the notification. Use integer version codes like 200, 201,
    //etc. You can specify only one endpoint (min or max) or both. Both are interpreted as inclusive
    private String maxVersion;
    private String minVersion;  // required:yes

    //If the dialog fields are present, a dialog will be shown when the notification is clicked.
    //That dialog will have the specified title and message. The message can contain newlines (use actual newlines, not \n),
    //and will automaticaly linkify links. dialogYes can be ommitted for dialogs that do not have a positive action.
    //For example "Dismiss" button only. The positive action (the YES button) will always launch the URL.
    private String dialogText;
    private String dialogYes;
    private String dialogNo;
    private String dialogTitle;

    private String format;
    private String expiry;  //indicates when the message expires (if devices get it after that date, they ignore it)
    private String title;   //title to appear in the notification
    private String message; //message to appear in the notification
    private String url;     //the URL to direct the user to when they click the notification

    public String getAudience() {
        return this.audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getMinVersion() {
        return this.minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    public String getDialogText() {
        return this.dialogText;
    }

    public void setDialogText(String dialogText) {
        this.dialogText = dialogText;
    }

    public String getDialogYes() {
        return this.dialogYes;
    }

    public void setDialogYes(String dialogYes) {
        this.dialogYes = dialogYes;
    }

    public String getDialogNo() {
        return this.dialogNo;
    }

    public void setDialogNo(String dialogNo) {
        this.dialogNo = dialogNo;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMaxVersion() {
        return this.maxVersion;
    }

    public void setMaxVersion(String maxVersion) {
        this.maxVersion = maxVersion;
    }

    public String getDialogTitle() {
        return this.dialogTitle;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public String getExpiry() {
        return this.expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
