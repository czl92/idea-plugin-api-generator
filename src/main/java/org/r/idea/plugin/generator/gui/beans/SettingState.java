package org.r.idea.plugin.generator.gui.beans;

/**
 * @ClassName SettingState
 * @Author Casper
 * @DATE 2019/6/10 9:47
 **/
public class SettingState {

    /**
     * 持久化的源文件路径
     */
    private String srcFilePaths;
    /**
     * 持久化的接口文件路径
     */
    private String interfaceFilePaths;
    /**
     * 持久化的文件输出目录
     */
    private String outputFilePaths;
    /**
     * 持久化的模板文件目录路径
     */
    private String templateFilePaths;
    /**
     * 持久化的基本类型
     */
    private String baseClass;
    /**
     * 持久化的集合类型
     */
    private String collectionClass;
    /**
     * 持久化的不支持类型
     */
    private String nonsupportClass;

    public String getSrcFilePaths() {
        return srcFilePaths;
    }

    public void setSrcFilePaths(String srcFilePaths) {
        this.srcFilePaths = srcFilePaths;
    }

    public String getInterfaceFilePaths() {
        return interfaceFilePaths;
    }

    public void setInterfaceFilePaths(String interfaceFilePaths) {
        this.interfaceFilePaths = interfaceFilePaths;
    }

    public String getOutputFilePaths() {
        return outputFilePaths;
    }

    public void setOutputFilePaths(String outputFilePaths) {
        this.outputFilePaths = outputFilePaths;
    }

    public String getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(String baseClass) {
        this.baseClass = baseClass;
    }

    public String getCollectionClass() {
        return collectionClass;
    }

    public void setCollectionClass(String collectionClass) {
        this.collectionClass = collectionClass;
    }

    public String getNonsupportClass() {
        return nonsupportClass;
    }

    public void setNonsupportClass(String nonsupportClass) {
        this.nonsupportClass = nonsupportClass;
    }

    public String getTemplateFilePaths() {
        return templateFilePaths;
    }

    public void setTemplateFilePaths(String templateFilePaths) {
        this.templateFilePaths = templateFilePaths;
    }
}
