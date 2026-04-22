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
import com.utils.PoiUtil;
import com.utils.R;
import com.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/save")
    public R save(@RequestBody XiaofeizheEntity xiaofeizhe, HttpServletRequest request) {
        logger.debug("save方法:,,Controller:{},,xiaofeizhe:{}", this.getClass().getName(), xiaofeizhe.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (StringUtil.isEmpty(role)) return R.error(511, "权限为空");
        Wrapper<XiaofeizheEntity> queryWrapper = new EntityWrapper<XiaofeizheEntity>()
                .eq("username", xiaofeizhe.getUsername())
                .or()
                .eq("xiaofeizhe_phone", xiaofeizhe.getXiaofeizhePhone())
                .or()
                .eq("xiaofeizhe_id_number", xiaofeizhe.getXiaofeizheIdNumber());
        XiaofeizheEntity db = xiaofeizheService.selectOne(queryWrapper);
        if (db == null) {
            xiaofeizhe.setCreateTime(new Date());
            xiaofeizhe.setPassword("123456");
            xiaofeizheService.insert(xiaofeizhe);
            return R.ok();
        }
        return R.error(511, "账户或者消费者手机号或者消费者身份证号已经被使用");
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

    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        logger.debug("delete:,,Controller:{},,ids:{}", this.getClass().getName(), ids.toString());
        xiaofeizheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
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

    @RequestMapping("/batchInsert")
    public R save(String fileName) {
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}", this.getClass().getName(), fileName);
        try {
            List<XiaofeizheEntity> xiaofeizheList = new ArrayList<>();
            Map<String, List<String>> seachFields = new HashMap<>();
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if (lastIndexOf == -1) {
                return R.error(511, "该文件没有后缀");
            } else {
                String suffix = fileName.substring(lastIndexOf);
                if (!".xls".equals(suffix)) {
                    return R.error(511, "只支持后缀为xls的excel文件");
                } else {
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);
                    File file = new File(resource.getFile());
                    if (!file.exists()) {
                        return R.error(511, "找不到上传文件，请联系管理员");
                    } else {
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());
                        dataList.remove(0);
                        for (List<String> data : dataList) {
                            XiaofeizheEntity xiaofeizheEntity = new XiaofeizheEntity();
//                            xiaofeizheEntity.setUsername(data.get(0));
//                            xiaofeizheEntity.setXiaofeizheName(data.get(0));
//                            xiaofeizheEntity.setXiaofeizhePhone(data.get(0));
//                            xiaofeizheEntity.setXiaofeizheIdNumber(data.get(0));
//                            xiaofeizheEntity.setXiaofeizhePhoto("");
//                            xiaofeizheEntity.setSexTypes(Integer.valueOf(data.get(0)));
//                            xiaofeizheEntity.setXiaofeizheEmail(data.get(0));
//                            xiaofeizheEntity.setCreateTime(date);
                            xiaofeizheList.add(xiaofeizheEntity);

                            if (seachFields.containsKey("username")) {
                                seachFields.get("username").add(data.get(0));
                            } else {
                                List<String> username = new ArrayList<>();
                                username.add(data.get(0));
                                seachFields.put("username", username);
                            }
                            if (seachFields.containsKey("xiaofeizhePhone")) {
                                seachFields.get("xiaofeizhePhone").add(data.get(0));
                            } else {
                                List<String> xiaofeizhePhone = new ArrayList<>();
                                xiaofeizhePhone.add(data.get(0));
                                seachFields.put("xiaofeizhePhone", xiaofeizhePhone);
                            }
                            if (seachFields.containsKey("xiaofeizheIdNumber")) {
                                seachFields.get("xiaofeizheIdNumber").add(data.get(0));
                            } else {
                                List<String> xiaofeizheIdNumber = new ArrayList<>();
                                xiaofeizheIdNumber.add(data.get(0));
                                seachFields.put("xiaofeizheIdNumber", xiaofeizheIdNumber);
                            }
                        }

                        List<XiaofeizheEntity> usernameExists = xiaofeizheService.selectList(new EntityWrapper<XiaofeizheEntity>().in("username", seachFields.get("username")));
                        if (usernameExists.size() > 0) {
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for (XiaofeizheEntity s : usernameExists) {
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511, "数据库的该表中的 [账户] 字段已经存在 存在数据为:" + repeatFields.toString());
                        }
                        List<XiaofeizheEntity> phoneExists = xiaofeizheService.selectList(new EntityWrapper<XiaofeizheEntity>().in("xiaofeizhe_phone", seachFields.get("xiaofeizhePhone")));
                        if (phoneExists.size() > 0) {
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for (XiaofeizheEntity s : phoneExists) {
                                repeatFields.add(s.getXiaofeizhePhone());
                            }
                            return R.error(511, "数据库的该表中的 [消费者手机号] 字段已经存在 存在数据为:" + repeatFields.toString());
                        }
                        List<XiaofeizheEntity> idNumberExists = xiaofeizheService.selectList(new EntityWrapper<XiaofeizheEntity>().in("xiaofeizhe_id_number", seachFields.get("xiaofeizheIdNumber")));
                        if (idNumberExists.size() > 0) {
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for (XiaofeizheEntity s : idNumberExists) {
                                repeatFields.add(s.getXiaofeizheIdNumber());
                            }
                            return R.error(511, "数据库的该表中的 [消费者身份证号] 字段已经存在 存在数据为:" + repeatFields.toString());
                        }
                        xiaofeizheService.insertBatch(xiaofeizheList);
                        return R.ok();
                    }
                }
            }
        } catch (Exception e) {
            return R.error(511, "批量插入数据异常，请联系管理员");
        }
    }

    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer id) {
        XiaofeizheEntity xiaofeizhe = new XiaofeizheEntity();
        xiaofeizhe.setPassword("123456");
        xiaofeizhe.setId(id);
        xiaofeizheService.updateById(xiaofeizhe);
        return R.ok();
    }

    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        XiaofeizheEntity xiaofeizhe = xiaofeizheService.selectOne(new EntityWrapper<XiaofeizheEntity>().eq("username", username));
        if (xiaofeizhe != null) {
            xiaofeizhe.setPassword("123456");
            boolean b = xiaofeizheService.updateById(xiaofeizhe);
            if (!b) {
                return R.error();
            }
        } else {
            return R.error("账号不存在");
        }
        return R.ok();
    }

    @RequestMapping("/session")
    public R getCurrXiaofeizhe(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("userId");
        XiaofeizheEntity xiaofeizhe = xiaofeizheService.selectById(id);
        if (xiaofeizhe != null) {
            XiaofeizheView view = new XiaofeizheView();
            BeanUtils.copyProperties(xiaofeizhe, view);
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        } else {
            return R.error(511, "查不到数据");
        }
    }

    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }
}
