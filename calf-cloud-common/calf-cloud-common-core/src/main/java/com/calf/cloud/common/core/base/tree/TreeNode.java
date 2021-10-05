
/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 19时01分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 19:01:54    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * -------------------------------------------------
 * <pre>树节点</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 19:01
 * --------------------------------------------------
 */
@Data
public class TreeNode implements INode {

    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    /**
     * 父节点ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long parentId;

    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<INode> children = new ArrayList<>();

    /**
     * 是否有子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean hasChildren;

    /**
     * 是否有子孙节点
     *
     * @return Boolean
     */
    @Override
    public Boolean getHasChildren() {
        if (children.size() > 0) {
            return true;
        } else {
            return this.hasChildren;
        }
    }
}
