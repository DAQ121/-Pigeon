package com.aqdai.pigeoncore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractMessage implements MessageSender,MessageReceiver {
    @Override
    public void send(Message message) {
        // 通用的发送前日志记录或格式化
        log.info("Sending message to: " + message.getReceiver());
        // 调用具体的实现发送逻辑
        doSend(message);
    }

    protected abstract void doSend(Message message);
}
