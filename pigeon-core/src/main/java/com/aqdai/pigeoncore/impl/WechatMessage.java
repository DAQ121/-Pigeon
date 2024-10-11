package com.aqdai.pigeoncore.impl;


import com.aqdai.pigeoncore.AbstractMessage;
import com.aqdai.pigeoncore.Message;

public class WechatMessage extends AbstractMessage {

    private String corpId;
    private String corpSecret;

    public WechatMessage(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    @Override
    protected void doSend(Message message) {
        // 企业微信消息发送逻辑
//        WechatApi.send(message);
    }

    @Override
    public void test() {

    }
}