package com.aqdai.pigeonweb.controller;

import com.aqdai.pigeoncommon.exception.BusinessException;
import com.aqdai.pigeoncore.MessageSender;
import com.aqdai.pigeoncore.SenderManager;
import com.aqdai.pigeonweb.entity.ChannelAccount;
import com.aqdai.pigeonweb.service.ChannelAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/channel-account")
public class ChannelAccountController {

    @Autowired
    private ChannelAccountService channelAccountService;

    @PostMapping("/test-send-message")
    public void testSendMessage(@RequestBody ChannelAccount channelAccount) {
        // 将ChannelConfig字符串转换为Map<String, String>
        ObjectMapper objectMapper = new ObjectMapper();
        Map config = null;
        try {
            config = objectMapper.readValue(channelAccount.getChannelConfig(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 获取并发送消息
        MessageSender messageSender = SenderManager.getSender(
                channelAccount.getId(),
                channelAccount.getChannelTypeCode(),
                config
        );

        // 发送消息
        messageSender.test();
    }

    // 查询所有渠道账号
    @GetMapping("/list")
    public List<ChannelAccount> list() {
        return channelAccountService.list();
    }

    // 通过ID查询渠道账号
    @GetMapping("/{id}")
    public ChannelAccount getById(@PathVariable Long id) {
        ChannelAccount account = channelAccountService.getById(id);
        if (account == null) {
            throw new BusinessException("渠道账号不存在");
        }
        return account;
    }

    // 新增渠道账号
    @PostMapping("/add")
    public void add(@RequestBody ChannelAccount channelAccount) {
        boolean success = channelAccountService.save(channelAccount);
        if (!success) {
            throw new BusinessException("渠道账号新增失败");
        }
    }

    // 更新渠道账号
    @PutMapping("/update")
    public void update(@RequestBody ChannelAccount channelAccount) {
        boolean success = channelAccountService.updateById(channelAccount);
        if (!success) {
            throw new BusinessException("渠道账号更新失败");
        }
    }

    // 删除渠道账号
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        boolean success = channelAccountService.removeById(id);
        if (!success) {
            throw new BusinessException("渠道账号删除失败");
        }
    }

    // 批量删除渠道账号
    @DeleteMapping("/deleteBatch")
    public void deleteBatch(@RequestBody List<Long> ids) {
        boolean success = channelAccountService.removeByIds(ids);
        if (!success) {
            throw new BusinessException("批量删除失败");
        }
    }

    // 分页查询渠道账号
    @PostMapping("/page")
    public IPage<ChannelAccount> page(@RequestBody Page<ChannelAccount> page) {
        QueryWrapper<ChannelAccount> queryWrapper = new QueryWrapper<>();
        return channelAccountService.page(page, queryWrapper);
    }
}
