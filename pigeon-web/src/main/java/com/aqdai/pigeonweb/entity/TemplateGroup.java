package com.aqdai.pigeonweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 模版分组表
 * @TableName template_group
 */
@TableName(value ="template_group")
@Data
public class TemplateGroup extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 分组名
     */
    private String groupName;

    /**
     * 分组编码（唯一）
     */
    private String groupCode;

    /**
     * 分组描述
     */
    private String groupDescription;

    /**
     * 模版数量
     */
    private Integer templateNumber;
}
