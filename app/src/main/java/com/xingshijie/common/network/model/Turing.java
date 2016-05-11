package com.xingshijie.common.network.model;

/**
 * {
 "code": 200000,
 "text": "亲，已帮你找到图片",
 "url": "http://m.image.so.com/i?q=%E5%B0%8F%E7%8B%97"
 }
 */
public class Turing {
    private int code;
    private String text;
    private String url;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
