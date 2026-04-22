package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.XiaofeizheEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface XiaofeizheService extends IService<XiaofeizheEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
