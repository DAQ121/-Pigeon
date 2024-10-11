package com.aqdai.pigeonweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 渠道账号表
 * @TableName channel_account
 */
@TableName(value ="channel_account")
@Data
public class ChannelAccount extends BaseEntity {

    // 渠道名称
    private String channelName;

    // 渠道描述
    private String channelCode;

    // 渠道类型名称
    private String channelTypeName;

    // 渠道类型编码
    private String channelTypeCode;

    @TableField(value = "channel_config")
    private String channelConfig;  // 存储不同渠道的配置信息
}
