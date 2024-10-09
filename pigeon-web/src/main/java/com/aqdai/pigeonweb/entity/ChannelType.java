package com.aqdai.pigeonweb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 渠道类型表
 * @TableName channel_type
 */
@TableName(value ="channel_type")
@Data
public class ChannelType extends BaseEntity{

    private String channelName;

    private String channelCode;

}
