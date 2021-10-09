
/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 19时04分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 19:04:20    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.base.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * -------------------------------------------------
 * <pre>森林节点管理</pre>
 *
 * @author : fengzijk
 * @date : 2021/10/5 19:00
 * --------------------------------------------------
 */
public class ForestNodeManager<T extends INode> {

    /**
     * 森林的所有节点
     */
    private List<T> list;

    /**
     * 森林的父节点ID
     */
    private List<Long> parentIds = new ArrayList<>();

    public ForestNodeManager(List<T> items) {
        list = items;
    }


    /**
     * 根据节点ID获取一个节点
     *
     * @param id 节点ID
     * @return com.calf.cloud.common.core.base.tree.INode
     * @author : fengzijk
     * @date : 2021/10/5 19:03
     */
    public INode getTreeNodeById(Long id) {
        for (INode forestNode : list) {
            if (forestNode.getId().longValue() == id.longValue()) {
                return forestNode;
            }
        }
        return null;
    }


    /**
     * 增加父节点ID
     *
     * @param parentId 父节点
     * @author : fengzijk
     * @date : 2021/10/5 19:02
     */
    public void addParentId(Long parentId) {
        parentIds.add(parentId);
    }


    /**
     * 获取树的根节点(一个森林对应多颗树)
     *
     * @return java.util.List<T> 树的根节点集合
     * @author : fengzijk
     * @date : 2021/10/5 19:02
     */
    public List<T> getRoot() {
        List<T> roots = new ArrayList<>();
        for (T forestNode : list) {
            if (forestNode.getParentId() == 0 || parentIds.contains(forestNode.getId())) {
                roots.add(forestNode);
            }
        }
        return roots;
    }
}
