package com.dao;

import com.entity.XiaofeizheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaofeizheView;

/**
 * 消费者 Dao 接口
 *
 * @author 
 */
public interface XiaofeizheDao extends BaseMapper<XiaofeizheEntity> {

   List<XiaofeizheView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
