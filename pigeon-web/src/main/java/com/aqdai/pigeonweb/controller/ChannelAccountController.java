package com.aqdai.pigeonweb.controller;

import com.aqdai.pigeonweb.entity.ChannelAccount;
import com.aqdai.pigeonweb.exception.BusinessException;
import com.aqdai.pigeonweb.service.ChannelAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel-account")
public class ChannelAccountController {

    @Autowired
    private ChannelAccountService channelAccountService;

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
