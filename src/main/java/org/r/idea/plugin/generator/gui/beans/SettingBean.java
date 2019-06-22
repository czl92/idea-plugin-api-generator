package org.r.idea.plugin.generator.gui.beans;

import java.util.List;

/**
 * @ClassName SettingBean
 * @Author Casper
 * @DATE 2019/6/10 9:44
 **/
public class SettingBean {

    /**
     * 源文件路径
     */
    private List<String> srcFilePaths;
    /**
     * 接口文件路径
     */
    private List<String> interfaceFilePaths;
    /**
     * 文件输出目录
     */
    private String outputFilePaths;
    /**
     * 基本类型
     */
    private List<String> baseClass;
    /**
     * 集合类型
     */
    private List<String> collectionClass;
    /**
     * 不支持类型
     */
    private List<String> nonsupportClass;

    public List<String> getSrcFilePaths() {
        return srcFilePaths;
    }

    public void setSrcFilePaths(List<String> srcFilePaths) {
        this.srcFilePaths = srcFilePaths;
    }

    public List<String> getInterfaceFilePaths() {
        return interfaceFilePaths;
    }

    public void setInterfaceFilePaths(List<String> interfaceFilePaths) {
        this.interfaceFilePaths = interfaceFilePaths;
    }

    public String getOutputFilePaths() {
        return outputFilePaths;
    }

    public void setOutputFilePaths(String outputFilePaths) {
        this.outputFilePaths = outputFilePaths;
    }

    public List<String> getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(List<String> baseClass) {
        this.baseClass = baseClass;
    }

    public List<String> getCollectionClass() {
        return collectionClass;
    }

    public void setCollectionClass(List<String> collectionClass) {
        this.collectionClass = collectionClass;
    }

    public List<String> getNonsupportClass() {
        return nonsupportClass;
    }

    public void setNonsupportClass(List<String> nonsupportClass) {
        this.nonsupportClass = nonsupportClass;
    }
}
