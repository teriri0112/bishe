package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.XiaofeizheEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 消费者
 */
@TableName("xiaofeizhe")
public class XiaofeizheView extends XiaofeizheEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public XiaofeizheView() {}

    public XiaofeizheView(XiaofeizheEntity xiaofeizheEntity) {
        try {
            BeanUtils.copyProperties(this, xiaofeizheEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
