package com.entity.model;

import com.entity.XiaofeizheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 消费者
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XiaofeizheModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 消费者姓名
     */
    private String xiaofeizheName;


    /**
     * 消费者手机号
     */
    private String xiaofeizhePhone;


    /**
     * 消费者身份证号
     */
    private String xiaofeizheIdNumber;


    /**
     * 消费者头像
     */
    private String xiaofeizhePhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String xiaofeizheEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：消费者姓名
	 */
    public String getXiaofeizheName() {
        return xiaofeizheName;
    }


    /**
	 * 设置：消费者姓名
	 */
    public void setXiaofeizheName(String xiaofeizheName) {
        this.xiaofeizheName = xiaofeizheName;
    }
    /**
	 * 获取：消费者手机号
	 */
    public String getXiaofeizhePhone() {
        return xiaofeizhePhone;
    }


    /**
	 * 设置：消费者手机号
	 */
    public void setXiaofeizhePhone(String xiaofeizhePhone) {
        this.xiaofeizhePhone = xiaofeizhePhone;
    }
    /**
	 * 获取：消费者身份证号
	 */
    public String getXiaofeizheIdNumber() {
        return xiaofeizheIdNumber;
    }


    /**
	 * 设置：消费者身份证号
	 */
    public void setXiaofeizheIdNumber(String xiaofeizheIdNumber) {
        this.xiaofeizheIdNumber = xiaofeizheIdNumber;
    }
    /**
	 * 获取：消费者头像
	 */
    public String getXiaofeizhePhoto() {
        return xiaofeizhePhoto;
    }


    /**
	 * 设置：消费者头像
	 */
    public void setXiaofeizhePhoto(String xiaofeizhePhoto) {
        this.xiaofeizhePhoto = xiaofeizhePhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getXiaofeizheEmail() {
        return xiaofeizheEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setXiaofeizheEmail(String xiaofeizheEmail) {
        this.xiaofeizheEmail = xiaofeizheEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
