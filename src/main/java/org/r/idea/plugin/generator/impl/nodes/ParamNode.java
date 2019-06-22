package org.r.idea.plugin.generator.impl.nodes;

import org.r.idea.plugin.generator.core.nodes.Node;

/**
 * @ClassName ParamNode
 * @Author Casper
 * @DATE 2019/6/21 10:18
 **/
public class ParamNode extends Node {

    private String typeShortName;

    private String typeQualifiedName;


    private boolean isJson;

    private boolean isArray;

    private boolean isGenericity;



    public boolean isGenericity() {
        return isGenericity;
    }

    public void setGenericity(boolean genericity) {
        isGenericity = genericity;
    }

    public boolean isJson() {
        return isJson;
    }

    public void setJson(boolean json) {
        isJson = json;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public String getTypeShortName() {
        return typeShortName;
    }

    public void setTypeShortName(String typeShortName) {
        this.typeShortName = typeShortName;
    }

    public String getTypeQualifiedName() {
        return typeQualifiedName;
    }

    public void setTypeQualifiedName(String typeQualifiedName) {
        String[] split = typeQualifiedName.split("\\.");
        if (split.length != 0) {
            this.typeShortName = split[split.length - 1];
        } else {
            this.typeShortName = typeQualifiedName;
        }
        this.typeQualifiedName = typeQualifiedName;
    }

}
