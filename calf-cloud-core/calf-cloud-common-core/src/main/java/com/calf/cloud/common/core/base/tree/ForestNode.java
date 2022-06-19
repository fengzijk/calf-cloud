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
 *  2022-06-19 13:33:39    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <pre>森林节点</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 18:58
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
