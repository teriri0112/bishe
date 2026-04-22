package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 消费者
 */
@TableName("xiaofeizhe")
public class XiaofeizheEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public XiaofeizheEntity() {}

    public XiaofeizheEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "xiaofeizhe_name")
    private String xiaofeizheName;

    @TableField(value = "xiaofeizhe_phone")
    private String xiaofeizhePhone;

    @TableField(value = "xiaofeizhe_id_number")
    private String xiaofeizheIdNumber;

    @TableField(value = "xiaofeizhe_photo")
    private String xiaofeizhePhoto;

    @TableField(value = "sex_types")
    private Integer sexTypes;

    @TableField(value = "xiaofeizhe_email")
    private String xiaofeizheEmail;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getXiaofeizheName() { return xiaofeizheName; }
    public void setXiaofeizheName(String xiaofeizheName) { this.xiaofeizheName = xiaofeizheName; }

    public String getXiaofeizhePhone() { return xiaofeizhePhone; }
    public void setXiaofeizhePhone(String xiaofeizhePhone) { this.xiaofeizhePhone = xiaofeizhePhone; }

    public String getXiaofeizheIdNumber() { return xiaofeizheIdNumber; }
    public void setXiaofeizheIdNumber(String xiaofeizheIdNumber) { this.xiaofeizheIdNumber = xiaofeizheIdNumber; }

    public String getXiaofeizhePhoto() { return xiaofeizhePhoto; }
    public void setXiaofeizhePhoto(String xiaofeizhePhoto) { this.xiaofeizhePhoto = xiaofeizhePhoto; }

    public Integer getSexTypes() { return sexTypes; }
    public void setSexTypes(Integer sexTypes) { this.sexTypes = sexTypes; }

    public String getXiaofeizheEmail() { return xiaofeizheEmail; }
    public void setXiaofeizheEmail(String xiaofeizheEmail) { this.xiaofeizheEmail = xiaofeizheEmail; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
