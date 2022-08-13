/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年06月19日 13时33分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-06-19 13:33:40    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.dal.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * <pre>数据库基础字段:</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/3 11:05
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -9039853980855787753L;

    /**
     * 创建人ID
     */
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    protected Long createId;

    /**
     * 更新人ID
     */
    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    protected Long updateId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 删除标记
     */
    @TableField("delete_flag")
    private Boolean deleteFlag;


    /**
     * 账户状态 1-已激活、0-未激活
     */
    private Boolean status;

}
