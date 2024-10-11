package com.aqdai.pigeoncore.impl;

import com.aqdai.pigeoncommon.exception.BusinessException;
import com.aqdai.pigeoncore.AbstractMessage;
import com.aqdai.pigeoncore.Message;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;

@Slf4j
public class DingDingMessage extends AbstractMessage {

    private String secret;
    private String webhookUrl;

    public DingDingMessage(String secret, String webhookUrl) {
        this.secret = secret;
        this.webhookUrl = webhookUrl;
    }

    @Override
    protected void doSend(Message message) {
        try {
            // 获取钉钉加签
            Long timestamp = System.currentTimeMillis();
            String sign = generateSign(timestamp, secret);

            // 构建钉钉的消息发送Client
            DingTalkClient client = new DefaultDingTalkClient(
                    webhookUrl + "&timestamp=" + timestamp + "&sign=" + sign
            );

            // 构建发送请求
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");

            // 构建文本消息
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(message.getContent());
            request.setText(text);

            // 执行消息发送
            OapiRobotSendResponse response = client.execute(request);

            // 检查发送结果
            if (response.getErrcode() != 0) {
                log.error("钉钉消息发送失败, 错误码: {}, 错误信息: {}", response.getErrcode(), response.getErrmsg());
                throw new BusinessException("钉钉消息发送失败: " + response.getErrmsg());
            }
            log.info("钉钉消息发送成功, 响应: {}", response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送钉钉消息时出错", e.getMessage());
        }
    }

    // 加签逻辑
    private String generateSign(Long timestamp, String secret) throws Exception {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
    }

    @Override
    public void test() {
        // 构建一个示例消息
        Message message = new Message();
        message.setReceiver("Receiver ID");
        message.setContent("测试文本消息");
        message.setType("text");

        try {
            doSend(message);
        } catch (Exception e) {
            log.error("测试消息发送失败: {}", e.getMessage());
            throw new BusinessException("测试失败, 请检查您的渠道账号配置是否正确");
        }
    }
}
