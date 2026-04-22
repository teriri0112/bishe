package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 消费者
 *
 * @author 
 * @email
 */
@TableName("xiaofeizhe")
public class XiaofeizheEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XiaofeizheEntity() {

	}

	public XiaofeizheEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 消费者姓名
     */
    @TableField(value = "xiaofeizhe_name")

    private String xiaofeizheName;


    /**
     * 消费者手机号
     */
    @TableField(value = "xiaofeizhe_phone")

    private String xiaofeizhePhone;


    /**
     * 消费者身份证号
     */
    @TableField(value = "xiaofeizhe_id_number")

    private String xiaofeizheIdNumber;


    /**
     * 消费者头像
     */
    @TableField(value = "xiaofeizhe_photo")

    private String xiaofeizhePhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    @TableField(value = "xiaofeizhe_email")

    private String xiaofeizheEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：消费者姓名
	 */
    public String getXiaofeizheName() {
        return xiaofeizheName;
    }


    /**
	 * 获取：消费者姓名
	 */

    public void setXiaofeizheName(String xiaofeizheName) {
        this.xiaofeizheName = xiaofeizheName;
    }
    /**
	 * 设置：消费者手机号
	 */
    public String getXiaofeizhePhone() {
        return xiaofeizhePhone;
    }


    /**
	 * 获取：消费者手机号
	 */

    public void setXiaofeizhePhone(String xiaofeizhePhone) {
        this.xiaofeizhePhone = xiaofeizhePhone;
    }
    /**
	 * 设置：消费者身份证号
	 */
    public String getXiaofeizheIdNumber() {
        return xiaofeizheIdNumber;
    }


    /**
	 * 获取：消费者身份证号
	 */

    public void setXiaofeizheIdNumber(String xiaofeizheIdNumber) {
        this.xiaofeizheIdNumber = xiaofeizheIdNumber;
    }
    /**
	 * 设置：消费者头像
	 */
    public String getXiaofeizhePhoto() {
        return xiaofeizhePhoto;
    }


    /**
	 * 获取：消费者头像
	 */

    public void setXiaofeizhePhoto(String xiaofeizhePhoto) {
        this.xiaofeizhePhoto = xiaofeizhePhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getXiaofeizheEmail() {
        return xiaofeizheEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setXiaofeizheEmail(String xiaofeizheEmail) {
        this.xiaofeizheEmail = xiaofeizheEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Xiaofeizhe{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", xiaofeizheName=" + xiaofeizheName +
            ", xiaofeizhePhone=" + xiaofeizhePhone +
            ", xiaofeizheIdNumber=" + xiaofeizheIdNumber +
            ", xiaofeizhePhoto=" + xiaofeizhePhoto +
            ", sexTypes=" + sexTypes +
            ", xiaofeizheEmail=" + xiaofeizheEmail +
            ", createTime=" + createTime +
        "}";
    }
}
