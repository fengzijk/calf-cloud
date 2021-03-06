
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

import java.util.List;

/**
 * <pre>节点合并</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 19:04
 */
public class ForestNodeMerger {

    /**
     * 将节点数组归并为一个森林（多棵树）（填充节点的children域） 时间复杂度为O(n^2)
     *
     * @param items 节点域
     * @return java.util.List<T>
     * @author : fengzijk
     * @date : 2021/10/5 19:04
     */
    public static <T extends INode> List<T> merge(List<T> items) {
        ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items);
        items.forEach(forestNode -> {
            if (forestNode.getParentId() != 0) {
                INode node = forestNodeManager.getTreeNodeById(forestNode.getParentId());
                if (node != null) {
                    node.getChildren().add(forestNode);
                } else {
                    forestNodeManager.addParentId(forestNode.getId());
                }
            }
        });
        return forestNodeManager.getRoot();
    }
}
