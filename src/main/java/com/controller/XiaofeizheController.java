package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.XiaofeizheEntity;
import com.entity.view.XiaofeizheView;
import com.service.DictionaryService;
import com.service.TokenService;
import com.service.XiaofeizheService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 消费者
 * 后端接口
 */
@RestController
@Controller
@RequestMapping("/xiaofeizhe")
public class XiaofeizheController {
    private static final Logger logger = LoggerFactory.getLogger(XiaofeizheController.class);

    @Autowired
    private XiaofeizheService xiaofeizheService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("page方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (StringUtil.isEmpty(role)) return R.error(511, "权限为空");
        else if ("消费者".equals(role)) params.put("id", request.getSession().getAttribute("userId"));
        if (params.get("orderBy") == null || params.get("orderBy") == "") {
            params.put("orderBy", "id");
        }
        PageUtils page = xiaofeizheService.queryPage(params);
        for (XiaofeizheView c : (java.util.List<XiaofeizheView>) page.getList()) {
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        logger.debug("info方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        XiaofeizheEntity xiaofeizhe = xiaofeizheService.selectById(id);
        if (xiaofeizhe != null) {
            XiaofeizheView view = new XiaofeizheView();
            BeanUtils.copyProperties(xiaofeizhe, view);
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }
        return R.error(511, "查不到数据");
    }

    @RequestMapping("/update")
    public R update(@RequestBody XiaofeizheEntity xiaofeizhe, HttpServletRequest request) {
        logger.debug("update方法:,,Controller:{},,xiaofeizhe:{}", this.getClass().getName(), xiaofeizhe.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (StringUtil.isEmpty(role)) return R.error(511, "权限为空");
        if ("消费者".equals(role)) {
            xiaofeizhe.setId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        }
        Wrapper<XiaofeizheEntity> queryWrapper = new EntityWrapper<XiaofeizheEntity>()
                .notIn("id", xiaofeizhe.getId())
                .andNew()
                .eq("username", xiaofeizhe.getUsername())
                .or()
                .eq("xiaofeizhe_phone", xiaofeizhe.getXiaofeizhePhone())
                .or()
                .eq("xiaofeizhe_id_number", xiaofeizhe.getXiaofeizheIdNumber());

        XiaofeizheEntity db = xiaofeizheService.selectOne(queryWrapper);
        if ("".equals(xiaofeizhe.getXiaofeizhePhoto()) || "null".equals(xiaofeizhe.getXiaofeizhePhoto())) {
            xiaofeizhe.setXiaofeizhePhoto(null);
        }
        if (db == null) {
            xiaofeizheService.updateById(xiaofeizhe);
            return R.ok();
        }
        return R.error(511, "账户或者消费者手机号或者消费者身份证号已经被使用");
    }

    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        XiaofeizheEntity xiaofeizhe = xiaofeizheService.selectOne(new EntityWrapper<XiaofeizheEntity>().eq("username", username));
        if (xiaofeizhe == null || !xiaofeizhe.getPassword().equals(password)) return R.error("账号或密码不正确");
        String token = tokenService.generateToken(xiaofeizhe.getId(), username, "xiaofeizhe", "消费者");
        R r = R.ok();
        r.put("token", token);
        r.put("role", "消费者");
        r.put("username", xiaofeizhe.getXiaofeizheName());
        r.put("tableName", "xiaofeizhe");
        r.put("userId", xiaofeizhe.getId());
        return r;
    }

    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody XiaofeizheEntity xiaofeizhe) {
        Wrapper<XiaofeizheEntity> queryWrapper = new EntityWrapper<XiaofeizheEntity>()
                .eq("username", xiaofeizhe.getUsername())
                .or()
                .eq("xiaofeizhe_phone", xiaofeizhe.getXiaofeizhePhone())
                .or()
                .eq("xiaofeizhe_id_number", xiaofeizhe.getXiaofeizheIdNumber());
        XiaofeizheEntity db = xiaofeizheService.selectOne(queryWrapper);
        if (db != null) return R.error("账户或者消费者手机号或者消费者身份证号已经被使用");
        xiaofeizhe.setCreateTime(new Date());
        xiaofeizheService.insert(xiaofeizhe);
        return R.ok();
    }
}
