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
 *  2021-10-05 19:01:00    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * -------------------------------------------------
 * <pre>森林节点</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 18:58
 * --------------------------------------------------
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ForestNode extends TreeNode {

    private static final long serialVersionUID = -5188222097134746118L;

    /**
     * 节点内容
     */
    private Object content;

    public ForestNode(Long id, Long parentId, Object content) {
        this.id = id;
        this.parentId = parentId;
        this.content = content;
    }

}
