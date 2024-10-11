package com.aqdai.pigeonweb.controller;

import com.aqdai.pigeonweb.entity.TemplateGroup;
import com.aqdai.pigeonweb.exception.BusinessException;
import com.aqdai.pigeonweb.service.TemplateGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template-group")
public class TemplateGroupController {

    @Autowired
    private TemplateGroupService templateGroupService;

    // 查询所有分组
    @GetMapping("/list")
    public List<TemplateGroup> list() {
        return templateGroupService.list();
    }

    // 通过ID查询
    @GetMapping("/{id}")
    public TemplateGroup getById(@PathVariable Long id) {
        TemplateGroup group = templateGroupService.getById(id);
        if (group == null) {
            throw new BusinessException("分组不存在");
        }
        return group;
    }

    // 新增分组
    @PostMapping("/add")
    public void add(@RequestBody TemplateGroup templateGroup) {
        boolean success = templateGroupService.save(templateGroup);
        if (!success) {
            throw new BusinessException("分组新增失败");
        }
    }

    // 更新分组
    @PutMapping("/update")
    public void update(@RequestBody TemplateGroup templateGroup) {
        boolean success = templateGroupService.updateById(templateGroup);
        if (!success) {
            throw new BusinessException("分组更新失败");
        }
    }

    // 删除分组
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        boolean success = templateGroupService.removeById(id);
        if (!success) {
            throw new BusinessException("分组删除失败");
        }
    }

    // 批量删除分组
    @DeleteMapping("/deleteBatch")
    public void deleteBatch(@RequestBody List<Long> ids) {
        boolean success = templateGroupService.removeByIds(ids);
        if (!success) {
            throw new BusinessException("批量删除失败");
        }
    }

    // 分页查询
    @PostMapping("/page")
    public IPage<TemplateGroup> page(@RequestBody Page<TemplateGroup> page) {
        QueryWrapper<TemplateGroup> queryWrapper = new QueryWrapper<>();
        return templateGroupService.page(page, queryWrapper);
    }
}
