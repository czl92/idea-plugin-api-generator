package org.r.idea.plugin.generator.impl.probe;

import java.util.List;

/**
 * @Author Casper
 * @DATE 2019/6/23 21:09
 **/
public class JarBuilder {

    /**
     * 容器jar在本jar包中的位置
     */
    private String contarinerJar = "/container.jar";

    /**
     * 目标可运行jar包
     */
    private String productJar = "api-doc.jar";

    /**
     * 临时的源文件目录
     */
    private String tmpJavaDir = "java/";

    /**
     * 临时的class文件目录
     */
    private String tmpClassDir = "class/";


    public void build(String srcDir, String outpuParh) {

        /*编译源文件,并储存为临时文件*/

        /*copy容器*/

        /*清理临时文件*/

    }

    /**
     * 编译源文件并输入到指定的临时目录
     *
     * @param srcDir 源文件的目录路径
     * @return
     */
    private List<String> compile(String srcDir) {
        return null;
    }


}
