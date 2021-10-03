package com.calf.cloud.user.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.calf.cloud.dal.pojo.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * -------------------------------------------------
 * <pre>用户信息</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/3 11:12
 * --------------------------------------------------
 */
@TableName("user_info")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Accessors(chain = true)
public class UserInfoEntity extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 注册手机号
     */
    @NotEmpty
    private String phone;

    /**
     * 注册邮箱
     */
    @NotEmpty
    private String email;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别(1:男 2:女)
     */
    private Byte sex;

    /**
     * 盐
     */
    @NotEmpty
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 身份证号
     */
    @TableField("identity_card")
    private String identityCard;

    /**
     * 微信唯一标识
     */
    @TableField("union_id")
    private String unionId;

    /**
     * 微信openID
     */
    @TableField("open_id")
    private String openId;

    /**
     * 学历编码
     */
    @TableField("degree_code")
    private Boolean degreeCode;

    /**
     * 学历名称
     */
    @TableField("degree_name")
    private String degreeName;

    /**
     * 用户绑定微信的昵称，不可修改，用户换绑页面展示
     */
    @TableField("wx_nickname")
    private String wxNickame;

    /**
     * 是否毕业(0:未毕业 1:毕业)
     */
    @TableField("graduation_flag")
    private Boolean graduationFlag;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 账户状态 1-已激活、0-未激活
     */
    @TableField("user_state")
    private Byte userState;

    /**
     * 注册时间
     */
    @TableField("registerTime")
    private LocalDateTime registerTime;

    /**
     * 苹果登陆唯一标识
     */
    @TableField("sign_apple_id")
    private String signAppleId;


    private static final long serialVersionUID = 1L;

}