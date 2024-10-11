package com.aqdai.pigeonweb.service.impl;

import com.aqdai.pigeoncommon.exception.BusinessException;
import com.aqdai.pigeonweb.entity.TemplateGroup;
import com.aqdai.pigeonweb.dao.TemplateGroupMapper;
import com.aqdai.pigeonweb.service.TemplateGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模版分组表 服务实现类
 * </p>
 *
 * @author aqdai
 * @since 2024-10-09
 */
@Service
public class TemplateGroupServiceImpl extends ServiceImpl<TemplateGroupMapper, TemplateGroup> implements TemplateGroupService {

    @Override
    public boolean save(TemplateGroup templateGroup) {
        QueryWrapper<TemplateGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_code", templateGroup.getGroupCode());

        if (this.count(queryWrapper) > 0) {
            throw new BusinessException("分组编码已存在，请重新填写编码");
        }

        return super.save(templateGroup);
    }
}
