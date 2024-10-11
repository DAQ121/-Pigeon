package com.aqdai.pigeoncore;

import com.aqdai.pigeoncore.impl.DingDingMessage;
import com.aqdai.pigeoncore.impl.WechatMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SenderManager {

    // 缓存发送者实例，key 可以是账号 ID 或者其他标识符
    private static final Map<Long, MessageSender> senderCache = new ConcurrentHashMap<>();

    // 获取发送者实例，如果不存在则创建新的实例并缓存
    public static MessageSender getSender(Long accountId, String channelType, Map<String, String> config) {
        if (senderCache.containsKey(accountId)) {
            return senderCache.get(accountId);
        }

        MessageSender sender = createSender(channelType, config);
        senderCache.put(accountId, sender);
        return sender;
    }

    // 根据渠道类型创建不同的发送者实例
    private static MessageSender createSender(String channelType, Map<String, String> config) {
        switch (channelType) {
            case "DINGDING":
                return new DingDingMessage(config.get("secret"), config.get("webhook"));
            case "WECHAT":
                return new WechatMessage(config.get("corpId"), config.get("corpSecret"));
            default:
                throw new IllegalArgumentException("Unsupported channel type: " + channelType);
        }
    }
}
