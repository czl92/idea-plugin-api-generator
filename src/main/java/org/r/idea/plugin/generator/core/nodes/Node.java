package org.r.idea.plugin.generator.core.nodes;

import java.util.List;

/**
 * @ClassName Node
 * @Author Casper
 * @DATE 2019/6/21 10:17
 **/
public class Node {

    private String name = "";

    private String desc = "";

    private List<Node> children;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
