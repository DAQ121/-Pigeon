package com.aqdai.pigeonweb.controller;

import com.aqdai.pigeonweb.entity.ChannelType;
import com.aqdai.pigeonweb.entity.TemplateGroup;
import com.aqdai.pigeonweb.exception.BusinessException;
import com.aqdai.pigeonweb.service.ChannelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aqdai
 * @since 2024-10-10
 */
@RestController
@RequestMapping("/channel-type")
public class ChannelTypeController {

    @Autowired
    private ChannelTypeService channelTypeService;

    // 查询所有渠道类型
    @GetMapping("/list")
    public List<ChannelType> list() {
        return channelTypeService.list();
    }

    // 新增渠道类型
    @PostMapping("/add")
    public void add(@RequestBody ChannelType channelType) {
        boolean success = channelTypeService.save(channelType);
        if (!success) {
            throw new BusinessException("渠道类型新增失败");
        }
    }


}
